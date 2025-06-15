import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/seats")
public class SeatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String room = request.getParameter("room");
        List<Seat> seats = SeatService.getSeatsByRoom(room);

        String json = new Gson().toJson(seats);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}