package com.school.schoolmanagement.repository;

import com.school.schoolmanagement.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<Fee, Long> {
}