package in.java.vendingMachine;

import java.time.LocalDateTime;

public class Ticket {
    private static int idCounter = 0;
    private int ticketID;
    private Route route;
    private double fare;
    private String ticketType;
    private LocalDateTime purchaseTime;

    public Ticket(Route route, double fare, String ticketType) {
        this.ticketID = ++idCounter;
        this.route = route;
        this.fare = fare;
        this.ticketType = ticketType;
        this.purchaseTime = LocalDateTime.now();
    }

    // Getters and toString method
    public int getTicketID() {
        return ticketID;
    }

    public Route getRoute() {
        return route;
    }

    public double getFare() {
        return fare;
    }

    public String getTicketType() {
        return ticketType;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketID + ", Route: " + route.getRouteName() +
                ", Ticket Type: " + ticketType + ", Fare: $" + fare + ", Purchase Time: " + purchaseTime;
    }
}
