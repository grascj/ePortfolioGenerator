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

import epg.ProgramConstants.COLOR;
import epg.ProgramConstants.LAYOUT;
import java.util.ArrayList;


public class Page {
    
    
    //@TODO fonts?
    LAYOUT layout;
    COLOR colors;
    String bannerURL;
    String footerURL;
    ArrayList<Component> components;
    
    public Page()
    {
        
    }
    
    
    
    
    
}
