package com.DataProvider.DataProvider.Entity;

import jakarta.persistence.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;

    @JoinColumn(name = "user_type")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserType userTypeId;
    private String gender;
    private String designation;
    private String password;
    private String email;
    private String address;
    private Long phoneNumber;
    private Date dateJoined;
    private String rollnumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private User parentId;
    private boolean status;
    private boolean isDeleted;
    private boolean enabled;
    private boolean isParent;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_class")
    private schoolClass  studentClass;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_subject")
    private Subject teacherSubject;

    public User() {
    }

    public User(int id, String name, UserType userTypeId, String gender, String designation, String password, String email, String address, Long phoneNumber, Date dateJoined, String rollnumber, User parentId, boolean status, boolean isDeleted, boolean enabled, boolean isParent, schoolClass studentClass, Subject teacherSubject, List<Attendence> attendence, Set<Fees> studentFees, Set<Role> userRole) {
        this.id = id;
        this.name = name;
        this.userTypeId = userTypeId;
        this.gender = gender;
        this.designation = designation;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateJoined = dateJoined;
        this.rollnumber = rollnumber;
        this.parentId = parentId;
        this.status = status;
        this.isDeleted = isDeleted;
        this.enabled = enabled;
        this.isParent = isParent;
        this.studentClass = studentClass;
        this.teacherSubject = teacherSubject;
        this.attendence = attendence;
        this.studentFees = studentFees;
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UserType userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getRollnumber() {
        return rollnumber;
    }

    public void setRollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
    }

    public User getParentId() {
        return parentId;
    }

    public void setParentId(User parentId) {
        this.parentId = parentId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public schoolClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(schoolClass studentClass) {
        this.studentClass = studentClass;
    }

    public Subject getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(Subject teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public List<Attendence> getAttendence() {
        return attendence;
    }

    public void setAttendence(List<Attendence> attendence) {
        this.attendence = attendence;
    }

    public Set<Fees> getStudentFees() {
        return studentFees;
    }

    public void setStudentFees(Set<Fees> studentFees) {
        this.studentFees = studentFees;
    }

    public Set<Role> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<Role> userRole) {
        this.userRole = userRole;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "attendence")
    private List<Attendence> attendence;
    @ManyToMany
    @JoinTable(
            name = "student_fees",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "fees_id"))
    Set<Fees> studentFees;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> userRole;

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        userRole.stream().forEach(r->{
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(r.getName());
            list.add(simpleGrantedAuthority);
        });
        return  list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
