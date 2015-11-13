/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.ProgramConstants;
import epg.controller.PageViewController;
import epg.model.Component;
import epg.model.Page;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author cgmp
 */
public class PageView extends BorderPane {
    
    //children
    HBox topBox;
    VBox sideBox;
    ComboBox layouts;
    ComboBox colors;
    Button footerButton;
    Button bannerButton;
    TextField pageTitleField;
    Button imageCompButton;
    Button textCompButton;
    Button slideCompButton;
    Button videoCompButton;
    Button removeCompButton;
   
    //
    Component selectedComponent;
    ComponentViewer cv;
    PageViewController pvc;
    
    public PageView(PageViewController pvc)
    {
        this.pvc = pvc;
        initChildren();
        initHandlers();
        placeChildren();
    }

    private void initChildren() {
        //containers
        topBox = new HBox();
        sideBox = new VBox();
        
        cv = new ComponentViewer();
        /*
            public enum LAYOUT      {lownav, sidenav, gaps, topnav, fixedname};
            public enum COLOR       {beach, campfire, personal, SBUred, vintage};
        */
        //buttons n stuff
        layouts = new ComboBox();
        layouts.getItems().addAll("Low Nav","Side Nav","Gaps","Top Nav","Fixed Name");
        
        colors = new ComboBox();
        colors.getItems().addAll("Beach","Campfire","Personal","SBU Red","Vintage");
        
        footerButton = new Button();
        bannerButton = new Button();
        pageTitleField = new TextField();
        //sidebar
        imageCompButton = new Button();
        textCompButton = new Button();
        slideCompButton = new Button();
        videoCompButton = new Button();
        removeCompButton = new Button();
    }
    
    private void initHandlers()
    {
        //@todo might work????
        layouts.setOnAction(e->{pvc.handleLayoutChange((ProgramConstants.LAYOUT) layouts.getSelectionModel().getSelectedItem());});
        colors.setOnAction(e->{pvc.handleColorChange((ProgramConstants.COLOR) colors.getSelectionModel().getSelectedItem());});
        
        footerButton.setOnAction(e->{pvc.handleFooterChange();});
        bannerButton.setOnAction(e->{pvc.handleBannerChange();});
        pageTitleField.setOnKeyReleased(e->{pvc.handleTitleChange(pageTitleField.getText());});
        imageCompButton.setOnAction(e->{pvc.handleImageComp();});
        textCompButton.setOnAction(e->{pvc.handleTextComp();});
        slideCompButton.setOnAction(e->{pvc.handleSlideComp();});
        videoCompButton.setOnAction(e->{pvc.handleVideoComp();});
        removeCompButton.setOnAction(e->{pvc.handleRemoveComp(selectedComponent);});
    }
    
    private void placeChildren()
    {
        topBox.getChildren().addAll(layouts, colors, footerButton, bannerButton, pageTitleField);
        sideBox.getChildren().addAll(imageCompButton, textCompButton, slideCompButton, videoCompButton, removeCompButton);
        
        this.setTop(topBox);
        this.setLeft(sideBox);
        this.setCenter(cv);
        
        
        
    }
    
    public void update(Page page)
    {
        pageTitleField.setText(page.getTitle());
        layouts.getSelectionModel().select(page.getLayout().ordinal());
        colors.getSelectionModel().select(page.getLayout().ordinal());
        cv.update(page.getComponents());
    }
    
    public Component getComponent()
    {
        return selectedComponent;
    }
    
    
    
    
    
    
    
}
