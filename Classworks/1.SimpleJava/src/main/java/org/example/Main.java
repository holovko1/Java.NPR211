package org.example;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());

        int[] mas = new int[n];
        for (int i = 0; i < mas.length; i++) {
            mas[i] = getRandom();
            System.out.print(mas[i] + " ");
        }
        System.out.println(" ");
    }

    public static int getRandom() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}