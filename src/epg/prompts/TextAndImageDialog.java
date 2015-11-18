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
public class TextAndImageDialog extends Stage {
    //data
    String fileName;
    String fileUrl;
    String text;
    boolean okay;
            
    //UI 
    Stage ui;
    Button okBtn;
    Button pickFile;
    TextField uitext;
    Label currentFileName;
    FileChooser fileChooser;
    
    
    public TextAndImageDialog(Stage primaryStage, String currentFileURL, String currentFile, String currentText)
    {
        okay = false;
        initModality(Modality.APPLICATION_MODAL);
        initOwner(primaryStage);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.png"));
        uitext = new TextField(currentText);
        this.currentFileName = new Label(currentFile);
        okBtn = new Button(OKAY);
        pickFile = new Button(NEWIMAGE);
        
        
        okBtn.setOnAction(e->{
            okay = true;
            text = uitext.getText();
            this.hide();
        });
        pickFile.setOnAction(e->{this.fileInput();});
        
        
        this.fileUrl=currentFileURL;
        this.fileName=currentFile;
        this.text=currentText;
        
        
        VBox uicontainer = new VBox();
        uicontainer.getChildren().addAll(uitext, this.currentFileName, pickFile, okBtn);
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
        String[] data = {fileUrl, fileName, uitext.getText()};
        return data;
    }
    
}
