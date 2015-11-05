/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

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
    
     @Override
    public String getFile() {return null;}
    
    
    

    @Override
    public void editPrompt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String htmlify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
