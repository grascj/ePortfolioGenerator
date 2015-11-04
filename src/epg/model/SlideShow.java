/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import java.util.ArrayList;

/**
 *
 * @author cgmp
 */
public class SlideShow {
    ArrayList<Slide> slides;
    int numSlides;
    Slide selectedSlide;
    
    public SlideShow(ArrayList<Slide> slides, int numSlides) {
        this.slides = slides;
        this.numSlides = numSlides;
    }
    
    public ArrayList<Slide> getSlides() {
        return slides;
    }

    public void setSlides(ArrayList<Slide> slides) {
        this.slides = slides;
    }

    public int getNumSlides() {
        return numSlides;
    }

        
    //@TODO
    //METHODS
    
    public void addSlide()
    {
        
    }
    
    public void removeSlide()
    {
        
    }
    
    
    
}
