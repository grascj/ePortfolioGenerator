/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.model.Portfolio;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
   siteEditor siteEditor;

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
       
   }
   
   public void initUI()
   {
       
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
