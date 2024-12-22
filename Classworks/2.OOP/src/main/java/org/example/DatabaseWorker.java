package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class DatabaseWorker {

    Connection connection = null;

    public DatabaseWorker() {
        // Initialize the connection
        try {
            ConnectDatabase();
            if (connection != null) {
                System.out.println("Connected to the database!");

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (Exception e) {
            System.out.println("Connection failure.");
        }
    }

    public void ConnectDatabase() {
        try {
            Path DBPath = Path.of(Objects
                    .requireNonNull(getClass().getClassLoader().getResource("option.json")).toURI());
            JsonNode root = new ObjectMapper().readTree(DBPath.toFile());

            String host = root.get("host").asText();
            String username = root.get("username").asText();
            String password = root.get("password").asText();
            String namedb = root.get("namedb").asText();

            String url = "jdbc:postgresql://"+host+":5432/"+namedb;

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    /*public void createContact(String firstName, String lastName, String email, String phone) {
        String time = String.valueOf(LocalDateTime.now());
        String request = "INSERT INTO Contacts (FirstName, LastName, Email, PhoneNumber) VALUES ("+firstName+","+lastName+","+email+","+phone+","+time+")";
        try {
            PreparedStatement statement = connection.prepareStatement(request);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}