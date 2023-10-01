package com.DataProvider.DataProvider.config;

import com.DataProvider.DataProvider.Scheduling.CroneJob;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final jwtService jwtServices;
    private  final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String header=request.getHeader("Authorization");
        if( header==null || !header.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }
        final String jwtToken=header.substring(7);
        String username =null;     //emailaddress
        username=jwtServices.extractUserName(jwtToken);
        if(Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
            if(jwtServices.isTokenValid(jwtToken,userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

        }
        filterChain.doFilter(request,response);

    }
//    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    @Scheduled(cron = "0 */10 * ? * *")
    public void runEvey10Seconds() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate);
        System.out.println("Rayyan pagal hai.........%$$@^$^&@#& ");
    }

}
