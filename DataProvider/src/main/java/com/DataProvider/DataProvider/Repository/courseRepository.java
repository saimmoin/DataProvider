package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface courseRepository extends JpaRepository <Course, Integer> {

}
