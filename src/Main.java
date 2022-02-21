import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> check;
        if (args.length == 0) {
            check = InputCheck.startChoice();
            OutputCheck.checkInConsole(check);
        } else if (args.length == 1) {
            check = InputCheck.startChoice(args[0]);
            OutputCheck.checkInConsole(check);
        } else  {
            check = InputCheck.startChoice(args[0]);
            OutputCheck.checkInFile(check,args[1]);
        }
    }
}

