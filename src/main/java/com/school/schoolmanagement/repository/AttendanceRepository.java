package com.school.schoolmanagement.repository;

import com.school.schoolmanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // FIXED METHOD (IMPORTANT)
    void deleteByStudent_Id(Long studentId);
}