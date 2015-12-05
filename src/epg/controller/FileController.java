/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.ProgramConstants.PATH_SAVES;
import static epg.ProgramConstants.PATH_SITES;
import epg.error.ErrorHandler;
import epg.file.JsonCreator;
import epg.file.SiteBuilder;
import epg.model.Portfolio;
import epg.prompts.PromptToSave;
import epg.prompts.SaveAsPrompt;
import epg.view.PortfolioView;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author cgmp
 */
public class FileController {

    PortfolioView pv;

    public FileController(PortfolioView pv) {
        this.pv = pv;
    }

    
    //@todo hide unless its being edited?
    public void handleNew() {
        if (!pv.isSaved()) {
            PromptToSave popup = new PromptToSave();
            if (popup.isOk()) {
                handleSave();
            }
        }
        pv.changePortfolio(new Portfolio());
        pv.newportfolio();
    }

    public void handleLoad() {
        if (!pv.isSaved()) {
            PromptToSave popup = new PromptToSave();
            if (popup.isOk()) {
                handleSave();
            }
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON files", "*.json"));
        fileChooser.setInitialDirectory(new File(PATH_SAVES));
        File selectedFile = fileChooser.showOpenDialog(pv.getPrimaryStage());
        if (selectedFile != null) {
            try {
                pv.changePortfolio(JsonCreator.loadPortfolio(selectedFile.getPath()));
                pv.loadstate();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void handleSave() {
        if (pv.getPortfolio().getFileName().equals("")) {
            handleSaveAs();
        } else {
            try {
                JsonCreator.savePortfolio(pv.getPortfolio());
                pv.saved();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void handleExport() {
        if (ErrorHandler.isValidPortfolio(pv.getPortfolio())) {
            try {
                if (!pv.getPortfolio().getStudentName().equals("")) {
                    SiteBuilder.buildSite(pv.getPortfolio(), PATH_SITES + pv.getPortfolio().getStudentName().replaceAll(" ", "_"));
                } else {
                    SiteBuilder.buildSite(pv.getPortfolio(), PATH_SITES + "newsite");
                }
                pv.exported();
                pv.loadSiteView();
                pv.editMode();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void handleExit() {
        if (!pv.isSaved()) {
            PromptToSave popup = new PromptToSave();
            if (popup.isOk()) {
                handleSave();
            }
        }
        System.exit(0);
    }

    public void handleSaveAs() {
        SaveAsPrompt a = new SaveAsPrompt(pv.getPortfolio(), "Enter a File Name:", "Enter the new File Name:");
        if (a.isOk()) {
            handleSave();
        }
    }

}
