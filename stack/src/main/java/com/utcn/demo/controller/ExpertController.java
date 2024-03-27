package com.utcn.demo.controller;

import com.utcn.demo.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experts")
public class ExpertController {

    @Autowired
    private ExpertService expertService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<com.utcn.demo.model.Expert> getAllExperts() {
        List<com.utcn.demo.model.Expert> experts = this.expertService.retrieveExperts();
        return experts;
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public com.utcn.demo.model.Expert getExpertById(@PathVariable Long id) {
       com.utcn.demo.model.Expert expert = this.expertService.retrieveExpertById(id);
        return expert;
    }

    @GetMapping("/getById")
    @ResponseBody
    public com.utcn.demo.model.Expert getExpertByIdVar2(@RequestParam("id") Long id) {
        com.utcn.demo.model.Expert expert = this.expertService.retrieveExpertById(id);
        return expert;
    }

    @PostMapping("/createExpert")
    @ResponseBody
    public com.utcn.demo.model.Expert createExpert(@RequestBody com.utcn.demo.model.Expert expert){
        return  this.expertService.addExpert(expert);
    }

    @PutMapping("/updateExpert")
    @ResponseBody
    public com.utcn.demo.model.Expert updateExpert(@RequestBody com.utcn.demo.model.Expert expert){
        return  this.expertService.addExpert(expert);
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public String deleteExpertById(@RequestParam Long id){
        return this.expertService.deleteExpertById(id);
    }
}
