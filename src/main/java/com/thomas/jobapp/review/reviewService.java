package com.thomas.jobapp.review;

import java.util.List;

public interface reviewService {

    List<Review> getAllReviews(Long companyId);

    Review getReview(Long companyId,Long reviewId);
    Boolean createReview(Long companyId,Review review);

    Boolean updateReview(Long companyId,Long reviewId,Review review);

    Boolean deleteReview(Long companyId, Long reviewId);

}
