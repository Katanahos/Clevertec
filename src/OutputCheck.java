import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OutputCheck {
    /** вызов метода в зависимости от места вывода данных*/
    public static void checkInConsole(ArrayList<String> array) //вывод итогового чека в консоль
    {
        ArrayList<String> hat = checkTitle();
        for (String printHat : hat)
            System.out.println(printHat);
        for (String text: array){
            System.out.println(text);
        }
    }

    public static void checkInFile(ArrayList<String> array, String outputData) // запись итогового чека в файл
    {
        BufferedWriter writer = null;
            try {
                File file = new File(outputData);
                writer = new BufferedWriter(new FileWriter(file));
            } catch (IOException e) {
                System.out.println("Ошибка ввода/файл не найден");
            }

        ArrayList<String> hat = checkTitle();
        try {
            for (String str : hat) {
                writer.write(str);
                writer.write("\n");
            }
            for (String str : array) {
                writer.write(str);
                writer.write("\n");
            }

            System.out.println("Чек успешно записан в файл");
            writer.close();
        }catch (IOException e){
            System.out.println("Ошибка записи в файл");
        }

    }

    public static ArrayList<String> checkTitle() // создание "шапки" чека
    {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        ArrayList<String> hat = new ArrayList<>();
        hat.add("");
        hat.add("          CASH RECEIPT          ");
        hat.add("        SUPERMARKET HOME        ");
        hat.add("");
        hat.add("Cashier №1          DATE "+df.format(date));
        hat.add("                    TIME "+time.format(date));
        hat.add("___________________________________");
        hat.add("QTY DESCRIPTION     PRICE    TOTAL");
        return hat;
    }
}
