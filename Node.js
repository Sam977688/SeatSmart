const express = require('express');
const sql = require('mssql');
const cors = require('cors');
const bodyParser = require('body-parser');
const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.json());

const config = {
  user: 'your_sql_username',
  password: 'your_password',
  server: 'localhost',
  database: 'StudentReservationDB',
  options: {
    encrypt: false,
    trustServerCertificate: true,
  },
};

// Connect once
sql.connect(config).then(pool => {
  console.log('Connected to MSSQL');

  // GET all reserved seats in Room 408
  app.get('/seats', async (req, res) => {
    try {
      const result = await pool.request()
        .query("SELECT SeatCode, IsReserved FROM Seats WHERE RoomNumber = '408'");
      res.json(result.recordset);
    } catch (err) {
      console.error(err);
      res.status(500).send('Error fetching seats');
    }
  });

  // POST reserve a seat
  app.post('/reserve', async (req, res) => {
    const { seatCode, studentId } = req.body;
    try {
      const result = await pool.request()
        .input('seatCode', sql.NVarChar, seatCode)
        .input('studentId', sql.Int, studentId)
        .query(`
          UPDATE Seats
          SET IsReserved = 1, ReservedByStudentId = @studentId
          WHERE SeatCode = @seatCode AND RoomNumber = '408'
        `);
      res.send({ success: true });
    } catch (err) {
      console.error(err);
      res.status(500).send('Error reserving seat');
    }
  });

}).catch(err => console.error('DB Connection Error:', err));

app.listen(PORT, () => console.log(`Server running on http://localhost:${PORT}`));