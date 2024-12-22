package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.entities.Cat;
import org.example.util.HibernateUtil;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var session = HibernateUtil.getSession();
        var cat = new Cat();
        List<Cat> cats = session.createQuery("FROM Cat", Cat.class).list();

        Scanner in = new Scanner(System.in);

        System.out.println("Вкажіть дію\n1.Додати котика\n2.Редагувати котика\n3.Вивести список котиків\n4.Видалити котика");
        int option = Integer.parseInt(in.nextLine());

        if (option == 1) {
            try {
                session.beginTransaction(); //Початок транзакції

                System.out.println("Вкажіть ім'я"); cat.setName(in.nextLine());
                System.out.println("Вкажіть вік"); cat.setAge(Integer.parseInt(in.nextLine()));
                System.out.println("Вкажіть колір"); cat.setColor(in.nextLine());
                session.persist(cat);

                session.getTransaction().commit(); //Кінець транзакції

                System.out.println("Збережено " + cat);
            } catch (Exception e) {
                System.out.println("Проблема при роботі із бд " + e.getMessage());
            }
        } else if (option == 2) {
            System.out.println("Вкажіть Id котика, якого хочете змінити");
            int redactId = Integer.parseInt(in.nextLine());

            session.beginTransaction();

            for(Cat i : cats) {
                if (redactId == i.getId()) {
                    System.out.println("Вкажіть нове ім'я"); i.setName(in.nextLine());
                    System.out.println("Вкажіть новий вік"); i.setAge(Integer.parseInt(in.nextLine()));
                    System.out.println("Вкажіть новий колір"); i.setColor(in.nextLine());
                    session.persist(i);
                    System.out.println("Котик успішно відредагоаний");
                    break;
                }
            }

            session.getTransaction().commit();
        } else if (option == 3) {
            session.beginTransaction();

            for (Cat i : cats) {
                System.out.println(i);
            }

            session.getTransaction().commit();
        } else if (option == 4) {
            System.out.println("Вкажіть Id котика, якого хочете видалити");
            int redactId = Integer.parseInt(in.nextLine());

            session.beginTransaction();
            
            for(Cat i : cats) {
                if (redactId == i.getId()) {
                    session.remove(i);
                    System.out.println("Котик успішно видалений");
                    break;
                }
            }

            session.getTransaction().commit();
        } else {
            System.out.println("Вкажіть число від 1 до 4");
        }

        HibernateUtil.shutdown();
    }
}