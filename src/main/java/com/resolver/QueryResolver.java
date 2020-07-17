package com.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.model.Director;
import com.model.Movie;
import com.repository.DirectorRepository;
import com.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorRepository directorRepository;

    public Iterable<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Iterable<Director> findAllDirectors(Number limit) {
        Iterable<Director> directors = directorRepository.findAll();
        List<Director> result = new ArrayList<>();
        directors.forEach(result::add);
        if(limit == null || limit.intValue() > result.size()) {
            return result;
        }
        return result.subList(0,limit.intValue());
    }

    public List<Movie> findMoviesByTitle(String title) {
        Iterable<Movie> movies = findAllMovies();
        List<Movie> filteredMovies = new ArrayList<>();
        movies.forEach(movie -> {
            if(movie.getTitle().equals(title)) {
                filteredMovies.add(movie);
            }
        });
        return filteredMovies;
    }

    public long countMovies() {
        return movieRepository.count();
    }
    public long countDirectors() {
        return directorRepository.count();
    }
}
