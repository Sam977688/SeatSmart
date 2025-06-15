import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatService {

    public static List<Seat> getSeatsByRoom(String roomNumber) {
        List<Seat> seats = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost;databaseName=StudentReservationDB;integratedSecurity=true");
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT Name, SeatNumber, Allowed FROM Students WHERE RoomNumber = ?")) {

            stmt.setString(1, roomNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                seats.add(new Seat(
                        rs.getString("Name"),
                        rs.getString("SeatNumber"),
                        rs.getBoolean("Allowed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }
}