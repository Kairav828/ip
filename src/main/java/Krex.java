import java.util.Scanner;

public class Krex {
    public static void main(String[] args) {
        Ui.showWelcome();

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            String input = sc.nextLine().trim();

            if (Parser.isBye(input)) {
                Ui.showBye();
                break;
            }

            if (Parser.isList(input)) {
                Ui.showMessage(taskList.formatList());
                continue;
            }

            if (Parser.isMark(input)) {
                int idx = Parser.parseIndex(input);
                Task t = taskList.get(idx);
                t.markDone();
                Ui.showMessage("Nice! I've marked this task as done:\n  " + t);
                continue;
            }

            if (Parser.isUnmark(input)) {
                int idx = Parser.parseIndex(input);
                Task t = taskList.get(idx);
                t.unmarkDone();
                Ui.showMessage("OK, I've marked this task as not done yet:\n  " + t);
                continue;
            }

            Task t = new Task(input);
            taskList.add(t);
            Ui.showMessage("added: " + t);
        }
    }
}
