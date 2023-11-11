package com.DataProvider.DataProvider.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="attendence")
public class Attendence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private User studentId;
    private Date attendence_date;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getStudentId() {
        return studentId;
    }

    public void setStudentId(User studentId) {
        this.studentId = studentId;
    }

    public Date getAttendence_date() {
        return attendence_date;
    }

    public void setAttendence_date(Date attendence_date) {
        this.attendence_date = attendence_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Attendence(int id, User studentId, Date attendence_date, boolean status) {
        this.id = id;
        this.studentId = studentId;
        this.attendence_date = attendence_date;
        this.status = status;
    }

    public Attendence() {
    }
}

