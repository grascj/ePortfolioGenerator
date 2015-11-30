/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg;

import epg.view.PortfolioView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class EPortfolioGenerator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       primaryStage.setTitle("ePortfolio Generator");
        PortfolioView pv = new PortfolioView(primaryStage);
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
