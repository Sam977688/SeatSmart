import java.io.FileWriter;
import java.io.IOException;

public class Room501 {
    private String email;
    private String seatId;

    public Room501(String email, String seatId) {
        this.email = email;
        this.seatId = seatId;
    }

    public String getEmail() {
        return email;
    }

    public String getSeatId() {
        return seatId;
    }

    public void saveToDatabase() {
        try (FileWriter writer = new FileWriter("database.txt", true)) {
            writer.write("Room 501 | " + getEmail() + " | " + getSeatId() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}