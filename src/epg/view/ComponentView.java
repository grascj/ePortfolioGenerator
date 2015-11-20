/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_COMPONENTVIEW;
import static epg.ProgramConstants.CSS_COMPONENTVIEW_BUTTON;
import static epg.ProgramConstants.CSS_COMPONENTVIEW_LABEL;
import epg.model.Component;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author cgmp
 */
public class ComponentView extends HBox{
    
    Component comp;
    Label displayLabel;
    Button editButton;
    
    
    
    
    public ComponentView(Component comp, String cssclass)
    {
        this.getStyleClass().add(cssclass);
        this.comp = comp;
        init();
    }
    
    public void init()
    {
        editButton = new Button("edit");
        editButton.getStyleClass().add(CSS_COMPONENTVIEW_BUTTON);
        editButton.setOnAction(e->{comp.editPrompt();});
        displayLabel = new Label(comp.getDisplayText());
        displayLabel.getStyleClass().add(CSS_COMPONENTVIEW_LABEL);
        
        this.getChildren().addAll(editButton, displayLabel);
    }
    
    
    
    
    
}
