package cinema.models;

public class Movie {
    private final String title;
    private int availableTickets;
    private final int price;

    public Movie(String title, int availableTickets, int price) {
        this.title = title;
        this.availableTickets = availableTickets;
        this.price = price;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public int getPrice() {
        return price;
    }
}