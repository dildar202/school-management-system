package com.school.schoolmanagement.service;

import com.school.schoolmanagement.entity.Fee;
import com.school.schoolmanagement.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {

    @Autowired
    private FeeRepository repository;

    public List<Fee> getAllFees() {
        return repository.findAll();
    }

    public Fee saveFee(Fee fee) {
        return repository.save(fee);
    }

    public Fee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}