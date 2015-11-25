/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_COMPONENTPANE;
import static epg.ProgramConstants.CSS_COMPONENTVIEW;
import static epg.ProgramConstants.CSS_COMPONENTVIEW_SELECTED;
import static epg.ProgramConstants.CSS_COMP_SCROLLPANE;
import epg.model.Component;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cgmp
 */
class ComponentViewer extends ScrollPane {
    //@todo
    //has a list of components that it turns into component views

    VBox componentPane;
    ArrayList<Component> componentList;
    public Component selection;
    PageView owner;

    public ComponentViewer(PageView owner) {
        this.owner = owner;
        componentPane = new VBox();
        componentPane.getStyleClass().add(CSS_COMPONENTPANE);

        this.getStyleClass().add(CSS_COMP_SCROLLPANE);
        this.setContent(componentPane);
    }

    public void update(ArrayList<Component> componentList) {

        componentPane.getChildren().clear();
        this.componentList = componentList;
        populateComponents();

    }

    public void populateComponents() {
        for (Component comp : componentList) {
            ComponentView cv = new ComponentView(comp);

            cv.setOnMouseClicked(e -> {
                selection = cv.getComponent();
                owner.idiotProofTB();
                update(this.componentList);
            });

            if (comp == selection) {
                cv.getStyleClass().add(CSS_COMPONENTVIEW_SELECTED);
            } else {
                cv.getStyleClass().add(CSS_COMPONENTVIEW);
            }

            componentPane.getChildren().add(cv);
        }
    }

}
