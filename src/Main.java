import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> check;
        Scanner scanner = new Scanner(System.in);
        int firstAnswer;
        int secondAnswer;
        while (true) {
            try {
                System.out.println("Выберите источник данных" +
                        "\n 1 - с уже заданными параметрами метода " +
                        "\n 2 - ввод с консоли " +
                        "\n 3 - чтение с файла");
                firstAnswer = scanner.nextInt();
                if (firstAnswer > 0 && firstAnswer < 4)
                    break;
                else
                    System.out.println("Введено неверное число\n");
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Введены неверные данные\n");
            }
        }

        while (true) {
            try {
                System.out.println("Выберите формат вывода данных" +
                        "\n 1 - вывод в консоль " +
                        "\n 2 - запись в файл");
                secondAnswer = scanner.nextInt();
                if (secondAnswer > 0 && secondAnswer < 3)
                    break;
                else
                    System.out.println("Введено неверное число\n");
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Введены неверные данные\n");
            }
        }

        switch (firstAnswer) {
            case 1:
                check = InputCheck.checkRunner("3-1 2-5 5-1 4-6 8-6 card-3");
                break;
            case 2:
                check = InputCheck.checkRunnerByConsole();
                break;
            case 3:
                check = InputCheck.checkRunnerByFile();
                break;
            default:                        // никогда не выполнится, написал чтобы не инициализировать список :)
                check = new ArrayList<>();
                break;
        }

        if (secondAnswer == 1)
            OutputCheck.checkInConsole(check);
        else
            OutputCheck.checkInFile(check);

    }
}

