import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      String email = request.getParameter("email").toLowerCase();
      String password = request.getParameter("password");

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      try {
          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          String url = "jdbc:sqlserver://localhost:1433;databaseName=student_reservation";
          String dbUser = "your_db_username";
          String dbPass = "your_db_password";

          Connection conn = DriverManager.getConnection(url, dbUser, dbPass);

          String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1, email);
          stmt.setString(2, password);
          ResultSet rs = stmt.executeQuery();

          if (rs.next()) {
              String role = rs.getString("role"); 
              if ("student".equalsIgnoreCase(role)) {
                  response.sendRedirect("dashboard.html");
              } else if ("professor".equalsIgnoreCase(role)) {
                  response.sendRedirect("dashboardprof.html");
              } else {
                  out.println("Invalid role");
              }
          } else {
              out.println("Invalid email or password");
          }

          conn.close();
      } catch (Exception e) {
          out.println("Database error: " + e.getMessage());
      }
  }
}