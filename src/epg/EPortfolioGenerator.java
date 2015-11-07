/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg;

import epg.file.HTMLWorker;
import epg.model.ImageComponent;
import epg.model.Page;
import epg.model.Slide;
import epg.model.SlideShow;
import epg.model.SlideShowComponent;
import epg.model.VideoComponent;
import java.util.ArrayList;
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
        
        ImageComponent a = new ImageComponent(200, 200, "url", "pic.jpg");
        System.out.println(HTMLWorker.generateImageComponentHTML(a));
       
        
        //@TODO LAYUOUTS MUST BE PUT INTO THE ENUM IN ORDER SPECIFIED BY THE JSON. JSON HAS TO HAVE THE INT VALUE
        VideoComponent b = new VideoComponent(200,200,"url","reee.mp4");
        System.out.println(HTMLWorker.generateVideoComponentHTML(b));
        
        Page c = new Page();
        c.setTitle("lotsoftext");
        System.out.println(HTMLWorker.navBarBuilderHTML(c));
        
        
        //String caption, String image, String imageURL
        
        //ArrayList<SlideShow> ss = new ArrayList<SlideShow>();
        ArrayList<Slide> sl = new ArrayList<Slide>();
        sl.add(new Slide("herro","pic.jpg","url"));
        sl.add(new Slide("le bayeux mmaymay","images.jpg","url"));
        SlideShow ss = new SlideShow(sl,2,"utah");
                
        
        SlideShowComponent d = new SlideShowComponent(400,400,ss);
        System.out.println(HTMLWorker.generateSlideShowComponentHTML(d,0));
        
        launch(args);
    }
    
}
