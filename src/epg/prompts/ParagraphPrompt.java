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
import static epg.ProgramConstants.CSS_SLIDE_SELECTED;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.ICON_MINUS;
import static epg.ProgramConstants.ICON_PLUS;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_OK;
import epg.controller.ChangeController;
import epg.error.ErrorHandler;
import epg.model.Hyperlink;
import epg.model.ParagraphComponent;
import static epg.view.ViewHelper.initChildButton;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
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

    BorderPane center;
    HBox linkbuttoncontainer;
    Button addLink;
    Button rmLink;

    ScrollPane scrollLinks;
    VBox linkcontainer;

    //SELECTION
    Hyperlink selection;

    //data
    ParagraphComponent comp;
    String text;
    ArrayList<Hyperlink> links;
    boolean ok;

    public class LinkBox extends HBox {

        Label text;
        Label urlLabel;
        TextField urlField;
        Hyperlink link;

        public LinkBox(Hyperlink link) {
            this.link = link;
            this.text = new Label(link.txt);
            urlLabel = new Label("     Enter URL: ");
            urlField = new TextField();

            urlField.setOnKeyReleased(e -> {
                this.link.url = urlField.getText();
            });

            this.getChildren().addAll(this.text, urlLabel, urlField);
            this.setStyle("-fx-padding: 10px; -fx-alignment: center;");
        }

    }

    public ParagraphPrompt(ParagraphComponent comp) {
        selection = null;
        this.setTitle("Add Paragraph");
        initModality(Modality.APPLICATION_MODAL);
        this.comp = comp;
        links = new ArrayList<>();
        for (Hyperlink link : comp.getLinks()) {
            links.add(link.copy());
        }
        ok = false;
        initUI();
        initHandlers();
        placeChildren();
        uipositioner.getStyleClass().add(CSS_CONTAINER);
        fb.getStyleClass().add(CSS_CONTAINER);
        paragraphcontainer.getStyleClass().add("nucontainer");
        Scene promptbody = new Scene(uipositioner, 550, 600);
        promptbody.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        populateHyperlinks();
        this.setScene(promptbody);
        this.showAndWait();
    }

    private void initUI() {
        uipositioner = new BorderPane();
        fb = new FontBox(comp.getFont(), comp.getFontSize());

        paragraphcontainer = new VBox();
        paraLabel = new Label("Enter the text for the Paragraph:");
        paragraphcontainer.setAlignment(Pos.CENTER);
        paraLabel.setTextAlignment(TextAlignment.CENTER);
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
        linkcontainer.getStyleClass().add("container_nospacing");
        scrollLinks = new ScrollPane(linkcontainer);
        scrollLinks.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollLinks.getStyleClass().add(CSS_CONTAINER);
    }

    private void initHandlers() {

        paraText.textProperty().addListener(e -> {
            checkLinkValidity();
        });

        rmLink.setOnAction(e -> {
            links.remove(selection);
            selection = null;
            populateHyperlinks();
        });
        addLink.setOnAction(e -> {
            makeHyperlink();

        });
        okayBtn.setOnAction(e -> {
            ok = true;
            comp.setFont(fb.getFontType());
            comp.setFontSize(fb.getFontSize());
            comp.setText(paraText.getText());
            comp.setLinks(links);
            //@todo check if all links exist
            ChangeController.wasChanged();
            this.hide();
        });
    }

    private void placeChildren() {

        paragraphcontainer.getChildren().addAll(paraLabel, paraText);

        center = new BorderPane();
        center.getStyleClass().add("container_nospacing");

        VBox box = new VBox();
        box.getChildren().addAll(paragraphcontainer, linkbuttoncontainer);
        box.getStyleClass().add(CSS_CONTAINER);

        center.setTop(box);
        center.setCenter(scrollLinks);

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

    public void makeHyperlink() {
        //@todo stop empty links
        if (!paraText.getSelectedText().trim().equals("")) {
            Hyperlink newlink = new Hyperlink(paraText.getSelection(), paraText.getSelectedText(), "");
            if (!checkOverlap(newlink)) {
                links.add(newlink);
                populateHyperlinks();
            }
        }
    }

    public void populateHyperlinks() {
        if (selection == null) {
            rmLink.setDisable(true);
        } else {
            rmLink.setDisable(false);
        }

        linkcontainer.getChildren().clear();
        for (Hyperlink link : links) {
            LinkBox a = new LinkBox(link);
            a.setOnMouseClicked(e -> {
                selection = link;
                populateHyperlinks();
            });
            if (link != selection) {
                a.getStyleClass().add(CSS_SLIDE);
            } else {
                a.getStyleClass().add(CSS_SLIDE_SELECTED);
            }

            linkcontainer.getChildren().add(a);
        }
    }

    //true means overlap 
    //false means no overlap
    public boolean checkOverlap(Hyperlink newlink) {
        //start is in the substring
        //end is not
        for (Hyperlink link : links) {
            if (compareLinkOverlap(newlink, link)) {
                ErrorHandler.processError("Link Error", "There cannot be overlapping Hyperlinks.");
                return true;
            }
        }
        return false;
    }

    //true means overlap
    //false means no overlap
    public boolean compareLinkOverlap(Hyperlink a, Hyperlink b) {
        boolean flag = false;
        if (a.range.getStart() <= b.range.getEnd() - 1 && b.range.getStart() <= a.range.getEnd() - 1) {
            flag = true;
        }

        return flag;
    }

    public void checkLinkValidity() {
        boolean flag = false;
        ArrayList<Hyperlink> stuffToRemove = new ArrayList<>();
        
        for (Hyperlink link : links) {
            if (paraText.getText().contains(link.txt)) {
                int a = paraText.getText().indexOf(link.txt);
                link.range = new IndexRange(a, a+link.txt.length());
            } else {
                stuffToRemove.add(link);
                flag = true;
            }
        }
        
        links.removeAll(stuffToRemove);
        
        populateHyperlinks();
        
        if (flag) {
            ErrorHandler.processError("Link Error", "A Link was removed because the text no longer exists.");
        }
    }

}
