import java.sql.*;

public class DatabaseApplication {
  
  public static void main(String[] args) {
    String user = "fabio";
    String password = "my_password";
    String database = "lab3";
    String table = "customers";
    String url = "jdbc:mysql://localhost/" + database + "?serverTimezone=UTC";
    
    try {
      // Connect using the application user created above
      Connection connection = DriverManager.getConnection(url, user, password);
      
      // Insert into the table
      String insertSql = "INSERT INTO " + table + " (name, email) VALUES (?, ?)";
      PreparedStatement insertStmt = connection.prepareStatement(insertSql);
      insertStmt.setString(1, "John");
      insertStmt.setString(2, "john@example.com");
      insertStmt.executeUpdate();
      
      // Select from the table using a WHERE clause with parameterized fields
      String selectSql = "SELECT * FROM " + table + " WHERE name = ? AND email = ?";
      PreparedStatement selectStmt = connection.prepareStatement(selectSql);
      selectStmt.setString(1, "John");
      selectStmt.setString(2, "john@example.com");
      ResultSet resultSet = selectStmt.executeQuery();
      
      // Display results of the select
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        System.out.println("id: " + id + ", name: " + name + ", email: " + email);
      }
      
      // Close resources
      resultSet.close();
      selectStmt.close();
      insertStmt.close();
      connection.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
