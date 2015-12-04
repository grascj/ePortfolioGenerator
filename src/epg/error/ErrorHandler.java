/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.error;

import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_PROMPT_BUTTON;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import epg.model.Page;
import epg.model.Portfolio;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class ErrorHandler {

    private ErrorHandler() {

    }

    public static void processError(String errorDialogTitle, String errorDialogMessage) {

        Stage errorStage = new Stage();
        errorStage.setTitle(errorDialogTitle);
        VBox vbox = new VBox();

        Label errorDialog = new Label(errorDialogMessage);
        Button okBtn = new Button();
        okBtn.setText("OK"); //same in english and german

        vbox.getStyleClass().add(CSS_CONTAINER);
        okBtn.getStyleClass().add(CSS_PROMPT_BUTTON);

        vbox.getChildren().addAll(errorDialog, okBtn);
        Scene errorScene = new Scene(vbox, 600, 200);
        errorScene.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        errorStage.setScene(errorScene);
        errorStage.setAlwaysOnTop(true);

        okBtn.setOnAction(e -> {
            errorStage.hide();
        });
        errorStage.showAndWait();

    }

    public static boolean isValidPortfolio(Portfolio portfolio) {
        boolean status = true;
        //check if it has a name
        if (portfolio.getStudentName() == null || portfolio.getStudentName().trim().equals("")) //check if it has 2 pages with the same title
        {
            processError("STUDENT NAME ERROR", "A student name must be entered to perform this action.");
            status = false;
        } else {
            boolean blanknames = false;
            boolean duplicate = false;
            for (Page page : portfolio.getPages()) {
                if (page.getTitle().trim().equals("")) {
                    blanknames = true;
                    processError("BLANK PAGE TITLE ERROR", "Every page needs a title to perform this action.");
                } else {
                    for (Page looppage : portfolio.getPages()) {
                        if (page != looppage && page.getTitle().equals(looppage.getTitle())) {
                            duplicate = true;
                            processError("DUPLICATE PAGE TITLE ERROR", "Every page needs a unique title for this action.");
                            break;
                        }
                    }
                }
                
                if(blanknames || duplicate)
                {
                    status = false;
                    break;
                }
            }
        }

        return status;

    }

    public static EventHandler<KeyEvent> getNumberKeyEventHandler() {
        EventHandler<KeyEvent> key = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char ar[] = event.getCharacter().toCharArray();
                if (ar.length > 0) {
                    char ch = ar[event.getCharacter().toCharArray().length - 1];
                    if (!(ch >= '0' && ch <= '9')) {
                        event.consume();
                    }
                }
            }
        };

        return key;
    }

}
