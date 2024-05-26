import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            registerPatient(request, response);
        } else if ("addHealthRecord".equals(action)) {
            addHealthRecord(request, response);
        } else if ("addPayment".equals(action)) {
            addPayment(request, response);
        }
    }

    private void registerPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO patients (name, age, gender, address, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setInt(2, age);
                statement.setString(3, gender);
                statement.setString(4, address);
                statement.setString(5, email);
                statement.setString(6, phoneNumber);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    response.getWriter().println("Patient registered successfully.");
                } else {
                    response.getWriter().println("Failed to register patient.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private void addHealthRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patient_id"));
        String purpose = request.getParameter("purpose");
        String operationPurpose = request.getParameter("operation_purpose");
        String operationDate = request.getParameter("operation_date");
        String operationTime = request.getParameter("operation_time");
        String responsibleDoctor = request.getParameter("responsible_doctor");
        String healthProblem = request.getParameter("health_problem");
        String checkupDate = request.getParameter("checkup_date");
        String checkupTime = request.getParameter("checkup_time");
        double fees = Double.parseDouble(request.getParameter("fees"));

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO health_records (patient_id, purpose, operation_purpose, operation_date, operation_time, responsible_doctor, health_problem, checkup_date, checkup_time, fees) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, patientId);
                statement.setString(2, purpose);
                statement.setString(3, operationPurpose);
                statement.setString(4, operationDate);
                statement.setString(5, operationTime);
                statement.setString(6, responsibleDoctor);
                statement.setString(7, healthProblem);
                statement.setString(8, checkupDate);
                statement.setString(9, checkupTime);
                statement.setDouble(10, fees);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    response.getWriter().println("Health record added successfully.");
                } else {
                    response.getWriter().println("Failed to add health record.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private void addPayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patient_id"));
        double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
        double amountPaid = Double.parseDouble(request.getParameter("amount_paid"));
        double amountDue = Double.parseDouble(request.getParameter("amount_due"));

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO payments (patient_id, total_amount, amount_paid, amount_due) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, patientId);
                statement.setDouble(2, totalAmount);
                statement.setDouble(3, amountPaid);
                statement.setDouble(4, amountDue);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    response.getWriter().println("Payment record added successfully.");
                } else {
                    response.getWriter().println("Failed to add payment record.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}