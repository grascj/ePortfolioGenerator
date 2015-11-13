/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.SiteToolbarController;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author cgmp
 */
public class SiteToolbar extends FlowPane{
    
    TextField nameField;
    ComboBox pageBox;
    Button addPageButton;
    Button removePageButton;
    SiteToolbarController stc;
    
    
    public SiteToolbar(SiteToolbarController stc)
    {
        this.stc = stc;
        initChildren();
        initHandlers();
    }
    
    
    public void initChildren() 
    {
        nameField = new TextField();
        pageBox = new ComboBox();
        addPageButton = new Button();
        removePageButton = new Button();
    }
    
    public void initHandlers()
    {
        nameField.setOnKeyReleased(e->{stc.handleNameChange(nameField.getText());});
        pageBox.setOnAction(e->{stc.handlePageChange(pageBox.getSelectionModel().getSelectedItem());});//@todo maybe use ints for enums
        addPageButton.setOnAction(e->{stc.handleAddPage();});
        removePageButton.setOnAction(e->{stc.handleRemovePage(pageBox.getSelectionModel().getSelectedItem());});
        
    }
    

   
    
}
