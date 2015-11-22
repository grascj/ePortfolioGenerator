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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class ParagraphPrompt extends Stage{

    BorderPane uipositioner;
    FontBox fb;
    
    TextField paraText;
    
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
    
    public void initUI()
    {
        uipositioner = new BorderPane();
        fb = new FontBox(comp.getFont(), comp.getFontSize());
        paraText = new TextField();
        paraText.setText(comp.getText());
        
        
        okayBtn = new Button(OKAY);
    }
    
    public void initHandlers()
    {
        okayBtn.setOnAction(e->{ok = true; comp.setText(paraText.getText());});
    }
    
    public void placeChildren()
    {
        uipositioner.setTop(fb);
        uipositioner.setBottom(okayBtn);
    }
    
        
        
}
