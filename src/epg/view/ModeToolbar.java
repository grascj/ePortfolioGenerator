/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_MODETOOLBAR_BUTTONS;
import epg.controller.ModeController;
import epg.model.Portfolio;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/**
 *
 * @author cgmp
 */
class ModeToolbar extends GridPane{
    
    //@TODO use an enum for state perhaps
    
    Button siteButton;
    Button editorButton;
    ModeController mc;
    
    public ModeToolbar(ModeController mc)
    {
        this.mc = mc;
        initButtons();
        initHandlers();
        placeChildren();

    }

    private void initButtons() {
        siteButton = new Button();
        siteButton.getStyleClass().add(CSS_MODETOOLBAR_BUTTONS);
        editorButton = new Button();
        editorButton.getStyleClass().add(CSS_MODETOOLBAR_BUTTONS);
        
    }

    private void initHandlers() {
        siteButton.setOnAction(e->{mc.handleSiteMode();});
        editorButton.setOnAction(e->{mc.handleEditMode();});
    }
    
    private void placeChildren()
    {
        //this.getChildren().addAll(siteButton, editorButton);
        
        RowConstraints rows = new RowConstraints();
        rows.setVgrow(Priority.ALWAYS);
        
        this.getRowConstraints().add(rows);
        this.getRowConstraints().add(rows);
        
        
        this.add(siteButton, 0, 0);
        
        this.add(editorButton, 0, 1);
    }
    
    
    public void updateControls(Portfolio portfolio)
    {
        //@TODO make the other button hidden
    }
    
    
    
    
    
    
}
