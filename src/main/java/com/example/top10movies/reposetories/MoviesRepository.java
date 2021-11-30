package com.example.top10movies.reposetories;


import com.example.top10movies.entities.Movie;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findDistinctByDateBeforeOrderByDate(LocalDateTime dateTime);

    @Cacheable(cacheNames = "MoviesCache", key = "#dateTime")
    List<Movie> findAllByDateOrderByPosition(LocalDateTime dateTime);

}
