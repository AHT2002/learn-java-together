package cinema;

import cinema.models.Movie;
import java.util.Map;

public interface TicketRepository {
    Map<Integer, Movie> getAllMovies();
    Movie getMovieById(int id);
    void updateMovie(int id, Movie movie);
}