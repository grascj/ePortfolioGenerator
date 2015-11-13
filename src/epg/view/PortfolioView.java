/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.FileController;
import epg.controller.ModeController;
import epg.error.ErrorHandler;
import epg.model.Page;
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
   
   
   
   public PortfolioView(Stage primaryStage)
   {
       workingPortfolio = new Portfolio();
       initUI();
       initWindow(primaryStage);
   }
   
   public void initUI()
   {
       fileTB = new FileToolbar(new FileController(this));
       modeTB = new ModeToolbar(new ModeController(this));
       pageEditor = new PageEditor(workingPortfolio);
       siteView = new SiteView();
       pageEditor.updatePage();
   }
   public void initWindow(Stage primaryStage)
   {
       uiPositioner = new BorderPane();
       uiPositioner.setTop(fileTB);
       uiPositioner.setLeft(modeTB);
       uiPositioner.setCenter(pageEditor);


        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
       
       
       
       primaryScene = new Scene(uiPositioner);
       
       primaryStage.setScene(primaryScene);
       //@todo css
       primaryStage.show();
   }

   //@todo idiot proof buttons
   public void viewMode()
   {
       uiPositioner.setCenter(pageEditor);
   }
   public void editMode()
   {
       uiPositioner.setCenter(siteView);
   }
   
   
   
   public void updateUI()
   {
       
   }
   
   public void updateToolbars()
   {
       
   }
   
   public void changed()
   {
       canSave = true;
   }
   public void saved()
   {
       canSave = false;
   }
   public void exported()
   {
       exported = true;
   }
   
   
   public boolean isChanged()
   {return canSave;}
   public boolean isExported()
   {return exported;}
   
   
   public void reset()
   {
       canSave = true;
       exported = false;
   }
   
   
   
   
   
   
   
   
   
}
