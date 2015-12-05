/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_OK;
import epg.model.Portfolio;
import static epg.view.ViewHelper.initChildButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class SaveAsPrompt extends Stage {

    BorderPane uicontainer;

    VBox container;
    Label fileLabel;
    TextField fileText;
    Button okayBtn;

    boolean ok;

    public SaveAsPrompt(Portfolio portfolio, String title, String label) {
        this.setTitle(title);
        initModality(Modality.APPLICATION_MODAL);

        ok = false;


        uicontainer = new BorderPane();
        container = new VBox();
        fileLabel = new Label(label);
        fileText = new TextField();
        okayBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_OK);
        okayBtn.setOnAction(e -> {
            ok = true;
            portfolio.setFileName(fileText.getText());
            this.hide();
        });
        container.getChildren().addAll(fileLabel, fileText);
        container.getStyleClass().add(CSS_CONTAINER);

        uicontainer.setCenter(container);
        uicontainer.setBottom(okayBtn);
        uicontainer.getStyleClass().add(CSS_CONTAINER);

        Scene promptbody = new Scene(uicontainer, 200, 200);
        promptbody.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptbody);
        this.showAndWait();

    }

    public boolean isOk() {
        return ok;
    }

}
