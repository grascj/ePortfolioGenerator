/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

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
    
    
    
    
    public ComponentView(Component comp)
    {
        this.comp = comp;
        init();
    }
    
    public void init()
    {
        editButton = new Button("edit");
        editButton.setOnAction(e->{comp.editPrompt();});
        displayLabel = new Label(comp.getDisplayText());

        this.getChildren().addAll(editButton, displayLabel);
    }
    
    
    
    
    
}
