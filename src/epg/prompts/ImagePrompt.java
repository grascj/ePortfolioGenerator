/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.NEWIMAGE;
import static epg.ProgramConstants.NEWVIDEO;
import static epg.ProgramConstants.OKAY;
import epg.model.ImageComponent;
import epg.model.VideoComponent;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    //data

    ImageComponent comp;
            
    //UI 
    Stage ui;
    Button okBtn;
    Button pickFile;
    Label currentFileName;
    FileChooser fileChooser;
    
    
    public ImagePrompt(Stage primaryStage, ImageComponent comp)
    {
        this.comp = comp;
        initModality(Modality.APPLICATION_MODAL);
        initOwner(primaryStage);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.png"));
        this.currentFileName = new Label(comp.getFile());
        okBtn = new Button(OKAY);
        pickFile = new Button(NEWIMAGE);
        
        
        okBtn.setOnAction(e->{
            this.hide();
        });
        pickFile.setOnAction(e->{this.fileInput();});
        
        
        VBox uicontainer = new VBox();
        uicontainer.getChildren().addAll(this.currentFileName, pickFile, okBtn);
        Scene promptScene = new Scene(uicontainer);
        this.setScene(promptScene);
        this.show("hello");
        //@todo pick size
        
    }
    
    public void fileInput() 
    {
                File selectedFile = fileChooser.showOpenDialog(this);
                if(selectedFile != null)
                {
                    comp.setImageURL(selectedFile.getAbsoluteFile().toString());
                    comp.setFile(selectedFile.getName());
                    currentFileName.setText(comp.getFile());
                }
    }
  
    public void show(String message)
    {
        //@todo message for prompt
        this.showAndWait();
    }
    
    
}
