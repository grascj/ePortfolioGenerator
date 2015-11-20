/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_SITETOOLBAR;
import epg.controller.SiteToolbarController;
import epg.model.Page;
import java.util.ArrayList;
import java.util.Collection;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author cgmp
 */
public class SiteToolbar extends HBox{
    
    HBox nameContainer;
    TextField nameField;
    Label nameFieldDesc;
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
        this.getStyleClass().add(CSS_SITETOOLBAR);        
       
        
        nameFieldDesc = new Label("Name:");
        nameField = new TextField();
        
        nameContainer = new HBox();
        nameContainer.getChildren().addAll( nameFieldDesc, nameField);

        
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
        this.getChildren().addAll(nameContainer, pageBox, addPageButton, removePageButton);
    }
    
    public void update(ArrayList<Page> pages, int index)
    {
        pageBox.getItems().clear();
        pageBox.getItems().setAll(pages);
        pageBox.getSelectionModel().select(index);
    }
    

    public void updateCurrentTitle()
    {
        Collection a = new ArrayList(pageBox.getItems());
        pageBox.getItems().setAll(a);

    }
    
    
    
   
    
}
