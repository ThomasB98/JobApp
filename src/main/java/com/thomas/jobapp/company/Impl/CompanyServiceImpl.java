package com.thomas.jobapp.company.Impl;

import com.thomas.jobapp.company.Company;
import com.thomas.jobapp.company.CompanyRepository;
import com.thomas.jobapp.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company) {
       companyRepository.save(company);
    }

    @Override
    public Boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCompany(Long id, Company company) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        if(companyOptional.isPresent()){
           Company company1= companyOptional.get();
           company1.setDescription(company.getDescription());
           company1.setName(company.getName());
           company1.setJobs(company.getJobs());
           companyRepository.save(company1);
           return true;
        }
        return false;
    }

    @Override
    public Company findCompanyById(Long id){
       return companyRepository.findById(id).orElse(null);
    }
}
