package com.thomas.jobapp.job;


import java.util.List;


public interface jobService {
    List<Job> findAll();
    void createJob(Job job);

    Job findById(Long id);

    Boolean deletById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
