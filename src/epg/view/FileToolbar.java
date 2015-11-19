/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_FILETOOLBAR_BUTTONS;
import epg.controller.FileController;
import epg.model.Portfolio;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author cgmp
 */
public class FileToolbar extends FlowPane{
    
    Button newButton;
    Button loadButton;
    Button saveButton;
    Button exportButton;
    Button exitButton;
    FileController fc;
    
    
    
    public FileToolbar(FileController fc)
    {
        this.fc = fc;
        initButtons();
        initHandlers();
        placeChildren();
    }
    
    private void initButtons()
    {
        newButton = new Button("new");
        newButton.getStyleClass().add(CSS_FILETOOLBAR_BUTTONS);
        loadButton = new Button("load");
        loadButton.getStyleClass().add(CSS_FILETOOLBAR_BUTTONS);
        saveButton = new Button("save");
        saveButton.getStyleClass().add(CSS_FILETOOLBAR_BUTTONS);
        exportButton = new Button("export");
        exportButton.getStyleClass().add(CSS_FILETOOLBAR_BUTTONS);
        exitButton = new Button("exit");
        exitButton.getStyleClass().add(CSS_FILETOOLBAR_BUTTONS);

        //@todo do gridpane BS
        
    }
    
    private void initHandlers()
    {
        newButton.setOnAction(e->{fc.handleNew();});
        loadButton.setOnAction(e->{fc.handleLoad();});
        saveButton.setOnAction(e->{fc.handleSave();});
        exportButton.setOnAction(e->{fc.handleExport();});
        exitButton.setOnAction(e->{fc.handleExit();});
    }
    
    private void placeChildren()
    {
        this.getChildren().addAll(newButton, loadButton, saveButton, exportButton, exitButton);
    }
    
    
    public void updateControls(Portfolio portfolio)
    {
        
    }
    
    
    
}
