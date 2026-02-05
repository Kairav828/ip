package krex;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final Krex krex = new Krex();

    @Override
    public void start(Stage stage) {
        MainWindow window = new MainWindow(krex);
        Scene scene = new Scene(window.getRoot(), 500, 600);

        stage.setTitle("Krex");
        stage.setScene(scene);
        stage.show();
    }
}
