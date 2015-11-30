/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static com.sun.glass.ui.Clipboard.TEXT_TYPE;
import epg.ProgramConstants.COMPONENTS;
import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_PROMPT_BUTTON;
import static epg.ProgramConstants.ICON_HEADER;
import static epg.ProgramConstants.ICON_LIST;
import static epg.ProgramConstants.ICON_PARAGRAPH;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_TEXT_HEAD;
import static epg.ProgramConstants.TT_TEXT_LIST;
import static epg.ProgramConstants.TT_TEXT_PARA;
import epg.model.HeaderComponent;
import epg.model.ListComponent;
import epg.model.ParagraphComponent;
import epg.model.TextComponent;
import epg.view.ViewHelper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class TextPrompt extends Stage {

    //data
    TextComponent comp;

    //ui
    BorderPane uipositioner;
    Label pickLabel;
    HBox buttoncontainer;
    Button paraBtn;
    Button listBtn;
    Button headerBtn;

    //FLAG
    boolean ok;
    COMPONENTS tt;

    public TextPrompt() {
        initModality(Modality.APPLICATION_MODAL);

        ok = false;

        initUI();
        initHandlers();
        placeChildren();
        //launch the other prompt depending on button pressed

        this.showAndWait();
    }

    public void initUI() {
        uipositioner = new BorderPane();
        pickLabel = new Label("Choose what type of Text Component:");
        buttoncontainer = new HBox();
        paraBtn = ViewHelper.initChildButton(CSS_PROMPT_BUTTON, ICON_PARAGRAPH, TT_TEXT_PARA);
        listBtn = ViewHelper.initChildButton(CSS_PROMPT_BUTTON, ICON_LIST, TT_TEXT_LIST);
        headerBtn = ViewHelper.initChildButton(CSS_PROMPT_BUTTON, ICON_HEADER, TT_TEXT_HEAD);
    }

    public void initHandlers() {
        paraBtn.setOnAction(e -> {
            this.hide();
            comp = new ParagraphComponent();
            ok = true;
            tt = COMPONENTS.PARAGRAPH;
        });

        listBtn.setOnAction(e -> {
            this.hide();
            comp = new ListComponent();
            ok = true;
            tt = COMPONENTS.LIST;
        });

        headerBtn.setOnAction(e -> {
            this.hide();
            comp = new HeaderComponent();
            ok = true;
            tt = COMPONENTS.HEADER;
        });
    }

    public void placeChildren() {
        buttoncontainer.getChildren().addAll(paraBtn, listBtn, headerBtn);

        HBox a = new HBox();
        a.getChildren().add(pickLabel);
        a.getStyleClass().add(CSS_CONTAINER);
        uipositioner.setTop(a);
        uipositioner.setCenter(buttoncontainer);

        buttoncontainer.getStyleClass().add(CSS_CONTAINER);
        uipositioner.getStyleClass().add(CSS_CONTAINER);
        Scene promptbody = new Scene(uipositioner, 300, 100);
        promptbody.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptbody);

    }

    public TextComponent getComp() {
        return comp;
    }

    public COMPONENTS getTextType() {
        return tt;
    }

    public boolean isOk() {
        return ok;
    }
}
