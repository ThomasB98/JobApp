package com.thomas.jobapp.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanController {

    @Autowired
    private CompanyService companyService;
    @GetMapping
    public ResponseEntity<List<Company>> findAll(){
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id){
        return new ResponseEntity<>(companyService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
        companyService.updateCompany(id,company);
        return new ResponseEntity<>("company updated successfully",HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("company added",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        Boolean isDeleted=companyService.deleteCompanyById(id);
        if(isDeleted){
            return new ResponseEntity<>("Company Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Company ID",HttpStatus.NOT_FOUND);
    }

}
