package com.thomas.jobapp.company;

import com.thomas.jobapp.job.Job;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    Company findById(Long id);

    void createCompany(Company company);

    Boolean deleteCompanyById(Long id);

    Boolean updateCompany(Long id, Company company);

    Company findCompanyById(Long id);

}
