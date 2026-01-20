import java.util.Scanner;

public class Krex {
    public static void main(String[] args) {
        Ui.showWelcome();

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            String input = sc.nextLine();

            if (Parser.isBye(input)) {
                Ui.showBye();
                break;
            }

            if (Parser.isList(input)) {
                Ui.showMessage(taskList.formatList());
                continue;
            }

            taskList.add(input);
            Ui.showMessage("added: " + input);
        }
    }
}
