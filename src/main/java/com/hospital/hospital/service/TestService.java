package com.hospital.hospital.service;

import com.hospital.hospital.model.Test;
import com.hospital.hospital.repo.TestRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestRepo testRepo ;

    public Test saveTest(Test test){
        return testRepo.save(test);
    }
}
