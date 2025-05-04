package cinema;

import cinema.models.Movie;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("سیستم رزرو بلیط سینما");
        System.out.println("----------------------");
        System.out.println("حالت اجرا را انتخاب کنید:");
        System.out.println("1. حالت حافظه داخلی (بدون فایل/دیتابیس)");
        System.out.println("2. حالت فایل (در آینده اضافه خواهد شد)");
        System.out.println("3. حالت دیتابیس (در آینده اضافه خواهد شد)");
        System.out.print("انتخاب شما: ");

        int mode = scanner.nextInt();
        scanner.nextLine(); // مصرف newline

        TicketRepository repository;

        switch (mode) {
            case 1:
                repository = new InMemoryTicketRepository();
                break;
            case 2:
            case 3:
                System.out.println("این حالت در حال حاضر پشتیبانی نمی‌شود.");
                return;
            default:
                System.out.println("گزینه نامعتبر!");
                return;
        }

        // ورود اطلاعات فیلم‌ها
        System.out.println("\nورود اطلاعات فیلم‌ها (برای پایان دادن، عنوان را خالی بگذارید)");

        while (true) {
            System.out.print("عنوان فیلم: ");
            String title = scanner.nextLine();

            if (title.isEmpty()) {
                break;
            }

            System.out.print("تعداد بلیط‌های موجود: ");
            int availableTickets = scanner.nextInt();

            System.out.print("قیمت هر بلیط (تومان): ");
            int price = scanner.nextInt();
            scanner.nextLine(); // مصرف newline

            ((InMemoryTicketRepository)repository).addMovie(new Movie(title, availableTickets, price));
        }

        TicketService ticketService = new TicketService(repository);

        // منوی اصلی
        while (true) {
            ticketService.displayMovies();

            System.out.println("1. رزرو بلیط");
            System.out.println("2. خروج");
            System.out.print("انتخاب شما: ");

            int choice = scanner.nextInt();

            if (choice == 2) {
                break;
            }

            if (choice == 1) {
                System.out.print("شناسه فیلم مورد نظر: ");
                int movieId = scanner.nextInt();

                System.out.print("تعداد بلیط: ");
                int quantity = scanner.nextInt();

                String result = ticketService.reserveTickets(movieId, quantity);
                System.out.println("\n" + result + "\n");
            }
        }

        System.out.println("با تشکر از استفاده شما!");
        scanner.close();
    }
}