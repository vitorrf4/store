package com.store.controllers;

import com.store.models.MovieCopy;
import com.store.services.MoviesAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SearchMovieController {
    private final MoviesAPIService apiService;

    @Autowired
    public SearchMovieController(MoviesAPIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/movies/search/{query}")
    public ResponseEntity<List<MovieCopy>> searchMovie(@PathVariable String query) {
        List<MovieCopy> responseMovies = apiService.searchMovie(query);

        if (responseMovies == null) return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(responseMovies);
    }
}
