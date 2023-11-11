package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.DTO.*;
import com.DataProvider.DataProvider.Entity.RefreshToken;
import com.DataProvider.DataProvider.Entity.Role;
import com.DataProvider.DataProvider.Entity.User;
import com.DataProvider.DataProvider.Repository.RefreshTokenRepo;
import com.DataProvider.DataProvider.Repository.UserRepository;
import com.DataProvider.DataProvider.Service.AuthService;
import com.DataProvider.DataProvider.config.jwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired

    private UserRepository userRepository;
    @Autowired

    private jwtService jwtService;
    @Autowired
    RefreshTokenRepo refreshTokenRepo;

   private  SecurityContextLogoutHandler securityContextLogoutHandler;





    @Override
    public ResponseDTO login(loginRequestDTO dto) {
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            if(Objects.isNull(dto.getUsername()) && Objects.isNull(dto.getPassword()))
            {
                responseDTO.setMessage("UserName or Password Not Found!");
                responseDTO.setStatusCode(HttpStatus.NOT_FOUND);
                return responseDTO;
            }

         User u= userRepository.findAllByEmail(dto.getUsername());
         boolean passMatch = passwordEncoder.matches(dto.getPassword(), u.getPassword());
        if(Objects.nonNull(u) && passMatch)
        {
            loginReSPONSEDTO loginReSPONSEDTO =new loginReSPONSEDTO();
         String token=   generateToken(u);
            loginReSPONSEDTO.setToken(token);
            //generating refresh token
          RefreshToken rt=  refreshTokenRepo.findByUser(u);
          if(Objects.nonNull(rt) )

          {
              ResponseDTO res =new ResponseDTO();
              if( Objects.nonNull(validateRefreshToken(rt.getToken())))
              {
                  RefreshToken t= validateRefreshToken(rt.getToken());
                   res= updateRefreshTokenExpiry(t);
              }
              else {
                  res = this.createToken(u);

              }
              if(res.getStatusCode().equals(HttpStatus.OK))
              {
                  RefreshToken refreshToken= (RefreshToken) res.getBody();
                  loginReSPONSEDTO.setRefreshToken(refreshToken.getToken());
              }

          }

           responseDTO.setBody(loginReSPONSEDTO);
           responseDTO.setMessage("Login Sucessfully");
           responseDTO.setStatusCode(HttpStatus.OK);
            return responseDTO;
        }
            responseDTO.setMessage("Login Failed!");
            responseDTO.setStatusCode(HttpStatus.CONFLICT);
           return responseDTO;
        }
        catch(Exception e)
        {
            System.out.println(e);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseDTO;

        }

    }

    private ResponseDTO updateRefreshTokenExpiry(RefreshToken refreshToken) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE, 6);
        Date updatedDate = calendar.getTime();
        refreshToken.setTokenExpiry(updatedDate);
        refreshToken.setStatus(true);
        refreshToken=  refreshTokenRepo.save(refreshToken);
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatusCode(HttpStatus.OK);
        responseDTO.setBody(refreshToken);
        return  responseDTO;

    }

    @Override
    public String generateToken(User user)
    {
        String token=   jwtService.generateToken(user);

        return token;
    }

    @Override
    public ResponseDTO register(RegisterRequestDTO dto) {
        try{
            ResponseDTO responseDTO = new ResponseDTO();

            if(dto.getRoles().size() > 0) {
                List<Role> roleList = new ArrayList<>();
                dto.getRoles().forEach(r -> {

                    Role role = new Role();
                    role.setId(r);
                    roleList.add(role);
                });
                String password = passwordEncoder.encode(dto.getPassword());
                User user = new User();
                user.setUserRole((Set<Role>) roleList);
                user.setId(dto.getId());
                user.setName(dto.getName());
                user.setEnabled(true);

                user.setPassword(password);
                user.setEmail(dto.getEmailAddress());

                user =  userRepository.save(user);
                if(Objects.nonNull(user))
                {
                    loginReSPONSEDTO loginReSPONSEDTO = new loginReSPONSEDTO();
                    String token=   generateToken(user);
                    loginReSPONSEDTO.setToken(token);
                    responseDTO.setBody(loginReSPONSEDTO);
                    responseDTO.setMessage("Registered Successfully!");
                    responseDTO.setStatusCode(HttpStatus.OK);
                    return responseDTO;
                }
                responseDTO.setMessage("Employee Not Saved Successfully!");
                responseDTO.setStatusCode(HttpStatus.CONFLICT);
                return responseDTO;

            }
            responseDTO.setMessage("Roles Not found!");
            responseDTO.setStatusCode(HttpStatus.NOT_FOUND);
            return responseDTO;
        }
       catch (Exception e)
       {
           ResponseDTO responseDTO = new ResponseDTO();
           System.out.println(e);
           responseDTO.setMessage("Registration Failed!"+e.getMessage());
           responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
           return responseDTO;
       }

    }


    @Override
    public ResponseDTO createToken(User user) {
        try{
            ResponseDTO responseDTO = new ResponseDTO();
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setUser(user);
            Date currentDate = new Date();

            // Create a Calendar instance and set it to the current date and time
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            // Add 6 minutes to the current time
            calendar.add(Calendar.MINUTE, 6);

            // Get the updated date and time
            Date updatedDate = calendar.getTime();
            refreshToken.setTokenExpiry(updatedDate);
            refreshToken.setStatus(true);
            refreshToken=  refreshTokenRepo.save(refreshToken);
            responseDTO.setMessage("Refresh Token generated successfully!");
            responseDTO.setStatusCode(HttpStatus.OK);
            responseDTO.setBody(refreshToken);
            return responseDTO;

        }
        catch(Exception e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseDTO;
        }
    }

    @Override
    public RefreshToken validateRefreshToken(String token) {
        try{
            RefreshToken refreshToken = refreshTokenRepo.findByToken(token);
            if(Objects.nonNull(refreshToken)){
                if(  refreshToken.getTokenExpiry().after(new Date())){

                    return refreshToken;
                }
                refreshTokenRepo.delete(refreshToken);
                return null;
            }
            return null;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public ResponseDTO generateRefreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        RefreshToken refreshToken = validateRefreshToken(refreshTokenRequestDTO.getRefreshToken());
        if(Objects.nonNull(refreshToken)) {
            User user=    userRepository.findAllByEmail(refreshTokenRequestDTO.getUsername());
            refreshToken.setTokenExpiry(new Date(System.currentTimeMillis() *300000));
            refreshToken.setStatus(true);
            refreshToken=  refreshTokenRepo.save(refreshToken);
            String token= this.generateToken(user);
            loginReSPONSEDTO loginReSPONSEDTO =new loginReSPONSEDTO();
            loginReSPONSEDTO.setRefreshToken(refreshToken.getToken());
            loginReSPONSEDTO.setToken(token);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setBody(loginReSPONSEDTO);
            responseDTO.setMessage("Token Refreshed Successfully!");
            responseDTO.setStatusCode(HttpStatus.OK);
            return responseDTO;

        }
        return null;
    }

    @Override
  public  ResponseDTO logout(Authentication authentication,
                       HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse)
    {
        securityContextLogoutHandler.logout(httpServletRequest,httpServletResponse,authentication);
        return null;
    }


}
