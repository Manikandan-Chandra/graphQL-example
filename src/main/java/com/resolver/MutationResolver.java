package com.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.model.Director;
import com.model.Movie;
import com.repository.DirectorRepository;
import com.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MutationResolver implements GraphQLMutationResolver {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorRepository directorRepository;

    public Director newDirector(String firstName, String lastName) {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setLastName(lastName);

        directorRepository.save(director);

        return director;
    }

    public Movie newMovie(String title, String language, double duration, double budget, Long directorId) {
        Movie movie = new Movie();
        movie.setDirector(new Director(directorId));
        movie.setTitle(title);
        movie.setLanguage(language);
        movie.setDuration(duration);
        movie.setBudget(budget);

        movieRepository.save(movie);

        return movie;
    }

    public boolean deleteMovie(Long id) {
        movieRepository.deleteById(id);
        return true;
    }

    public Movie updateMovieDuration(double duration, Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.get() == null) {
            throw new IllegalArgumentException("The movie to be updated was not found");
        }
        movie.get().setDuration(duration);

        movieRepository.save(movie.get());

        return movie.get();
    }
}
