package com.thomas.jobapp.job;

import com.thomas.jobapp.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    @Autowired
    private jobService jobservice;


    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobservice.findAll(),HttpStatus.OK);
    }




    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        jobservice.createJob(job);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id){
        Job job=jobservice.findById(id);
        if (job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
        boolean update=jobservice.updateJob(id,updatedJob);
        if (update)
            return new ResponseEntity<>("updated successfully",HttpStatus.OK);
        return new ResponseEntity<>("Invalid id",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deletById(@PathVariable Long id){
        Boolean deleted=jobservice.deletById(id);
        if(deleted){
            return new ResponseEntity<>("Job Deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid job Id",HttpStatus.NOT_FOUND);
    }
}
