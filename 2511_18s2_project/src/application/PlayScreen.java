package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayScreen {
	private Stage stage;
    private String screenTitle;
    private String fxmlFile;
    private FXMLLoader fxmlLoader;

    public PlayScreen(Stage stage, String screenTitle, String fxmlFile) {
        this.stage = stage;
        this.screenTitle = screenTitle;
        this.fxmlFile = fxmlFile;
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    }

    public Stage getStage() {
        return stage;
    }

    public void start(MainMenuController mainMenuControl) {
    	System.out.println(this.stage);
    	System.out.println(this.screenTitle);
    	System.out.println(this.fxmlFile);
    	
        stage.setTitle(screenTitle);
        fxmlLoader.setController(mainMenuControl);
        try {
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
