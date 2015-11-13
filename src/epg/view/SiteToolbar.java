/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.SiteToolbarController;
import epg.model.Page;
import java.util.ArrayList;
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
        placeChildren();
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
        pageBox.setOnAction(e->{stc.handlePageChange(pageBox.getSelectionModel().getSelectedIndex());});//@todo maybe use ints for enums
        addPageButton.setOnAction(e->{stc.handleAddPage();});
        removePageButton.setOnAction(e->{stc.handleRemovePage();});
    }
    
    public void placeChildren()
    {
        this.getChildren().addAll(nameField, addPageButton, removePageButton, pageBox);
    }
    
    public void update(ArrayList<Page> pages, Page selectedPage)
    {
        pageBox.getItems().clear();
        for(Page page : pages)
        {
            pageBox.getItems().add(page.getTitle());
        }    
        
        pageBox.getSelectionModel().select(selectedPage.getTitle());
        
    }
    
    
    

   
    
}
