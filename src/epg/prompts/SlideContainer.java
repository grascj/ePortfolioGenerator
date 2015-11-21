/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.DEFAULT_SLIDEIMG;
import epg.model.Slide;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class SlideContainer extends HBox {

    //data
    Slide slide;
    Stage prompt;

    //ui
    VBox captionBox;
    Label captionLabel;
    TextField captionField;
    ImageView slideImage;
    FileChooser fileChooser;
    

    public SlideContainer(Slide slide, Stage prompt) {
        this.prompt = prompt;
        this.slide = slide;
        initUI();
        initHandlers();
        
    }

    public void initUI() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.png"));

        captionLabel = new Label("Caption:");
        captionField = new TextField(slide.caption);

        if (slide.imageURL != null) {
            slideImage = new ImageView(slide.imageURL);
        } else {
            slideImage = new ImageView("file:" + DEFAULT_SLIDEIMG);
        }

        captionBox = new VBox();
        captionBox.getChildren().addAll(captionLabel, captionField);
        this.getChildren().addAll(slideImage, captionBox);
        
    }

    public void initHandlers() {
        captionField.setOnKeyReleased(e -> {
            slide.caption = captionField.getText();
        });
        slideImage.setOnMouseClicked(e -> {
            try {
                fileInput();
            } catch (MalformedURLException ex) {
                Logger.getLogger(SlideContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void fileInput() throws MalformedURLException {
        File selectedFile = fileChooser.showOpenDialog(prompt);
        if (selectedFile != null) {
            slide.image = selectedFile.getName();
            slide.imageURL = selectedFile.getPath();
            slideImage.setImage(new Image(selectedFile.toURI().toString()));
            
        }
    }

}
