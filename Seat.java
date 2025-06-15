public class Seat {
    private String name;
    private String seatNumber;
    private boolean allowed;

    public Seat(String name, String seatNumber, boolean allowed) {
        this.name = name;
        this.seatNumber = seatNumber;
        this.allowed = allowed;
    }

    public String getName() {
        return name;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isAllowed() {
        return allowed;
    }
}