package com.utcn.demo.service;

import com.utcn.demo.repository.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertService {
    @Autowired
    private ExpertRepository expertRepository;

    public List<com.utcn.demo.model.Expert> retrieveExperts(){
        return (List<com.utcn.demo.model.Expert>) this.expertRepository.findAll();
    }

    public com.utcn.demo.model.Expert retrieveExpertById(Long id){

        Optional<com.utcn.demo.model.Expert> expert=this.expertRepository.findById(id);
        if(expert.isPresent()){
            return expert.get();
        }else{
            return null;
        }

    }

    public com.utcn.demo.model.Expert addExpert(com.utcn.demo.model.Expert expert){
        return this.expertRepository.save(expert);
    }

    public String deleteExpertById(Long id){
        try{
            this.expertRepository.deleteById(id);
            return "Entry successfully deleted!";
        }catch (Exception e){
            return "Failed to delete entry with id:" + id;
        }
    }

}
