import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Reservation {
    protected String email;
    protected String room;
    protected String seat;

    public Reservation(String email, String room, String seat) {
        this.email = email;
        this.room = room;
        this.seat = seat;
    }

    public String getFormatted() {
        return email + " | " + room + " | " + seat;
    }
}

class StudentReservation extends Reservation {
    public StudentReservation(String email, String room, String seat) {
        super(email, room, seat);
    }
}

class ReservationSystem {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation r) {
        reservations.add(r);
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            for (Reservation r : reservations) {
                writer.write(r.getFormatted() + "\n");
            }
            System.out.println("Reservations saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        // Simulate two student reservations
        StudentReservation res1 = new StudentReservation("student@example.com", "408", "");
        StudentReservation res2 = new StudentReservation("student@example.com", "501", "");

        system.addReservation(res1);
        system.addReservation(res2);

        system.saveToFile("reservations.txt");
    }
}