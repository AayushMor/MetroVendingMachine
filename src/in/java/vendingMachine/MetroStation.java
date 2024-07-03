package in.java.vendingMachine;

public class MetroStation {
    private String stationName;
    private String stationCode;

    public MetroStation(String stationName, String stationCode) {
        this.stationName = stationName;
        this.stationCode = stationCode;
    }

    // Getters and toString method
    public String getStationName() {
        return stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    @Override
    public String toString() {
        return stationName + " (" + stationCode + ")";
    }
}

