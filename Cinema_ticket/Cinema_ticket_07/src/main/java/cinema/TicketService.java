package cinema;

import cinema.models.Movie;
import java.util.Map;

public class TicketService {
    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public String reserveTickets(int movieId, int quantity) {
        Movie movie = repository.getMovieById(movieId);

        if (movie == null) {
            return "فیلم مورد نظر یافت نشد!";
        }

        if (movie.getAvailableTickets() < quantity) {
            return "تعداد بلیط درخواستی بیشتر از موجودی است!";
        }

        movie.setAvailableTickets(movie.getAvailableTickets() - quantity);
        repository.updateMovie(movieId, movie);

        int totalPrice = movie.getPrice() * quantity;
        return String.format("رزرو موفقیت‌آمیز! مبلغ کل: %d تومان", totalPrice);
    }

    public void displayMovies() {
        Map<Integer, Movie> movies = repository.getAllMovies();
        System.out.println("\nلیست فیلم‌های موجود:");
        System.out.println("-----------------------------");
        System.out.printf("%-5s %-20s %-10s %s%n", "ID", "movie name", "price", "number of tickets");
        System.out.println("-----------------------------");

        for (Map.Entry<Integer, Movie> entry : movies.entrySet()) {
            Movie movie = entry.getValue();
            System.out.printf("%-5d %-20s %-10d %d%n",
                    entry.getKey(),
                    movie.getTitle(),
                    movie.getPrice(),
                    movie.getAvailableTickets());
        }
        System.out.println("-----------------------------\n");
    }
}