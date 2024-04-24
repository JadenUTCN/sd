package com.utcn.demo.service;

import com.utcn.demo.model.Asker;
import com.utcn.demo.repository.AskerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AskerService {

    @Autowired
    private AskerRepository askerRepository;

    public List<Asker> retrievePersons(){
        return (List<Asker>) askerRepository.findAll();
    }
}
