import java.sql.*;
import java.util.*;

public class RoomAdmin extends Room {
    private Connection conn;

    public RoomAdmin(String roomName) {
        super(roomName);
        connectDatabase();
    }

    private void connectDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:roomdb.db");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "seat TEXT," +
                "allowed BOOLEAN DEFAULT 1," +
                "room TEXT NOT NULL)");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void addStudent(Student s) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, seat, allowed, room) VALUES (?, ?, ?, ?)");
            ps.setString(1, s.getName());
            ps.setString(2, s.getSeat());
            ps.setBoolean(3, s.isAllowed());
            ps.setString(4, s.getRoom());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add error: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }

    public void allowStudent(int id, boolean allow) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE students SET allowed = ? WHERE id = ?");
            ps.setBoolean(1, allow);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Allow error: " + e.getMessage());
        }
    }

    public List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE room = ?");
            ps.setString(1, getRoomName());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("seat"),
                    rs.getBoolean("allowed"),
                    rs.getString("room")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Fetch error: " + e.getMessage());
        }
        return list;
    }
}