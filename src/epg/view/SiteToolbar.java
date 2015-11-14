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
    
    
    private void initChildren() 
    {
        nameField = new TextField();
        pageBox = new ComboBox();
        addPageButton = new Button("add page");
        removePageButton = new Button("remove page");
    }
    
    private void initHandlers()
    {
        nameField.setOnKeyReleased(e->{stc.handleNameChange(nameField.getText());});
        pageBox.setOnAction(e->{
            if(pageBox.getItems().size() > 0 && pageBox.getItems() != null){stc.handlePageChange(pageBox.getSelectionModel().getSelectedIndex());}
                    });
        addPageButton.setOnAction(e->{stc.handleAddPage();});
        removePageButton.setOnAction(e->{stc.handleRemovePage();});
    }
    
    private void placeChildren()
    {
        this.getChildren().addAll(nameField, addPageButton, removePageButton, pageBox);
    }
    
    public void update(ArrayList<Page> pages, int index)
    {
        System.out.println("updated");
        pageBox.getItems().clear();
        pageBox.getItems().setAll(pages);
        pageBox.getSelectionModel().select(index);
    }
    
   //make a listener that we can disable when updating
    
    
    
    
    
   
    
}
