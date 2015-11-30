/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.CSS_SLIDE;
import static epg.ProgramConstants.CSS_SLIDESHOW_BUTTON;
import static epg.ProgramConstants.CSS_SLIDE_SELECTED;
import static epg.ProgramConstants.ICON_ARROW_DOWN;
import static epg.ProgramConstants.ICON_ARROW_UP;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.ICON_MINUS;
import static epg.ProgramConstants.ICON_PLUS;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_OK;
import static epg.ProgramConstants.TT_SS_ADD;
import static epg.ProgramConstants.TT_SS_DOWN;
import static epg.ProgramConstants.TT_SS_RM;
import static epg.ProgramConstants.TT_SS_UP;
import epg.model.Slide;
import epg.model.SlideShow;
import epg.model.SlideShowComponent;
import epg.view.ViewHelper;
import static epg.view.ViewHelper.initChildButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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

    Button okayBtn;

    //FLAG
    boolean ok;

    public SlideShowPrompt(SlideShowComponent comp) {
        this.setTitle("Add Slide Show");
        initModality(Modality.APPLICATION_MODAL);

        this.comp = comp;
        ok = false;
        initUI();
        initHandlers();
        placeChildren();

        if (comp.getslideshow() != null) {
            this.ss = comp.getslideshow();
            populatePane();
        } else {
            this.ss = new SlideShow();
        }

        update();

        this.showAndWait();
    }

    private void initUI() {

        titleBox = new HBox();
        titleLabel = new Label("Title:");
        titleField = new TextField();
        titleField.setText(comp.getslideshow().getTitle());

        slidePane = new VBox();
        slidePane.getStyleClass().add("container_nospacing");
        slideScroll = new ScrollPane(slidePane);

        sideToolbar = new VBox();

        upBtn = ViewHelper.initChildButton(CSS_SLIDESHOW_BUTTON, ICON_ARROW_UP, TT_SS_UP);
        downBtn = ViewHelper.initChildButton(CSS_SLIDESHOW_BUTTON, ICON_ARROW_DOWN, TT_SS_DOWN);
        rmButton = ViewHelper.initChildButton(CSS_SLIDESHOW_BUTTON, ICON_MINUS, TT_SS_RM);
        addButton = ViewHelper.initChildButton(CSS_SLIDESHOW_BUTTON, ICON_PLUS, TT_SS_ADD);

        okayBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_OK);

        uipositioner = new BorderPane();

    }

    private void initHandlers() {
        upBtn.setOnAction(e -> {
            comp.getslideshow().moveDown();
            update();
        });
        downBtn.setOnAction(e -> {
            comp.getslideshow().moveUp();
            update();
        });
        rmButton.setOnAction(e -> {
            comp.getslideshow().removeSlide();
            update();
        });
        addButton.setOnAction(e -> {
            ss.addSlide();
            update();
        });

        okayBtn.setOnAction(e -> {
            ss.title = titleField.getText();
            ok = true;
            comp.setslideshow(ss);
            this.hide();
        });

    }

    public void update() {
        if (comp.getslideshow().selectedSlide == null) {
            rmButton.setDisable(true);
            downBtn.setDisable(true);
            upBtn.setDisable(true);

        } else {
            rmButton.setDisable(false);

            if (comp.getslideshow().indexSelection() == comp.getslideshow().getSlides().size() - 1) {
                downBtn.setDisable(true);
            } else {
                downBtn.setDisable(false);
            }

            if (comp.getslideshow().indexSelection() == 0) {
                upBtn.setDisable(true);
            } else {
                upBtn.setDisable(false);
            }
        }
        populatePane();
    }

    private void placeChildren() {
        titleBox.getChildren().addAll(titleLabel, titleField);
        sideToolbar.getChildren().addAll(upBtn, downBtn, rmButton, addButton);
        uipositioner.setTop(titleBox);
        uipositioner.setCenter(slideScroll);
        uipositioner.setLeft(sideToolbar);
        uipositioner.setBottom(okayBtn);

        slideScroll.getStyleClass().add(CSS_CONTAINER);
        titleBox.getStyleClass().add("slide_toolbar");
        sideToolbar.getStyleClass().add("slide_toolbar");
        uipositioner.getStyleClass().add(CSS_CONTAINER);
        Scene promptbody = new Scene(uipositioner, 400, 400);
        promptbody.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptbody);
    }

    private void populatePane() {
        slidePane.getChildren().clear();
        for (Slide s : ss.getSlides()) {
            SlideContainer sc = new SlideContainer(s, this);
            sc.setOnMouseClicked(e -> {
                ss.selectedSlide = s;
                this.update();
            });

            if (s == ss.selectedSlide) {
                sc.getStyleClass().add(CSS_SLIDE_SELECTED);
            } else {
                sc.getStyleClass().add(CSS_SLIDE);
            }

            slidePane.getChildren().add(sc);
        }

    }

    public boolean isOk() {
        return ok;
    }

}
