/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_PAGEVIEW;
import static epg.ProgramConstants.CSS_PAGEVIEW_SIDEBAR_BUTTONS;
import static epg.ProgramConstants.CSS_PAGEVIEW_TOPBAR;
import static epg.ProgramConstants.CSS_PAGEVIEW_TOPBAR_CHILD;
import static epg.ProgramConstants.ICON_BANNER;
import static epg.ProgramConstants.ICON_BOARD;
import static epg.ProgramConstants.ICON_CIRCLE_X;
import static epg.ProgramConstants.ICON_FOOTER;
import static epg.ProgramConstants.ICON_PICTURE;
import static epg.ProgramConstants.ICON_TEXT_PLUS;
import static epg.ProgramConstants.ICON_VIDEO;
import static epg.ProgramConstants.TT_COLOR;
import static epg.ProgramConstants.TT_COMP_IMAGE;
import static epg.ProgramConstants.TT_COMP_REMOVE;
import static epg.ProgramConstants.TT_COMP_SS;
import static epg.ProgramConstants.TT_COMP_TEXT;
import static epg.ProgramConstants.TT_COMP_VIDEO;
import static epg.ProgramConstants.TT_FONT;
import static epg.ProgramConstants.TT_LAYOUT;
import static epg.ProgramConstants.TT_PV_BANNER;
import static epg.ProgramConstants.TT_PV_FOOTER;
import epg.controller.PageViewController;
import epg.model.Component;
import epg.model.Page;
import static epg.view.ViewHelper.initChildButton;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

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

    ComboBox fonts;

    HBox titlecontainer;
    Label titleLabel;
    
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

    public PageView(PageViewController pvc) {
        this.pvc = pvc;
        this.getStyleClass().add(CSS_PAGEVIEW);
        initChildren();
        initHandlers();
        placeChildren();

    }

    private void initChildren() {
        //containers
        topBox = new HBox();
        topBox.getStyleClass().add(CSS_PAGEVIEW_TOPBAR);
        sideBox = new GridPane();

        cv = new ComponentViewer(this);
        /*
         public enum LAYOUT      {lownav, sidenav, gaps, topnav, fixedname};
         public enum COLOR       {beach, campfire, personal, SBUred, vintage};
         public enum FONT        {Fjalla_One, Bree_Serif, Muli, Vollkorn, Iconsolata};

         */
        //buttons n stuff

        titlecontainer = new HBox();
        titleLabel = new Label("Title: ");
        pageTitleField = new TextField();

        fonts = new ComboBox();
        fonts.getItems().addAll("Fjalla One", "Bree Serif", "Muli", "Vollkorn", "Iconsolata");
        fonts.setTooltip(new Tooltip(TT_FONT));
        
        layouts = new ComboBox();
        layouts.getItems().addAll("Low Nav", "Side Nav", "Gaps", "Top Nav", "Fixed Name");
        layouts.setTooltip(new Tooltip(TT_LAYOUT));
        
        colors = new ComboBox();
        colors.getItems().addAll("Beach", "Campfire", "Personal", "SBU Red", "Vintage");
        colors.setTooltip(new Tooltip(TT_COLOR));
        
        titlecontainer.getStyleClass().add(CSS_PAGEVIEW_TOPBAR_CHILD);
        fonts.getStyleClass().add(CSS_PAGEVIEW_TOPBAR_CHILD);
        layouts.getStyleClass().add(CSS_PAGEVIEW_TOPBAR_CHILD);
        colors.getStyleClass().add(CSS_PAGEVIEW_TOPBAR_CHILD);
        
        
        footerButton = initChildButton(CSS_PAGEVIEW_TOPBAR_CHILD, ICON_FOOTER, TT_PV_FOOTER);
        bannerButton = initChildButton(CSS_PAGEVIEW_TOPBAR_CHILD, ICON_BANNER, TT_PV_BANNER);
        //sidebar

        imageCompButton = ViewHelper.initChildButton(CSS_PAGEVIEW_SIDEBAR_BUTTONS, ICON_PICTURE, TT_COMP_IMAGE);
        textCompButton = ViewHelper.initChildButton(CSS_PAGEVIEW_SIDEBAR_BUTTONS, ICON_TEXT_PLUS, TT_COMP_TEXT);
        slideCompButton = ViewHelper.initChildButton(CSS_PAGEVIEW_SIDEBAR_BUTTONS, ICON_BOARD, TT_COMP_SS);
        videoCompButton = ViewHelper.initChildButton(CSS_PAGEVIEW_SIDEBAR_BUTTONS, ICON_VIDEO, TT_COMP_VIDEO);
        removeCompButton = ViewHelper.initChildButton(CSS_PAGEVIEW_SIDEBAR_BUTTONS, ICON_CIRCLE_X, TT_COMP_REMOVE);
    }

    private void initHandlers() {
        layouts.setOnAction(e -> {
            pvc.handleLayoutChange(layouts.getSelectionModel().getSelectedIndex());
        });
        colors.setOnAction(e -> {
            pvc.handleColorChange(colors.getSelectionModel().getSelectedIndex());
        });
        fonts.setOnAction(e -> {
            pvc.handleFontChange(fonts.getSelectionModel().getSelectedIndex());
        });

        footerButton.setOnAction(e -> {
            pvc.handleFooterChange();
        });
        bannerButton.setOnAction(e -> {
            pvc.handleBannerChange();
        });
        pageTitleField.setOnKeyReleased(e -> {
            pvc.handleTitleChange(pageTitleField.getText());
        });
        imageCompButton.setOnAction(e -> {
            pvc.handleImageComp();
        });
        textCompButton.setOnAction(e -> {
            pvc.handleTextComp();
        });
        slideCompButton.setOnAction(e -> {
            pvc.handleSlideComp();
        });
        videoCompButton.setOnAction(e -> {
            pvc.handleVideoComp();
        });
        removeCompButton.setOnAction(e -> {
            pvc.handleRemoveComp(cv.selection);
        });
    }

    private void placeChildren() {
        titlecontainer.getChildren().addAll(titleLabel, pageTitleField);
        topBox.getChildren().addAll(titlecontainer, fonts, layouts, colors, footerButton, bannerButton);

        RowConstraints a = new RowConstraints();
        a.vgrowProperty().set(Priority.ALWAYS);
        for (int i = 0; i < 5; i++) {
            sideBox.getRowConstraints().add(a);
        }
        sideBox.addColumn(0, imageCompButton, textCompButton, slideCompButton, videoCompButton, removeCompButton);

        //sideBox.getChildren().addAll(imageCompButton, textCompButton, slideCompButton, videoCompButton, removeCompButton);
        this.setTop(topBox);
        this.setLeft(sideBox);
        this.setCenter(cv);

    }

    public void update(Page page) {
        pageTitleField.setText(page.getTitle());
        layouts.getSelectionModel().select(page.getLayout().ordinal());
        colors.getSelectionModel().select(page.getColors().ordinal());
        fonts.getSelectionModel().select(page.getFont().ordinal());
        idiotProofTB();
        cv.update(page.getComponents());
    }
    
    public void idiotProofTB()
    {
        if(cv.selection == null)
            removeCompButton.setDisable(true);
        else
            removeCompButton.setDisable(false);
    }
    
    public void disarm()
    {
        cv.selection = null;
    }
    

    public Component getComponent() {
        return selectedComponent;
    }

}
