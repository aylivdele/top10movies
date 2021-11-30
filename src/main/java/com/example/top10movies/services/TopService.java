package com.example.top10movies.services;

import com.example.top10movies.entities.Movie;
import com.example.top10movies.reposetories.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopService {

    private MoviesRepository moviesRepository;

    public TopService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<Movie> getTopTen(LocalDateTime dateTime){
        LocalDateTime closestDateTime = getClosestDate(dateTime);
        if (closestDateTime == null)
            return new ArrayList<>();
        List<Movie> tempList = moviesRepository.findAllByDateOrderByPosition(closestDateTime);
        if (tempList.size() > 10)
            return tempList.subList(0,10);
        else
            return tempList.subList(0,tempList.size());
    }

    private LocalDateTime getClosestDate(LocalDateTime dateTime){
        List<Movie> tempMovies = moviesRepository.findDistinctByDateBeforeOrderByDate(dateTime);
        if (!tempMovies.isEmpty())
            return tempMovies.get(tempMovies.size()-1).getDate();
        else
            return null;
    }
}
