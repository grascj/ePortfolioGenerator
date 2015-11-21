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
    public Slide selectedSlide;
    public String title;
    
    public SlideShow(ArrayList<Slide> slides, int numSlides, String title) {
        this.slides = slides;
        this.numSlides = numSlides;
        this.title = title;
    }

    public SlideShow() {
        slides = new ArrayList<Slide>();
        numSlides = 0;
        selectedSlide = null;
        title = "";
        
    }
    
    
    
    public String getTitle()
    {
        return title;
    }
    
    public ArrayList<Slide> getSlides() {
        return slides;
    }

    public void setSlides(ArrayList<Slide> slides) {
        this.slides = slides;
        numSlides = slides.size() + 1;
    }

    public int getNumSlides() {
        return numSlides;
    }

        
    //@TODO
    //METHODS
    
    public void addSlide()
    {
        slides.add(new Slide());
        numSlides++;
    }
    
    public void removeSlide()
    {
        slides.remove(selectedSlide);
        numSlides--;
    }
    
    
    
}
