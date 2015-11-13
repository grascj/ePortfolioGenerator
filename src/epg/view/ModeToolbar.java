/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.ModeController;
import epg.model.Portfolio;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author cgmp
 */
class ModeToolbar extends VBox{
    
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
        editorButton = new Button();
    }

    private void initHandlers() {
        siteButton.setOnAction(e->{mc.handleSiteMode();});
        editorButton.setOnAction(e->{mc.handleEditMode();});
    }
    
    private void placeChildren()
    {
        this.getChildren().addAll(siteButton, editorButton);
    }
    
    
    public void updateControls(Portfolio portfolio)
    {
        //@TODO make the other button hidden
        
    }
    
    
    
    
    
    
}
