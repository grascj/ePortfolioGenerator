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
import epg.controller.FileController;
import epg.controller.ModeController;
import epg.error.ErrorHandler;
import epg.model.Portfolio;
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
    boolean canSave;
    boolean exported;

    public PortfolioView(Stage primaryStage) {
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
    }

    private void initWindow() {
        uiPositioner = new BorderPane();
        uiPositioner.setTop(fileTB);
        uiPositioner.setLeft(modeTB);
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
        System.out.println(primaryScene.getStylesheets());
        this.primaryStage.setScene(primaryScene);
        this.primaryStage.show();
    }

    //@todo idiot proof buttons
    public void viewMode() {
        uiPositioner.setCenter(pageEditor);
    }

    public void editMode() {
        uiPositioner.setCenter(siteView);
    }

    public void updateUI() {
        fileTB.updateControls(workingPortfolio);
        modeTB.updateControls(workingPortfolio);
        pageEditor.portfolioChange(workingPortfolio, workingPortfolio.getPages().get(0));

    }

    public void updateToolbars() {

    }

    public void changed() {
        canSave = true;
    }

    public void saved() {
        canSave = false;
    }

    public void exported() {
        exported = true;
    }

    public boolean isChanged() {
        return canSave;
    }

    public boolean isExported() {
        return exported;
    }

    public void changePortfolio(Portfolio portfolio) {
        this.workingPortfolio = portfolio;
        canSave = true;
        exported = false;
        updateUI();

    }
    
    public Portfolio getPortfolio()
    {
        return workingPortfolio;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    

}
