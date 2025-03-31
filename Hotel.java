package Project.sql_conection;

import java.sql.SQLException;
import java.util.Scanner;

public class Hotel {
    Scanner sc = new Scanner(System.in);
    Scanner scId = new Scanner(System.in);
    Scanner scNomer = new Scanner(System.in);
    Scanner scName = new Scanner(System.in);
    Scanner scBrone = new Scanner(System.in);


    private int value;

    public Hotel() throws SQLException, ClassNotFoundException {
        start();
    }


    int start() throws SQLException, ClassNotFoundException {
        DataBase db = new DataBase();
        db.isConnection();
        db.createDb("hotel");
        while(true){

            System.out.println("***Инструкция***");
            System.out.println("1. Добавить номер");
            System.out.println("2. Получить список всех броней");
            System.out.println("3. Вывести все не забронированные номера");
            System.out.println("4. Закрыть приложение");

            value = sc.nextInt();


            if (value == 1) {
                System.out.println("Напишите значение ID: ");
                int id = scId.nextInt();
                System.out.println("Напишите номер: ");
                int nomer = scNomer.nextInt();
                System.out.println("Напишите имя бронирующего: ");
                String name = scName.nextLine();
                System.out.println("Забронирован или нет ");
                boolean bron = scBrone.nextBoolean();
                db.addHotel("hotel", id, nomer, name, bron);
            }

            if (value == 2) {
                System.out.println("Все брони");
                db.selectBroned("hotel");

            }
            if (value == 3) {
                System.out.println("Все не забронированные номера");
                db.selectnoBroned("hotel");
            }

            if (value == 4) {
                return 1;
            }
        }
    }


}
