package com.model;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="language", nullable = true)
    private String language;

    @Column(name="duration", nullable = true)
    private double duration;

    @Column(name="budget", nullable = true)
    private double budget;

    @ManyToOne
    @JoinColumn(name = "director_id",
            nullable = false, updatable = false)
    private Director director;

    public Movie() {
    }

    public Movie(String title, String language, double duration, double budget, Director director) {
        this.title = title;
        this.language = language;
        this.duration = duration;
        this.budget = budget;
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language=" + language +
                ", director=" + director +
                '}';
    }
}
