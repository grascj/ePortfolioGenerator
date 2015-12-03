/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_CHOOSE_BUTTON;
import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.ICON_CHOOSE;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_CHOOSE;
import static epg.ProgramConstants.TT_OK;
import epg.error.ErrorHandler;
import epg.model.VideoComponent;
import static epg.view.ViewHelper.initChildButton;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
public class VideoPrompt extends Stage {

    //component
    VideoComponent comp;

    //data
    int width;
    int length;
    String fileName;
    String filePath;
    String caption;

    //UI 
    Stage ui;
    Button okBtn;
    Button pickFile;
    Label currentFileName;
    FileChooser fileChooser;
    //@video thumbnail?

    TextField widthField;
    TextField lengthField;
    TextField captionField;

    //FLAG
    boolean ok;

    public VideoPrompt(VideoComponent comp) {

        width = comp.getWidth();
        length = comp.getLength();
        caption = comp.getCaption();
        fileName = comp.getFile();
        filePath = comp.getFileURL();

        this.setTitle("Add Video");

        //SET THE FLAG TO FALSE, NEEDS TO BE TRUE TO COMMIT CHANGES
        ok = false;
        this.comp = comp;
        caption = comp.getCaption();

        initModality(Modality.APPLICATION_MODAL);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("MP4 Files", "*.mp4"));

        //init fields
        this.currentFileName = new Label(comp.getFile());
        okBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_OK);
        pickFile = initChildButton(CSS_CHOOSE_BUTTON, ICON_CHOOSE, TT_CHOOSE);

        widthField = new TextField(Integer.toString(comp.getWidth()));
        lengthField = new TextField(Integer.toString(comp.getLength()));

        captionField = new TextField(caption);

        initHandlers();

        BorderPane uicontainer = new BorderPane();
        BorderPane center = new BorderPane();
        center.getStyleClass().add(CSS_CONTAINER);

        VBox topBox = new VBox();
        topBox.setStyle("-fx-border-width: 0 0 10px 0; -fx-border-color:transparent;");

        Label captionLabel = new Label("Caption: ");
        HBox captionBox = new HBox();
        captionBox.getChildren().addAll(captionLabel, captionField);
        captionBox.getStyleClass().add(CSS_CONTAINER);

        topBox.getStyleClass().add(CSS_CONTAINER);
        topBox.getChildren().addAll(currentFileName, pickFile, captionBox);
        center.setTop(topBox);

        VBox leftBox = new VBox();
        leftBox.getStyleClass().add("container_nospacing");
        leftBox.getChildren().addAll(new Label("Set the width:"), widthField);
        center.setLeft(leftBox);

        VBox rightBox = new VBox();
        rightBox.getStyleClass().add("container_nospacing");
        rightBox.getChildren().addAll(new Label("Set the height:"), lengthField);
        center.setRight(rightBox);

        center.setStyle("-fx-border-width: 0 0 10px 0; -fx-border-color:transparent;");

        uicontainer.setCenter(center);
        uicontainer.setBottom(okBtn);

        uicontainer.getStyleClass().add(CSS_CONTAINER);

        Scene promptScene = new Scene(uicontainer, 400, 300);
        promptScene.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptScene);
        this.show("hello");

    }

    public void initHandlers() {
        widthField.addEventFilter(KeyEvent.KEY_TYPED, ErrorHandler.getNumberKeyEventHandler());
        lengthField.addEventFilter(KeyEvent.KEY_TYPED, ErrorHandler.getNumberKeyEventHandler());
        okBtn.setOnAction(e -> {
            //@todo idiot proof the number values
            ok = true;
            comp.setFile(fileName);
            comp.setFileURL(filePath);

            if (lengthField.getText().isEmpty()) {
                comp.setLength(0);
            } else {
                comp.setLength(Integer.parseInt(lengthField.getText()));
            }

            if (widthField.getText().isEmpty()) {
                comp.setWidth(0);
            } else {
                comp.setWidth(Integer.parseInt(widthField.getText()));
            }

            caption = captionField.getText();
            comp.setCaption(caption);
            this.hide();
        });
        pickFile.setOnAction(e -> {
            try {
                this.fileInput();
            } catch (MalformedURLException ex) {
                Logger.getLogger(ImagePrompt.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void fileInput() throws MalformedURLException {
        File selectedFile = fileChooser.showOpenDialog(this);
        if (selectedFile != null) {
            filePath = selectedFile.getPath();
            fileName = selectedFile.getName();
            currentFileName.setText(fileName);
        }
    }

    public void show(String message) {
        this.showAndWait();
    }

    public boolean isOk() {
        return ok;
    }

}
