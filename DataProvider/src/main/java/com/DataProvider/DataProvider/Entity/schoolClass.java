package com.DataProvider.DataProvider.Entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name= "class")
public class schoolClass {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="section")
    private Section sectionId;
    private int no_of_students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="class_teacher")
    private User classTeacher;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_in_class")
    private List<User> studentInClass;

    public schoolClass(int id, String name, Section sectionId, int no_of_students, User classTeacher) {
        this.id = id;
        this.name = name;
        this.sectionId = sectionId;
        this.no_of_students = no_of_students;
        this.classTeacher = classTeacher;
    }

    public schoolClass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getStudentInClass() {
        return studentInClass;
    }

    public void setStudentInClass(List<User> studentInClass) {
        this.studentInClass = studentInClass;
    }

    public Set<Subject> getClassSubject() {
        return classSubject;
    }

    public void setClassSubject(Set<Subject> classSubject) {
        this.classSubject = classSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Section getSectionId() {
        return sectionId;
    }

    public void setSectionId(Section sectionId) {
        this.sectionId = sectionId;
    }

    public int getNo_of_students() {
        return no_of_students;
    }

    public void setNo_of_students(int no_of_students) {
        this.no_of_students = no_of_students;
    }

    public User getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(User classTeacher) {
        this.classTeacher = classTeacher;
    }



    @ManyToMany
    @JoinTable(
            name = "class_sub",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    Set<Subject> classSubject;
}
