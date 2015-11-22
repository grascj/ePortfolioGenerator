/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.NEWIMAGE;
import static epg.ProgramConstants.OKAY;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    
    public BannerPrompt(Stage primaryStage, String currentFileURL, String currentFile)
    {
        okay = false;
        initModality(Modality.APPLICATION_MODAL);
        initOwner(primaryStage);

        if(currentFileURL != null)
            this.currentIMG = new ImageView("file:"+currentFileURL);
        else
            this.currentIMG = new ImageView();
        
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.png"));
        this.currentFileName = new Label(currentFile);
        okBtn = new Button(OKAY);
        pickFile = new Button(NEWIMAGE);
        
        
        okBtn.setOnAction(e->{
            okay = true;
            this.hide();
        });
        pickFile.setOnAction(e->{this.fileInput();});
        
        
        this.fileUrl=currentFileURL;
        this.fileName=currentFile;
        
        
        VBox uicontainer = new VBox();
        uicontainer.getChildren().addAll(currentIMG, currentFileName, pickFile, okBtn);
        Scene promptScene = new Scene(uicontainer);
        this.setScene(promptScene);
        this.show("hello");
        
        

        
    }
    
    public void fileInput() 
    {
                File selectedFile = fileChooser.showOpenDialog(this);
                if(selectedFile != null)
                {
                    fileUrl = selectedFile.getAbsoluteFile().toString();
                    fileName = selectedFile.getName();
                    currentFileName.setText(fileName);
                    currentIMG.setImage(new Image("file:"+fileUrl));
                }
    }
    
    
    public boolean isOk()
    {
        return okay;
    }
    
    
  
    public void show(String message)
    {
        //@todo message for prompt
        this.showAndWait();
    }
    
    /**
     * Returns the data collected by the prompt
     * @return a String array such that [file url, file name, text]
     */
    public String[] getSelection()
    {
        String[] data = {fileUrl, fileName};
        return data;
    }
    
}
