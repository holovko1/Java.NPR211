package org.example;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DatabaseWorker {
    private String host = "ep-proud-feather-a5ayx291.us-east-2.aws.neon.tech";
    private String url = "jdbc:postgresql://"+host+":5432/neondb";
    private String user = "neondb_owner";
    private String password = "r2PKbS1IupFV";

    Connection connection = null;

    public DatabaseWorker() {
        // Initialize the connection
        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database!");

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
        }
    }

    public void createTables() {
        createContacts();
    }

    private void createContacts() {
        try {
            Path filePath = Path.of(Objects
                    .requireNonNull(getClass().getClassLoader().getResource("contacts_table.sql")).toURI());

            String sqlScript = Files.readString(filePath);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlScript);
            statement.close();
        } catch (IOException e) {
            // Handle potential I/O errors
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (URISyntaxException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error in reading file: " + e.getMessage());
        }
    }
}