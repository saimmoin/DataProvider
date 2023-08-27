package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface studentRepository extends JpaRepository <Student, Integer> {

}
