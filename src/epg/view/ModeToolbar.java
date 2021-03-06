/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_MODETOOLBAR_BUTTONS;
import static epg.ProgramConstants.ICON_EDIT;
import static epg.ProgramConstants.ICON_EYE;
import static epg.ProgramConstants.TT_MODE_EDIT;
import static epg.ProgramConstants.TT_MODE_SITE;
import epg.controller.ModeController;
import epg.model.Portfolio;
import static epg.view.ModeToolbar.MODE_STATE.VIEW;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author cgmp
 */
class ModeToolbar extends GridPane {

    enum MODE_STATE {

        VIEW, EDIT
    };

    Button siteButton;
    Button editorButton;
    ModeController mc;
    MODE_STATE state;

    public ModeToolbar(ModeController mc) {
        this.mc = mc;
        initButtons();
        initHandlers();
        placeChildren();

    }

    private void initButtons() {

        siteButton = ViewHelper.initChildButton(CSS_MODETOOLBAR_BUTTONS, ICON_EYE, TT_MODE_SITE);
        editorButton = ViewHelper.initChildButton(CSS_MODETOOLBAR_BUTTONS, ICON_EDIT, TT_MODE_EDIT);
    }

    private void initHandlers() {
        siteButton.setOnAction(e -> {
            mc.handleEditMode();
        });
        editorButton.setOnAction(e -> {
            mc.handleSiteMode();
        });
    }

    private void placeChildren() {
        //this.getChildren().addAll(siteButton, editorButton);

        RowConstraints rows = new RowConstraints();
        rows.setVgrow(Priority.ALWAYS);

        this.getRowConstraints().add(rows);
        this.getRowConstraints().add(rows);

        this.add(siteButton, 0, 0);

        this.add(editorButton, 0, 1);
    }

    public void updateControls(Portfolio portfolio) {
        switch (state) {
            case VIEW: {
                siteButton.setDisable(true);
                editorButton.setDisable(false);
                break;
            }
            case EDIT: {
                siteButton.setDisable(false);
                editorButton.setDisable(true);
                break;
            }
        }
    }

    public void refreshSite() {
        if (state == VIEW) {
            mc.handleEditMode();
        }
    }

}
