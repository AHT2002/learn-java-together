package cinema;

import cinema.models.Movie;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTicketRepository implements TicketRepository {
    private final Map<Integer, Movie> movies = new HashMap<>();
    private int nextId = 1;

    @Override
    public Map<Integer, Movie> getAllMovies() {
        return new HashMap<>(movies);
    }

    @Override
    public Movie getMovieById(int id) {
        return movies.get(id);
    }

    @Override
    public void updateMovie(int id, Movie movie) {
        movies.put(id, movie);
    }

    public void addMovie(Movie movie) {
        movies.put(nextId++, movie);
    }
}