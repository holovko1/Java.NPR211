package org.example.util;

import lombok.Getter;
import org.example.entities.Cat;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    // Метод для отримання SessionFactory
    @Getter
    private static SessionFactory sessionFactory;

    static {
        try {
            var config = new Configuration()
//                    .configure("hibernate.cfg.xml")
                    .configure();
            config.addAnnotatedClass(Cat.class);
            sessionFactory = config.buildSessionFactory();

            System.out.println("------Підключення до БД успішно-----");
        } catch (Exception e) {
            System.out.println("Помилка зяднання з БД!");
        }
    }

    // Метод для отримання нової сесії
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    // Закриття фабрики сесій
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
