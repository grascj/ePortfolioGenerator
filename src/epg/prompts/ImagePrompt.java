/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_CHOOSE_BUTTON;
import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.CSS_PROMPT_IMAGE;
import static epg.ProgramConstants.DEFAULT_IMG;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.ICON_CHOOSE;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_CHOOSE;
import static epg.ProgramConstants.TT_OK;
import epg.model.ImageComponent;
import static epg.view.ViewHelper.initChildButton;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class ImagePrompt extends Stage {

    //component
    ImageComponent comp;

    //data
    int width;
    int length;
    String caption;
    String fileName;
    String filePath;

    //UI 
    Stage ui;
    Button okBtn;
    Button pickFile;
    Label currentFileName;
    FileChooser fileChooser;
    ImageView currentImage;
    Label captionLabel;

    Label floatLabel;
    ComboBox floatBox;
    
    TextField widthField;
    TextField lengthField;
    TextField captionField;

    //FLAG
    boolean ok;

    public ImagePrompt(ImageComponent comp) {
        
        width = comp.getWidth();
        length = comp.getLength();
        caption = comp.getCaption();
        fileName = comp.getFile();
        filePath = comp.getFileURL();
        
                
        this.setTitle("Add Image");
        //SET THE FLAG TO FALSE, NEEDS TO BE TRUE TO COMMIT CHANGES
        ok = false;
        this.comp = comp;
        caption = comp.getCaption();
        initModality(Modality.APPLICATION_MODAL);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.png"));

        //init fields
        if (comp.getFileURL()!= null) {
            this.currentImage = new ImageView("file:" + comp.getFileURL());
            this.currentFileName = new Label(comp.getFile());
        } else {
            this.currentImage = new ImageView("file:" + DEFAULT_IMG);
            this.currentFileName = new Label("DefaultImage.png");
        }
        
        currentImage.setPreserveRatio(true);
        currentImage.getStyleClass().add(CSS_PROMPT_IMAGE);
        currentImage.setFitHeight(150);
        currentImage.setFitWidth(150);
        
        //@todo implement
        floatLabel = new Label("Floating: ");
        floatBox = new ComboBox();
        floatBox.getItems().addAll("No","Left","Right");
        HBox floatcontainer = new HBox();
        floatcontainer.getChildren().addAll(floatLabel,floatBox);
        floatcontainer.getStyleClass().add(CSS_CONTAINER);
        
        
        okBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_OK);
        pickFile = initChildButton(CSS_CHOOSE_BUTTON, ICON_CHOOSE, TT_CHOOSE);

        widthField = new TextField(Integer.toString(comp.getWidth()));
        lengthField = new TextField(Integer.toString(comp.getLength()));
        captionField = new TextField(caption);

        initHandlers();

        BorderPane uicontainer = new BorderPane();
        //uicontainer.setTop(currentImage);
        BorderPane center = new BorderPane();
        VBox topBox = new VBox();
        topBox.getStyleClass().add(CSS_CONTAINER);

        captionLabel = new Label("Caption: ");
        HBox captionBox = new HBox();
        captionBox.getChildren().addAll(captionLabel, captionField);
        captionBox.getStyleClass().add(CSS_CONTAINER);

        topBox.getChildren().addAll(currentImage, currentFileName, pickFile, captionBox, floatcontainer);
        topBox.setStyle("-fx-border-width: 0 0 10px 0; -fx-border-color:transparent;");
        center.setTop(topBox);

        VBox leftBox = new VBox();
        leftBox.getStyleClass().add(CSS_CONTAINER);
        leftBox.getChildren().addAll(new Label("Set the width:"), widthField);
        center.setLeft(leftBox);

        VBox rightBox = new VBox();
        rightBox.getStyleClass().add(CSS_CONTAINER);
        rightBox.getChildren().addAll(new Label("Set the height:"), lengthField);
        center.setRight(rightBox);

        uicontainer.setCenter(center);
        uicontainer.setBottom(okBtn);
        uicontainer.getStyleClass().add(CSS_CONTAINER);
        Scene promptScene = new Scene(uicontainer, 400, 480);
        promptScene.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptScene);
        this.show("hello");
    }

    public void initHandlers() {
        okBtn.setOnAction(e -> {
            //@todo idiot proof the number values
            ok = true;
            comp.setFile(fileName);
            comp.setImageURL(filePath);
            comp.setLength(Integer.parseInt(widthField.getText()));
            comp.setWidth(Integer.parseInt(widthField.getText()));
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

            currentImage.setImage(new Image(selectedFile.toURI().toString()));
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
