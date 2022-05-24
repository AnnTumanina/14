package com.company;

import java.io.*;
import java.util.Scanner;

class Function implements Serializable {
    double x, y;

    double getY() {
        y = x - Math.sin(x);
        return y;
    }
}

public class Main {
    public static void main(String[] args) {
        String txt;
        Scanner in = new Scanner(System.in);
        Function func = new Function(); //объект класса функтион
        System.out.print("Введите значение х: ");
        while (true) {
            txt = in.nextLine();
            try {
                double x = Double.parseDouble(txt);
                func.x = x;//x присваиваем значение строки double x = Double.parseDouble(txt);
                func.getY();
                System.out.println("Вы можете ввести команду: сохранить, загрузить, изменить, проверить, остановить");
            } catch (Exception IOe) {
                if (txt.equalsIgnoreCase("сохранить")) {
                    try (ObjectOutputStream wr = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
                        wr.writeObject(func);
                        System.out.println("Данные сохранены в файл");
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
                else if (txt.equalsIgnoreCase("загрузить")) {
                    try (ObjectInputStream rd = new ObjectInputStream(new FileInputStream("object.txt"))) {
                        func = (Function) rd.readObject();
                        System.out.println("Загрузка успешна");
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                }
                else if (txt.equalsIgnoreCase("изменить")) {
                    System.out.printf("Введите значение х: ");
                    txt = in.nextLine();
                    double x = Double.parseDouble(txt);
                    func.x = x;
                    func.getY();
                }
                else if (txt.equalsIgnoreCase("проверить")) {
                    System.out.printf("x: %s\ny: %s\n",func.x, func.y);
                }
                else if (txt.equalsIgnoreCase("остановить")){
                    break;
                }
                else {
                    System.out.println("Такой команды нет");
                }
                System.out.println("Вы можете ввести команду: сохранить, загрузить, изменить, проверить, остановить");
            }
        }
    }
}