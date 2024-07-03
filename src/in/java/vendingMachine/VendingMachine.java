package in.java.vendingMachine;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VendingMachine {
    private List<Route> routes;
    private List<Ticket> purchasedTickets;

    public VendingMachine() {
        routes = new ArrayList<>();
        purchasedTickets = new ArrayList<>();
        initializeRoutes();
    }

    private void initializeRoutes() {
        // Predefined routes and stations
        Route route1 = new Route("Route 1", List.of(
                new MetroStation("Station A", "A"),
                new MetroStation("Station B", "B"),
                new MetroStation("Station C", "C")
        ));
        Route route2 = new Route("Route 2", List.of(
                new MetroStation("Station D", "D"),
                new MetroStation("Station E", "E"),
                new MetroStation("Station F", "F"),
                new MetroStation("Station G", "G")
        ));
        routes.add(route1);
        routes.add(route2);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Metro Ticket Vending Machine!");
            System.out.println("Please select an option:");
            System.out.println("1. View All Routes");
            System.out.println("2. Purchase Ticket");
            System.out.println("3. View Purchased Tickets");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllRoutes();
                    break;
                case 2:
                    purchaseTicket(scanner);
                    break;
                case 3:
                    viewPurchasedTickets();
                    break;
                case 4:
                    System.out.println("Thank you for using the Metro Ticket Vending Machine! Goodbye.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllRoutes() {
        System.out.println("Available Routes:");
        routes.forEach(route -> System.out.println(route.getRouteName() + ": " + 
            route.getStations().stream()
                .map(MetroStation::getStationName)
                .collect(Collectors.joining(" -> "))));
        System.out.println("Press Enter to return to the main menu.");
        new Scanner(System.in).nextLine();
    }

    private void purchaseTicket(Scanner scanner) {
        System.out.print("Enter the route name for which you want to purchase a ticket: ");
        String routeName = scanner.nextLine();
        Optional<Route> selectedRoute = routes.stream()
                .filter(route -> route.getRouteName().equalsIgnoreCase(routeName))
                .findFirst();

        if (!selectedRoute.isPresent()) {
            System.out.println("Invalid route. Please try again.");
            return;
        }

        System.out.println("Select ticket type:");
        System.out.println("1. Single Ride");
        System.out.println("2. Return");
        System.out.println("3. Weekly Pass");
        System.out.println("4. Monthly Pass");
        System.out.print("Enter your choice: ");
        int ticketTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String ticketType;
        switch (ticketTypeChoice) {
            case 1:
                ticketType = "Single Ride";
                break;
            case 2:
                ticketType = "Return";
                break;
            case 3:
                ticketType = "Weekly Pass";
                break;
            case 4:
                ticketType = "Monthly Pass";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        System.out.print("Is it peak hours? (yes/no): ");
        boolean isPeak = scanner.nextLine().equalsIgnoreCase("yes");

        double fare = calculateFare(ticketType, isPeak);

        System.out.printf("The fare for a %s ticket on %s during %s hours is $%.2f.%n",
                ticketType, routeName, isPeak ? "peak" : "off-peak", fare);
        System.out.print("Do you want to purchase this ticket? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            Ticket ticket = new Ticket(selectedRoute.get(), fare, ticketType);
            purchasedTickets.add(ticket);
            System.out.println("Ticket purchased successfully!");
            System.out.println(ticket);
        } else {
            System.out.println("Ticket purchase canceled.");
        }
        System.out.println("Press Enter to return to the main menu.");
        scanner.nextLine();
    }

    private double calculateFare(String ticketType, boolean isPeak) {
        double baseFare;
        switch (ticketType) {
            case "Single Ride":
                baseFare = 3.00;
                break;
            case "Return":
                baseFare = 5.00;
                break;
            case "Weekly Pass":
                baseFare = 20.00;
                break;
            case "Monthly Pass":
                baseFare = 50.00;
                break;
            default:
                throw new IllegalArgumentException("Invalid ticket type");
        }

        if (isPeak) {
            baseFare *= 1.2; // 20% increase during peak hours
        }

        if (ticketType.equals("Weekly Pass")) {
            baseFare *= 0.9; // 10% discount for weekly pass
        } else if (ticketType.equals("Monthly Pass")) {
            baseFare *= 0.8; // 20% discount for monthly pass
        }

        return baseFare;
    }

    private void viewPurchasedTickets() {
        if (purchasedTickets.isEmpty()) {
            System.out.println("No tickets purchased yet.");
        } else {
            System.out.println("Purchased Tickets:");
            purchasedTickets.forEach(System.out::println);
        }
        System.out.println("Press Enter to return to the main menu.");
        new Scanner(System.in).nextLine();
    }

    public static void main(String[] args) {
        new VendingMachine().start();
    }
}
