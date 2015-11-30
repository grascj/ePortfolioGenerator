/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import static epg.ProgramConstants.DEFAULT_IMG;
import static epg.ProgramConstants.DEFAULT_IMG_NAME;

/**
 *
 * @author cgmp
 */
public class Slide {
    
    public String caption;
    public String image;
    public String imageURL;
    
    
   

    public Slide(String caption, String image, String imageURL) {
        this.caption = caption;
        this.image = image;
        this.imageURL = imageURL;
    }
    
    public Slide()
    {
        this.caption = "";
        this.image = DEFAULT_IMG_NAME;
        this.imageURL = DEFAULT_IMG;
    }
            
    
    
    
    
    
    
}
