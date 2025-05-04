package cinema;

import cinema.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceTest {
    private InMemoryTicketRepository repository;
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryTicketRepository();
        repository.addMovie(new Movie("فیلم ۱", 10, 50000));
        repository.addMovie(new Movie("فیلم ۲", 5, 70000));
        ticketService = new TicketService(repository);
    }

    @Test
    public void testReserveTickets_Success() {
        String result = ticketService.reserveTickets(1, 3);
        assertEquals("رزرو موفقیت‌آمیز! مبلغ کل: 150000 تومان", result);
        assertEquals(7, repository.getMovieById(1).getAvailableTickets());
    }

    @Test
    public void testReserveTickets_NotEnoughTickets() {
        String result = ticketService.reserveTickets(2, 10);
        assertEquals("تعداد بلیط درخواستی بیشتر از موجودی است!", result);
        assertEquals(5, repository.getMovieById(2).getAvailableTickets());
    }

    @Test
    public void testReserveTickets_MovieNotFound() {
        String result = ticketService.reserveTickets(99, 1);
        assertEquals("فیلم مورد نظر یافت نشد!", result);
    }
}