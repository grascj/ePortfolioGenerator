/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.ProgramConstants.FONT;
import java.util.ArrayList;

/**
 *
 * @author cgmp
 */
public class TextComponent extends Component{

    FONT font;
    
    ArrayList<TextHolder> text;
    
    
    //@TODO need clarifications
    public TextComponent(int width, int length, FONT font, ArrayList<TextHolder> text) {
        super(width, length);
        this.font = font;
        this.text = text;
    }

    @Override
    public String getFile(){return null;}
    @Override
    public int getWidth(){return -1;}
    @Override
    public int getLength(){return -1;}
    
    
    
    
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
    
    //@todo
    @Override
    public String getDisplayText() {
        return "A Text which is: " + width + "px wide and " + length + "px tall. The title is: ";
    }    
}
