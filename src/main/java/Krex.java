import java.util.Scanner;

public class Krex {
    public static void main(String[] args) {
        Ui.showWelcome();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            if (input.trim().equals("bye")) {
                Ui.showBye();
                break;
            }

            Ui.showMessage(input);
        }
    }
}