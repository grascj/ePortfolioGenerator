/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg;

import epg.file.HTMLWorker;
import epg.model.ImageComponent;
import epg.model.Page;
import epg.model.VideoComponent;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class EPortfolioGenerator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ImageComponent a = new ImageComponent(200, 200, "url", "pic");
        System.out.println(HTMLWorker.generateImageComponentHTML(a));
        System.out.println(a+"\n\n\n");
        
        
        VideoComponent b = new VideoComponent(200,200,"url","reee.mp4");
        System.out.println(HTMLWorker.generateVideoComponentHTML(b));
        System.out.println(b);
        
        Page c = new Page();
        c.setTitle("lotsoftext");
        System.out.println(HTMLWorker.navBarBuilderHTML(c));
        
        
        
        
        launch(args);
    }
    
}
