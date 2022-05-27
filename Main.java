package com.company;

import java.io.*;
import java.util.Scanner;

class Function implements Serializable {
    double x, y;

    double ccc() {
        y = x - Math.sin(x);
        return y;
    }
}

public class Main {
    public static void main(String[] args) {
        String ttt;
        Scanner in = new Scanner(System.in);
        Function fff = new Function();
        System.out.print("Число х: ");
        while (true) {
            ttt = in.nextLine();
            try {
                double x = Double.parseDouble(ttt);
                fff.x = x;
                fff.ccc();
                System.out.println("Введите команду: сохранить, показать результаты, изменить значение, загрузить сохранённые данные, завершить");
            } catch (Exception IOe) {
                if (ttt.equalsIgnoreCase("сохранить")) {
                    try (ObjectOutputStream wr = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
                        wr.writeObject(fff);
                        System.out.println("Сохранено");
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
                else if (ttt.equalsIgnoreCase("показать результаты")) {
                    System.out.printf("Результаты \nx: %s\ny: %s\n",fff.x, fff.y);
                }
                else if (ttt.equalsIgnoreCase("изменить значение")) {
                    System.out.printf("Число х: ");
                    try {
                        ttt = in.nextLine();
                        double x = Double.parseDouble(ttt);
                        fff.x = x;
                        fff.ccc();
                    }
                    catch (Exception IOee) {
                        System.out.println("Нераспознанная команда");
                    }
                }
                else if (ttt.equalsIgnoreCase("загрузить сохранённые данные")) {
                    try (ObjectInputStream rd = new ObjectInputStream(new FileInputStream("object.txt"))) {
                        fff = (Function) rd.readObject();
                        System.out.println("Данные восстановленны");
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                }
                else if (ttt.equalsIgnoreCase("завершить")){
                    break;
                }
                else {
                    System.out.println("Нераспознанная команда");
                }
                System.out.println("Введите команду: сохранить, показать результаты, изменить значение, загрузить сохранённые данные, завершить");
            }
        }
    }
}
