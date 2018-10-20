package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Screen {
    private Stage stage;
    private String screenTitle;
    private String fxmlFile;
    private FXMLLoader fxmlLoader;

    public Screen(Stage stage, String screenTitle, String fxmlFile) {
        this.stage = stage;
        this.screenTitle = screenTitle;
        this.fxmlFile = fxmlFile;
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    }

    public Stage getStage() {
        return this.stage;
    }

    public void start(MainMenuController mainMenuControl) {
    	
        this.stage.setTitle(this.screenTitle);
        this.fxmlLoader.setController(mainMenuControl);
        try {
            Parent root = this.fxmlLoader.load();
            Scene sc = new Scene(root, 600, 400);
            this.stage.setScene(sc);
            //this.stage.setMinHeight(300);
    		//this.stage.setMinWidth(600);
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
