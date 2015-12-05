/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_FILETOOLBAR;
import static epg.ProgramConstants.CSS_MODETOOLBAR;
import static epg.ProgramConstants.CSS_PAGEEDITOR;
import static epg.ProgramConstants.CSS_SITEVIEW;
import static epg.ProgramConstants.PATH_STYLESHEET;
import epg.controller.ChangeController;
import epg.controller.FileController;
import epg.controller.ModeController;
import epg.error.ErrorHandler;
import epg.model.Portfolio;
import epg.view.ModeToolbar.MODE_STATE;
import static epg.view.ModeToolbar.MODE_STATE.EDIT;
import static epg.view.ModeToolbar.MODE_STATE.VIEW;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class PortfolioView {

    //ui stuff
    Stage primaryStage;
    Scene primaryScene;

    //ui components
    FileToolbar fileTB;
    ModeToolbar modeTB;
    SiteView siteView;
    PageEditor pageEditor;

    //positioning
    BorderPane uiPositioner;

    //error Handling
    ErrorHandler eH;

    //data
    Portfolio workingPortfolio;

    //states
    boolean saved;
    boolean exported;
    boolean newportfolio;

    public PortfolioView(Stage primaryStage) {
        ChangeController.initChangeController(this);
        this.primaryStage = primaryStage;
        workingPortfolio = new Portfolio();
        initUI();
        initWindow();
    }

    private void initUI() {
        fileTB = new FileToolbar(new FileController(this));
        fileTB.getStyleClass().add(CSS_FILETOOLBAR);
        modeTB = new ModeToolbar(new ModeController(this));
        modeTB.getStyleClass().add(CSS_MODETOOLBAR);
        pageEditor = new PageEditor(workingPortfolio, primaryStage);
        pageEditor.getStyleClass().add(CSS_PAGEEDITOR);
        siteView = new SiteView();
        siteView.getStyleClass().add(CSS_SITEVIEW);
        pageEditor.update();
        newportfolio();
    }

    private void initWindow() {
        uiPositioner = new BorderPane();
        uiPositioner.setTop(fileTB);
        uiPositioner.setLeft(modeTB);
        modeTB.state = MODE_STATE.EDIT;
        modeTB.updateControls(workingPortfolio);
        uiPositioner.setCenter(pageEditor);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.primaryStage.setX(bounds.getMinX());
        this.primaryStage.setY(bounds.getMinY());
        this.primaryStage.setWidth(bounds.getWidth());
        this.primaryStage.setHeight(bounds.getHeight());

        primaryScene = new Scene(uiPositioner);
        primaryScene.getStylesheets().add(PATH_STYLESHEET);
        this.primaryStage.setScene(primaryScene);
        this.primaryStage.show();
    }

    public void viewMode() {
        modeTB.state = EDIT;
        modeTB.updateControls(workingPortfolio);
        uiPositioner.setCenter(pageEditor);
    }

    public void editMode() {
        modeTB.state = VIEW;
        modeTB.updateControls(workingPortfolio);
        uiPositioner.setCenter(siteView);
    }

    public void updateUI() {
        fileTB.updateControls(workingPortfolio);
        modeTB.updateControls(workingPortfolio);
        modeTB.refreshSite();
        pageEditor.portfolioChange(workingPortfolio, workingPortfolio.getPages().get(0));
    }

    public void updateToolbars() {

    }

    public void changePortfolio(Portfolio portfolio) {
        this.workingPortfolio = portfolio;
        updateUI();

    }

    public Portfolio getPortfolio() {
        return workingPortfolio;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void loadSiteView() {

        try {
            siteView.load(workingPortfolio);
        } catch (IOException ex) {
            Logger.getLogger(PortfolioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void wasChanged() {
        newportfolio = false;
        exported = false;
        saved = false;
        updateFileToolbar();
    }
    
    
    public void loadstate()
    {
        saved = true;
        newportfolio = false;
        exported = false;
        updateUI();
        updateFileToolbar();
    }
    
    public void saved()
    {
        saved = true;
        newportfolio = false;
        updateFileToolbar();
    }
    
    public void exported()
    {
        exported = true;
        newportfolio = false;
        updateFileToolbar();
    }
    
    public void newportfolio()
    {
        exported = true;
        saved = true;
        newportfolio = true;
        updateFileToolbar();
    }

    public void updateFileToolbar() {
        fileTB.exportButton.setDisable(exported);
        fileTB.saveButton.setDisable(saved);
        fileTB.newButton.setDisable(newportfolio);
        fileTB.saveAsButton.setDisable(newportfolio);
    }

    public boolean isSaved() {
        return saved;
    }

    
    
    
}
