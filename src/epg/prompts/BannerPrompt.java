/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_BANNER;
import static epg.ProgramConstants.CSS_BANNER_IMAGE;
import static epg.ProgramConstants.CSS_BUTTON_CONTAINER;
import static epg.ProgramConstants.CSS_CHOOSE_BUTTON;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.DEFAULT_IMG;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.ICON_CHOOSE;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_CHOOSE;
import static epg.ProgramConstants.TT_OK;
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
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class BannerPrompt extends Stage {

    //data

    String fileName;
    String fileUrl;
    boolean okay;

    //UI 
    Stage ui;
    Button okBtn;
    Button pickFile;
    ImageView currentIMG;
    Label currentFileName;
    FileChooser fileChooser;
    HBox buttoncontainer;

    public BannerPrompt(Stage primaryStage, String currentFileURL, String currentFile) {
        okay = false;
        initModality(Modality.APPLICATION_MODAL);
        initOwner(primaryStage);

        if (currentFileURL != null) {
            this.currentIMG = new ImageView("file:" + currentFileURL);
            this.currentFileName = new Label(currentFile);
        } else {
            this.currentIMG = new ImageView("file:" + DEFAULT_IMG);
            this.currentFileName = new Label("DefaultImage.png");
        }

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.png"));

        okBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_OK);
        pickFile = initChildButton(CSS_CHOOSE_BUTTON, ICON_CHOOSE, TT_CHOOSE);

        
        okBtn.setOnAction(e -> {
            okay = true;
            this.hide();
        });
        pickFile.setOnAction(e -> {
            this.fileInput();
        });

        this.fileUrl = currentFileURL;
        this.fileName = currentFile;

        currentIMG.getStyleClass().add(CSS_BANNER_IMAGE);
        
        buttoncontainer = new HBox();
        buttoncontainer.getStyleClass().add(CSS_BUTTON_CONTAINER);
        buttoncontainer.getChildren().addAll(pickFile, okBtn);
        
        VBox uicontainer = new VBox();
        uicontainer.getStyleClass().add(CSS_BANNER);
        uicontainer.getChildren().addAll(currentIMG, currentFileName, buttoncontainer);
        Scene promptScene = new Scene(uicontainer, 300, 300);
        promptScene.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptScene);
        this.show("hello");

    }

    public void fileInput() {
        File selectedFile = fileChooser.showOpenDialog(this);
        if (selectedFile != null) {
            fileUrl = selectedFile.getAbsoluteFile().toString();
            fileName = selectedFile.getName();
            currentFileName.setText(fileName);
            currentIMG.setImage(new Image("file:" + fileUrl));
        }
    }

    public boolean isOk() {
        return okay;
    }

    public void show(String message) {
        //@todo message for prompt
        this.showAndWait();
    }

    /**
     * Returns the data collected by the prompt
     *
     * @return a String array such that [file url, file name, text]
     */
    public String[] getSelection() {
        String[] data = {fileUrl, fileName};
        return data;
    }

}
