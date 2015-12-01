/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.ProgramConstants.PATH_SAVES;
import static epg.ProgramConstants.PATH_SITES;
import epg.file.JsonCreator;
import epg.file.SiteBuilder;
import epg.model.Portfolio;
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

    //@todo idiot proof and disable button sometimes
    //I think enums are the way to go, use enums -> have an update function
    public FileController(PortfolioView pv) {
        this.pv = pv;
    }

    public void handleNew() {
        pv.changePortfolio(new Portfolio());
        pv.newportfolio();
    }

    public void handleLoad() {

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
        try {
            JsonCreator.savePortfolio(pv.getPortfolio());
            pv.saved();
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleExport() {
        try {
            if (!pv.getPortfolio().getStudentName().equals("")) {
                SiteBuilder.buildSite(pv.getPortfolio(), PATH_SITES + pv.getPortfolio().getStudentName().replaceAll(" ", "_"));
            } else {
                SiteBuilder.buildSite(pv.getPortfolio(), PATH_SITES + "newsite");
            }
            pv.exported();

        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleExit() {
        System.exit(0);
    }

    public void handleSaveAs() {
        SaveAsPrompt a = new SaveAsPrompt(pv.getPortfolio());
        if (a.isOk()) {
            handleSave();
        }
    }

}
