/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.TT_OK;
import static epg.view.ViewHelper.initChildButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class FooterPrompt extends Stage{
    
    String footerText;
    
    TextField footerField;
    VBox uicontainer;
    Button okayBtn;
    Label footerLabel;
    
    //FLAG
    boolean ok;
    
    
    public FooterPrompt(String currentFooterText)
    {
        ok = false;
        this.footerText = currentFooterText;
        
        footerLabel = new Label("Set a message for the Footer:");
        footerField = new TextField(footerText);
        uicontainer = new VBox();
        okayBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_OK);
        
        initHandlers();
        
        uicontainer.getChildren().addAll(footerLabel, footerField, okayBtn);
        
        
        Scene promptbody = new Scene(uicontainer);
        this.setScene(promptbody);
        this.showAndWait(); 
    }
    
    
    private void initHandlers()
    {
        footerField.setOnKeyReleased(e-> {footerText = footerField.getText();});
        okayBtn.setOnAction(e->{ok = true; this.hide();});
    }
    
    public boolean isOk()
    {
        return ok;
    }
    
    public String getData()
    {
        return footerText;
    }
    
    
    
}
