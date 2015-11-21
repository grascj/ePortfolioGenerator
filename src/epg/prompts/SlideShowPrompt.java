/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import epg.model.Slide;
import epg.model.SlideShow;
import epg.model.SlideShowComponent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class SlideShowPrompt extends Stage {

    //component
    SlideShowComponent comp;

    //data
    SlideShow ss;

    //ui
    BorderPane uipositioner;
    HBox titleBox;
    Label titleLabel;
    TextField titleField;
    
    ScrollPane slideScroll;
    VBox slidePane;
    VBox sideToolbar;
    Button upBtn;
    Button downBtn;
    Button rmButton;
    Button addButton;
    
    
    
    

    public SlideShowPrompt(SlideShowComponent comp) {
        if (comp.getslideshow() == null) {
            this.ss = comp.getslideshow();
            populatePane();
        } else {
            ss = new SlideShow();
        }
        
        
    }

    private void initUI() {
        
        titleBox = new HBox();
        titleLabel = new Label("Title:");
        titleField = new TextField();
        titleBox.getChildren().addAll(titleLabel, titleField);
        
        slidePane = new VBox();
        slideScroll = new ScrollPane(slidePane);
        
        sideToolbar = new VBox();
        upBtn = new Button();
        downBtn = new Button();
        rmButton = new Button();
        addButton = new Button();
        sideToolbar.getChildren().addAll(upBtn,downBtn,rmButton,addButton);
        
        
        
        
        uipositioner = new BorderPane();
        Scene promptbody = new Scene(uipositioner);
        this.setScene(promptbody);
    }

    private void populatePane() {
        slidePane.getChildren().clear();
        for (Slide s : ss.getSlides()) {
            slidePane.getChildren().add(new SlideContainer(s, this));
        }
    }

}
