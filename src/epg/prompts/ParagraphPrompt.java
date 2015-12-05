/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_BUTTON_CONTAINER;
import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.CSS_PROMPT_BUTTON;
import static epg.ProgramConstants.CSS_SLIDE;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.ICON_MINUS;
import static epg.ProgramConstants.ICON_PLUS;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_OK;
import epg.controller.ChangeController;
import epg.model.Hyperlink;
import epg.model.ParagraphComponent;
import static epg.view.ViewHelper.initChildButton;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
public class ParagraphPrompt extends Stage {

    BorderPane uipositioner;
    FontBox fb;

    VBox paragraphcontainer;

    Label paraLabel;
    TextArea paraText;

    Button okayBtn;

    HBox linkbuttoncontainer;
    Button addLink;
    Button rmLink;

    ScrollPane scrollLinks;
    VBox linkcontainer;

    //data
    ParagraphComponent comp;
    String text;
    ArrayList<Hyperlink> links;
    boolean ok;

    public class LinkBox extends HBox {

        Label text;
        Label url;
        TextField urlField;

        public LinkBox(String text) {
            this.text = new Label(text);
            url = new Label("     Enter URL: ");
            urlField = new TextField();

            this.getChildren().addAll(this.text, url, urlField);
            this.setStyle("-fx-padding: 10px; -fx-alignment: center;");
        }

    }

    public ParagraphPrompt(ParagraphComponent comp) {
        this.setTitle("Add Paragraph");
        initModality(Modality.APPLICATION_MODAL);

        this.comp = comp;
        ok = false;

        initUI();
        initHandlers();
        placeChildren();

        uipositioner.getStyleClass().add(CSS_CONTAINER);
        fb.getStyleClass().add(CSS_CONTAINER);
        paragraphcontainer.getStyleClass().add(CSS_CONTAINER);
        Scene promptbody = new Scene(uipositioner, 550, 600);
        promptbody.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptbody);
        this.showAndWait();

    }

    private void initUI() {
        uipositioner = new BorderPane();
        fb = new FontBox(comp.getFont(), comp.getFontSize());

        paragraphcontainer = new VBox();
        paraLabel = new Label("Enter the text for the Paragraph:");
        paraText = new TextArea();
        paraText.wrapTextProperty().set(true);
        paraText.setText(comp.getText());
        okayBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_OK);

        addLink = initChildButton(CSS_PROMPT_BUTTON, ICON_PLUS, "Add Link.");
        rmLink = initChildButton(CSS_PROMPT_BUTTON, ICON_MINUS, "Remove Link.");

        linkbuttoncontainer = new HBox();
        linkbuttoncontainer.getStyleClass().add(CSS_BUTTON_CONTAINER);
        linkbuttoncontainer.getChildren().addAll(addLink, rmLink);

        linkcontainer = new VBox();
        scrollLinks = new ScrollPane(linkcontainer);
        linkcontainer.getStyleClass().add("container_nospacing");

        LinkBox test = new LinkBox("sample text");
        test.getStyleClass().add(CSS_SLIDE);
        linkcontainer.getChildren().add(test);

    }

    private void initHandlers() {
        okayBtn.setOnAction(e -> {
            ok = true;
            comp.setFont(fb.getFontType());
            comp.setFontSize(fb.getFontSize());
            comp.setText(paraText.getText());
            ChangeController.wasChanged();
            this.hide();
        });
    }

    private void placeChildren() {

        paragraphcontainer.getChildren().addAll(paraLabel, paraText);

        VBox center = new VBox();
        center.getChildren().addAll(paragraphcontainer, linkbuttoncontainer, linkcontainer);

        uipositioner.setTop(fb);
        uipositioner.setCenter(center);
        uipositioner.setBottom(okayBtn);
    }

    public boolean isOk() {
        return ok;
    }

    //check the string, if a hyperlink is no longer present it gets deleted
    public String checkChanges() {
        return null;
    }

}
