package com.thomas.jobapp.review.Impl;

import com.thomas.jobapp.company.Company;
import com.thomas.jobapp.company.CompanyService;
import com.thomas.jobapp.review.Review;
import com.thomas.jobapp.review.reviewRepository;
import com.thomas.jobapp.review.reviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class reviewServiceImpl implements reviewService {

    @Autowired
    private reviewRepository reviewRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }


    @Override
    public Boolean createReview(Long companyId, Review review) {
        Company company=companyService.findCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.findCompanyById(companyId)!=null){
            updatedReview.setCompany((companyService.findCompanyById(companyId)));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.findCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
            Review review=reviewRepository.findById(reviewId).orElse(null);
            Company company=review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(companyId,company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }


}
