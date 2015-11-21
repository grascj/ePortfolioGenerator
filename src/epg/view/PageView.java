/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_PAGEVIEW;
import static epg.ProgramConstants.CSS_PAGEVIEW_SIDEBAR_BUTTONS;
import static epg.ProgramConstants.CSS_PAGEVIEW_TOPBAR;
import epg.controller.PageViewController;
import epg.model.Component;
import epg.model.Page;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
public class PageView extends BorderPane {
    
    //children
    HBox topBox;
    GridPane sideBox;
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
        this.getStyleClass().add(CSS_PAGEVIEW);
        
    }

    private void initChildren() {
        //containers
        topBox = new HBox();
        topBox.getStyleClass().add(CSS_PAGEVIEW_TOPBAR);
        sideBox = new GridPane();
        
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
        
        footerButton = new Button("footer");
        bannerButton = new Button("banner");
        pageTitleField = new TextField();
        //sidebar
        imageCompButton = new Button();
        imageCompButton.getStyleClass().add(CSS_PAGEVIEW_SIDEBAR_BUTTONS);
        textCompButton = new Button();
        textCompButton.getStyleClass().add(CSS_PAGEVIEW_SIDEBAR_BUTTONS);
        slideCompButton = new Button();
        slideCompButton.getStyleClass().add(CSS_PAGEVIEW_SIDEBAR_BUTTONS);
        videoCompButton = new Button();
        videoCompButton.getStyleClass().add(CSS_PAGEVIEW_SIDEBAR_BUTTONS);
        removeCompButton = new Button();
        removeCompButton.getStyleClass().add(CSS_PAGEVIEW_SIDEBAR_BUTTONS);
    }
    
    private void initHandlers()
    {
        layouts.setOnAction(e->{pvc.handleLayoutChange(layouts.getSelectionModel().getSelectedIndex());});
        colors.setOnAction(e->{pvc.handleColorChange(colors.getSelectionModel().getSelectedIndex());});
        
        footerButton.setOnAction(e->{pvc.handleFooterChange();});
        bannerButton.setOnAction(e->{pvc.handleBannerChange();});
        pageTitleField.setOnKeyReleased(e->{pvc.handleTitleChange(pageTitleField.getText());});
        imageCompButton.setOnAction(e->{pvc.handleImageComp();});
        textCompButton.setOnAction(e->{pvc.handleTextComp();});
        slideCompButton.setOnAction(e->{pvc.handleSlideComp();});
        videoCompButton.setOnAction(e->{pvc.handleVideoComp();});
        removeCompButton.setOnAction(e->{pvc.handleRemoveComp(cv.selection);});
    }
    
    private void placeChildren()
    {
        topBox.getChildren().addAll(layouts, colors, footerButton, bannerButton, pageTitleField);
        
        
        RowConstraints a = new RowConstraints();
        a.vgrowProperty().set(Priority.ALWAYS);
        for(int i = 0; i < 5; i++)
        {
            sideBox.getRowConstraints().add(a);
        }
        sideBox.addColumn(0, imageCompButton, textCompButton, slideCompButton, videoCompButton, removeCompButton);
                
        
        //sideBox.getChildren().addAll(imageCompButton, textCompButton, slideCompButton, videoCompButton, removeCompButton);
        
        
        
        
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
