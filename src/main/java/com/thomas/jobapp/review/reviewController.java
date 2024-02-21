package com.thomas.jobapp.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/{companyId}")
public class reviewController {
    @Autowired
    private reviewService reviewservice;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewservice.getAllReviews(companyId),HttpStatus.OK);
    }


    @PostMapping("/reviews")
    public ResponseEntity<String> CreateReview(@PathVariable Long companyId,@RequestBody Review review){
        boolean flag=reviewservice.createReview(companyId,review);
        if(flag){
            return new ResponseEntity<>("Review saved",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company NotFound",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewservice.getReview(companyId,reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review review){
        boolean isUpdated= reviewservice.updateReview(companyId,reviewId,review);
        if (isUpdated) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean isDeleted=reviewservice.deleteReview(companyId,reviewId);
        if (isDeleted) {
            return new ResponseEntity<>("Review Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not Deleted", HttpStatus.NOT_FOUND);
    }

}
