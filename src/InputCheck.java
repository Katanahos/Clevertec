import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputCheck {

    /**
     * вызов метода в зависимости от источника данных
     */
    public static ArrayList<String> checkRunner(String text) { // работа с данными из параметров
        String[] array = text.split(" ");
        return check(array);
    }

    public static ArrayList<String> checkRunnerByConsole() { // работа с данными из консоли
        System.out.println("Введите данные в формате (номер товара-количество) через пробел и скидочную карту в формате (card-номер карты) ");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = bufferedReader.readLine();
            String[] array = text.split(" ");
            return check(array);
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<String> checkRunnerByFile() {     // работа с данными из файла
        System.out.println("Введите путь файла для чтения данных");
        while (true) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                String text = bufferedReader.readLine();
                Path path = Paths.get(text);
                List<String> list = Files.readAllLines(path);
                String[] array = list.get(0).split(" ");
                return check(array);
            } catch (IOException e) {
                System.out.println("Ошибка ввода/файл не найден+ \n Введите корректный путь");

            }
        }
    }

    public static ArrayList<String> check(String[] array) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Integer> product = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        int s = 0;                         //  модификатор для списка при наличии/отсутсвии карты
        double sum = 0.0;                  //  общая сумма
        double sale = 0.0;                 //  общая скидка
        boolean haveCard = false;
        DecimalFormat df = new DecimalFormat("0.00");

        if (array[array.length - 1].startsWith("card")) { // проверяем наличие скидочной карты
            int cardNumber = 0;
            try {
                cardNumber = Integer.parseInt(array[array.length - 1].substring(5));
            } catch (NumberFormatException e) {
                System.out.println("Предъявлена скидочная карта другого магазина");
            }
            if (DiscountCard.values().length < cardNumber) // номера каточек в базе идут по порядку,номер карточки не может быть больше
                System.out.println("Предъявлена неверная скидочная карта");
            else {
                haveCard = true;
                s = 1;
            }
        }

        for (int i = 0; i < array.length - s; i++) { //преобразуем исходный массив в данные формата itemId-quantity
            try {
                int delimiter = array[i].indexOf('-');
                product.add(Integer.parseInt(array[i].substring(0, delimiter)));
                quantity.add(Integer.parseInt(array[i].substring(delimiter + 1)));
            } catch (Exception e) {
                System.out.println("В продукте №" + (i + 1) + " заданы неверные данные ");
            }
        }


        for (int i = 0; i < product.size(); i++) {
            if (product.get(i) > Product.values().length) {
                System.out.println("Такого продукта не существует");
            } else {
                Product prod = Product.fromNumber(product.get(i));
                if (prod.discount && quantity.get(i) > 4) {
                    double discount = prod.price * quantity.get(i) * 0.1;
                    result.add(quantity.get(i) + "   " + prod.productName + " $" + df.format(prod.price) + "   $" + df.format((prod.price * quantity.get(i) - discount)));
                    result.add("    sale                     $" + df.format(discount));
                    sale += discount;
                } else {
                    result.add(quantity.get(i) + "   " + prod.productName + " $" + df.format(prod.price) + "   $" + df.format(prod.price * quantity.get(i)));
                }
                sum += prod.price * quantity.get(i);
            }
        }

        if (haveCard)
            sale += sum * 0.05;

        result.add("___________________________________");
        result.add("SUBTOTAL                     $" + df.format(sum));
        result.add("YOU SAVED                    $" + df.format(sale));
        result.add("TOTAL                        $" + df.format(sum - sale));
        return result;
    }

}
