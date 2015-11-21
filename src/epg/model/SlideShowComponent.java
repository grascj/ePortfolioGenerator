/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.file.HTMLWorker;

/**
 *
 * @author cgmp
 */
public class SlideShowComponent extends Component{

    SlideShow slideshow;
    
    public SlideShowComponent(int width, int length, SlideShow slideshow) {
        super(width, length);
        this.slideshow = slideshow;
    }

    public SlideShowComponent() {
        super(200, 200);
        slideshow = new SlideShow();
        slideshow.title = "";
    }
    
    
    public SlideShow getslideshow()
    {
        return slideshow;
    }
    
    
    public void setslideshow(SlideShow ss)
    {
        this.slideshow = ss;
    }
    
    
     @Override
    public String getFile() {return null;}
    
    
    

    @Override
    public void editPrompt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    


    @Override
    public String htmlify() {
        return HTMLWorker.generateSlideShowComponentHTML(this, slideshow.getNumSlides());
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDisplayText() {
        return "A Slide Show which is: " + width + "px wide and " + length + "px tall. The title is: " + slideshow.getTitle();
    }

   
    
}
