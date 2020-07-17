package com.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.model.Director;
import com.model.Movie;
import com.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieResolver implements GraphQLResolver<Movie> {
    @Autowired
    private DirectorRepository directorRepository;

    public MovieResolver(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Director getDirector(Movie movie) {
        return directorRepository.findById(movie.getDirector().getId()).get();
    }
}
