package org.example;

import java.awt.*;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Math test");
        Scanner in = new Scanner(System.in);
        int a, b, c = 0;
        int counter = 0;
        int j = getRandomCount();
        for (int i = 0; i < j; i++) {
            a = getRandomNumber(); b = getRandomNumber();
            System.out.print(a + "*" + b + " = ");
            c = Integer.parseInt(in.nextLine());
            if (a * b == c) {
                System.out.println("Correct");
                counter += 1;
            }
            else {
                System.out.println("Incorrect");
            }
        }
        System.out.println("Test ended.\nYour grade : " + Math.round(((double)counter / j) * 12));
    }

    //Кількість тестових завдань
    public static int getRandomCount() {
        Random random = new Random();
        return random.nextInt(6) + 10;
    }

    //Рандомайзер чисел від 1 до 10
    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}