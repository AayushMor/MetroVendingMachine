package in.java.vendingMachine;

import java.util.List;

public class Route {
    private String routeName;
    private List<MetroStation> stations;

    public Route(String routeName, List<MetroStation> stations) {
        this.routeName = routeName;
        this.stations = stations;
    }

    // Getters and toString method
    public String getRouteName() {
        return routeName;
    }

    public List<MetroStation> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return routeName + ": " + stations;
    }
}
