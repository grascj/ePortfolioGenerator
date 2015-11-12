/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.FileController;
import epg.model.Portfolio;
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
    }
    
    private void initButtons()
    {
        newButton = new Button();
        loadButton = new Button();
        saveButton = new Button();
        exportButton = new Button();
        exitButton = new Button();
    }
    
    private void initHandlers()
    {
        newButton.setOnAction(e->{fc.handleNew();});
        loadButton.setOnAction(e->{fc.handleLoad();});
        saveButton.setOnAction(e->{fc.handleSave();});
        exportButton.setOnAction(e->{fc.handleExport();});
        exitButton.setOnAction(e->{fc.handleExit();});
    }
    
    public void updateControls(Portfolio portfolio)
    {
        
    }
    
    
    
}
