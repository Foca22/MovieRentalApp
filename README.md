### Movie Rental App

### Pre-requisites

* Java 17
* Maven
* an SQL database of your choice (MySQL, Oracle, PostgreSQL etc)
* an IDE of your choice (IntellijIDEA, Eclipse etc)

### Requirements

You must implement the back end system of an application used for movie rentals. The application will store the data in an SQL database of your choice.

The application needs to provide implementation for the following use cases:

1. Add a movie – allows a user to add a new movie in the database. The following business rules are defined for this use case:
* The movies are stored in a table in the database
* The following movie genres are supported: COMEDY, DRAMA, THRILLER
* If the movie was released before 2010, the price cannot be more than 5 EUR to rent.
* If the movie was released between 2010 and 2020 (interval ends included), the price cannot be more than 10 EUR to rent.
* If the movie was released after 2020 the price to rent cannot be less than 13 EUR.

* The following table contains the list of attributes of a Movie, along with the validations that need to be implemented for each attribute:
 
 Attribute name | Validation
------------- | -----------
 id           | integer positive value, primary key, generated automatically in the database
 name         | text, with maximum length of 200 characters
 release_year | positive integer (plausible year value)
 price        | real positive value
 genre        | can have only one of these values: COMEDY, DRAMA, THRILLER
 rented       | boolean flag that marsks rental

2. Update the price of a movie - allows a user to update the price of a movie stored in the database. The user must specify the id of the movie they want to update. 

3. Retrieve the list of all movies - allows the user to retrieve the list of all movies details from the database. The movies should be sorted by price.

4. Retrieve the list of all movie genres and the number of movies for each genre from the database. eg

        COMEDY : 1,<br> 
        DRAMA : 4, <br>
        THRILLER : 0 <br>

5. Rent and release a movie - The user should be able to rent a movie.  The following business rules are defined for this use case:
* The user must specify the id of the movie to rent, and that movie should be marked in the database as 'rented'. 
* The rented movie should also be able to be released using the id of the movie. 
* Once rented, the movie cannot be rented again before being released. 
* Only rented movies can be released.

6. List all the movies rented - allows the user to retrieve a list of all the movies marked as 'rented'.

Unit tests should be implemented, with a coverage of at least 70% per line.

### Notes
The application may be implemented as any of these options:
* a console application, using only Java SE
* a REST API exposing endpoints for each use case, using Spring / Spring Boot


