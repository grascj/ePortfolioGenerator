/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.OKAY;
import epg.model.ParagraphComponent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class ParagraphPrompt extends Stage{

    BorderPane uipositioner;
    FontBox fb;
    
    VBox paragraphcontainer;
    
    Label paraLabel;
    TextArea paraText;
    
    
    
    Button okayBtn;
    
    
    
    //data
    ParagraphComponent comp;
    boolean ok;
    
    public ParagraphPrompt(ParagraphComponent comp) {
        this.comp = comp;
        ok = false;
        
        initUI();
        initHandlers();
        placeChildren();
        
        Scene promptbody = new Scene(uipositioner);
        this.setScene(promptbody);
        this.showAndWait();
        
    }
    
    private void initUI()
    {
        uipositioner = new BorderPane();
        fb = new FontBox(comp.getFont(), comp.getFontSize());
        
        paragraphcontainer = new VBox();
        paraLabel = new Label("Enter the text for the Paragraph:");
        paraText = new TextArea();
        paraText.setText(comp.getText());
        okayBtn = new Button(OKAY);
    }
    
    private void initHandlers()
    {
        okayBtn.setOnAction(e->{ok = true; comp.setText(paraText.getText()); this.hide();});
    }
    
    private void placeChildren()
    {
        
        paragraphcontainer.getChildren().addAll(paraLabel, paraText);
        
        uipositioner.setTop(fb);
        uipositioner.setCenter(paragraphcontainer);
        uipositioner.setBottom(okayBtn);
    }
    
    public boolean isOk()
    {
        return ok;
    }
    
    //check the string, if a hyperlink is no longer present it gets deleted
    public String checkChanges()
    {
        
        return null;
    }   
        
}
