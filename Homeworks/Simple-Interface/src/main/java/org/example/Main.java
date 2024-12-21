package org.example;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseWorker dw = new DatabaseWorker();
        dw.createTables();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter action : ");
        int option = Integer.parseInt(in.nextLine());
        if (option == 1) {
            System.out.println("Enter first name : ");
            String firstName = in.nextLine();
            System.out.println("Enter last name : ");
            String lastName = in.nextLine();
            System.out.println("Enter email : ");
            String email = in.nextLine();
            System.out.println("Enter phone : ");
            String phone = in.nextLine();

            dw.createContact(firstName, lastName, email, phone);
        } else if (option == 2) {

        } else {
            System.out.println("Enter 1 or 2");
        }
    }
}