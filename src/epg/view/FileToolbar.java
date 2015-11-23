/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_FILETOOLBAR_BUTTONS;
import static epg.ProgramConstants.ICON_BOOK_OPEN;
import static epg.ProgramConstants.ICON_FLOPPYDISK;
import static epg.ProgramConstants.ICON_FOLDER_PLUS;
import static epg.ProgramConstants.ICON_PAGE_ARROW;
import static epg.ProgramConstants.ICON_SAVEAS;
import static epg.ProgramConstants.ICON_X;
import static epg.ProgramConstants.TT_FILE_EXIT;
import static epg.ProgramConstants.TT_FILE_EXPORT;
import static epg.ProgramConstants.TT_FILE_LOAD;
import static epg.ProgramConstants.TT_FILE_NEW;
import static epg.ProgramConstants.TT_FILE_SAVE;
import static epg.ProgramConstants.TT_FILE_SAVEAS;
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
    Button saveAsButton;
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
        
        newButton = ViewHelper.initChildButton(CSS_FILETOOLBAR_BUTTONS, ICON_FOLDER_PLUS, TT_FILE_NEW);
        loadButton = ViewHelper.initChildButton(CSS_FILETOOLBAR_BUTTONS, ICON_BOOK_OPEN, TT_FILE_LOAD);
        saveButton = ViewHelper.initChildButton(CSS_FILETOOLBAR_BUTTONS, ICON_FLOPPYDISK, TT_FILE_SAVE);
        saveAsButton = ViewHelper.initChildButton(CSS_FILETOOLBAR_BUTTONS, ICON_SAVEAS, TT_FILE_SAVEAS);
        exportButton = ViewHelper.initChildButton(CSS_FILETOOLBAR_BUTTONS, ICON_PAGE_ARROW, TT_FILE_EXPORT);
        exitButton = ViewHelper.initChildButton(CSS_FILETOOLBAR_BUTTONS, ICON_X, TT_FILE_EXIT);
    }
    
    private void initHandlers()
    {
        newButton.setOnAction(e->{fc.handleNew();});
        loadButton.setOnAction(e->{fc.handleLoad();});
        saveButton.setOnAction(e->{fc.handleSave();});
        saveAsButton.setOnAction(e->{fc.handleSaveAs();});
        exportButton.setOnAction(e->{fc.handleExport();});
        exitButton.setOnAction(e->{fc.handleExit();});
    }
    
    private void placeChildren()
    {
        this.getChildren().addAll(newButton, loadButton, saveButton, saveAsButton, exportButton, exitButton);
    }

    public void updateControls(Portfolio portfolio)
    {
        
    }
    
    
    
}
