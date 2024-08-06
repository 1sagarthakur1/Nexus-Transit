package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.Rating;

    public interface RatingRepo extends JpaRepository<Rating, Integer> {

}
