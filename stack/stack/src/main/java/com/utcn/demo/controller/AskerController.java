package com.utcn.demo.controller;

import com.utcn.demo.model.Asker;
import com.utcn.demo.service.AskerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asker")
public class AskerController {

    @Autowired
    AskerService askerService;

    @GetMapping("/getAll")
    public List<Asker> getPerson(){
        return askerService.retrievePersons();
    }

}
