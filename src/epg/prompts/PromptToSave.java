/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_BANNER;
import static epg.ProgramConstants.CSS_BANNER_IMAGE;
import static epg.ProgramConstants.CSS_BUTTON_CONTAINER;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.ICON_X;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_DONT_SAVE;
import static epg.ProgramConstants.TT_SAVE;
import static epg.view.ViewHelper.initChildButton;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class PromptToSave extends Stage {

    //data

    String fileName;
    String fileUrl;
    boolean okay;

    //UI 
    Stage ui;
    Button okBtn;
    Button noBtn;
    Label messageLabel;
    HBox buttoncontainer;

    public PromptToSave() {
        this.setTitle("Unsaved work!");
        okay = false;
        initModality(Modality.APPLICATION_MODAL);

        messageLabel = new Label("Do you wish to save before you do this?");

        okBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_SAVE);
        noBtn = initChildButton(CSS_OK_BUTTON, ICON_X, TT_DONT_SAVE);

        
        okBtn.setOnAction(e -> {
            okay = true;
            this.hide();
        });
        noBtn.setOnAction(e -> {
            this.hide();
        });

        
        buttoncontainer = new HBox();
        buttoncontainer.getStyleClass().add(CSS_BUTTON_CONTAINER);
        buttoncontainer.getChildren().addAll(noBtn, okBtn);
        
        VBox uicontainer = new VBox();
        uicontainer.getStyleClass().add(CSS_BANNER);
        uicontainer.getChildren().addAll(messageLabel, buttoncontainer);
        Scene promptScene = new Scene(uicontainer, 300, 300);
        promptScene.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptScene);
        this.show("hello");

    }



    public boolean isOk() {
        return okay;
    }

    public void show(String message) {
        this.showAndWait();
    }

}
