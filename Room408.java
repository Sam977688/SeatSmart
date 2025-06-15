import java.io.FileWriter;
import java.io.IOException;

public class Room408 extends Reservation {

    public Room408(String email, String seatId) {
        super(email, seatId);
    }

    public String getEmail() {
        return super.email;
    }

    public String getSeatId() {
        return super.getSeatId();
    }

    public void saveToDatabase() {
        try (FileWriter writer = new FileWriter("database.txt", true)) {
            writer.write("Room 408 | " + getEmail() + " | " + getSeatId() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}