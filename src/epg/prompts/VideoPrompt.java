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
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    
    
    
    //UI 
    Stage ui;
    Button okBtn;
    Button pickFile;
    Label currentFileName;
    FileChooser fileChooser;
    //@video thumbnail?
    
    TextField widthField;
    TextField lengthField;
    
    
    //FLAG
    boolean ok;
    
    
    public VideoPrompt(Stage primaryStage, VideoComponent comp)
    {
        //SET THE FLAG TO FALSE, NEEDS TO BE TRUE TO COMMIT CHANGES
        ok = false;
        this.comp = comp;
        initModality(Modality.APPLICATION_MODAL);
        initOwner(primaryStage);

        
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("MP4 Files", "*.mp4"));
       
        //init fields

        
        this.currentFileName = new Label(comp.getFile());
        okBtn = new Button(OKAY);
        pickFile = new Button(NEWVIDEO);
        
        
        widthField = new TextField(Integer.toString(comp.getWidth()));
        lengthField = new TextField(Integer.toString(comp.getLength()));
        

        initHandlers();
        
        
        BorderPane uicontainer = new BorderPane();
        BorderPane center = new BorderPane();
        VBox topBox = new VBox();
        topBox.getChildren().addAll(currentFileName, pickFile);
        center.setTop(topBox);
        
        VBox leftBox = new VBox();
        leftBox.getChildren().addAll(new Label("Set the width:"),widthField);
        center.setLeft(leftBox);
        
        VBox rightBox = new VBox();
        rightBox.getChildren().addAll(new Label("Set the height:"),lengthField);
        center.setRight(rightBox);
        
        
        
        uicontainer.setCenter(center);
        uicontainer.setBottom(okBtn);
        
        
        
        Scene promptScene = new Scene(uicontainer, 400, 400);
        this.setScene(promptScene);
        this.show("hello");
        //@todo pick size
        
    }
    
    
    public void initHandlers()
    {
         okBtn.setOnAction(e->{
             //@todo idiot proof the number values
            ok = true;
            comp.setFile(fileName);
            comp.setVideoURL(filePath);
            comp.setLength(Integer.parseInt(widthField.getText()));
            comp.setWidth(Integer.parseInt(widthField.getText()));
            this.hide();
        });
        pickFile.setOnAction(e->{try {
            this.fileInput();
             } catch (MalformedURLException ex) {
                 Logger.getLogger(ImagePrompt.class.getName()).log(Level.SEVERE, null, ex);
             }
});
    }
    
    public void fileInput() throws MalformedURLException 
    {
                File selectedFile = fileChooser.showOpenDialog(this);
                if(selectedFile != null)
                {
                    filePath = selectedFile.getPath();
                    fileName = selectedFile.getName();
                    currentFileName.setText(fileName);
                }
    }
  
    public void show(String message)
    {
        //@todo message for prompt
        this.showAndWait();
    }
    
    public boolean isOk()
    {
        return ok;
    }
    
}
