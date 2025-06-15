public class Reservation {
    private String email;
    private String seatId;

    public Reservation(String email, String seatId) {
        this.email = email;
        this.seatId = seatId;
    }

    public String getEmail() {
        return email;
    }

    public String getSeatId() {
        return seatId;
    }
}