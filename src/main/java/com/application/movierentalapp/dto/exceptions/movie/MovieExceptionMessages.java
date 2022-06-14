package com.application.movierentalapp.dto.exceptions.movie;

public class MovieExceptionMessages {
    public static final String MOVIE_ALREADY_RENTED = "Movie already rented!";
    public static final String MOVIE_ALREADY_RELEASED = "Movie already released!";
    public static final String MOVIE_NOT_FOUND = "Movie not found!";

    public static final String MOVIE_NAME_TOO_BIG = "Movie name exceeds the limit of 200 characters! Please provide a valid movie name.";
    public static final String BAD_PRICE_FOR_MOVIE_LESS_THAN_2010 = "Movie with release year less than 2010 cannot have a price bigger than 5 euros!";
    public static final String BAD_PRICE_FOR_MOVIE_BETWEEN_2010_AND_2020 = "Movie with release year between 2010 and 2020 cannot have a price bigger than 10 euros!";
    public static final String BAD_PRICE_FOR_MOVIE_BIGGER_THAN_2020 = "Movie with release year bigger than 2020 cannot have a price lower than 13 euros!";
}
