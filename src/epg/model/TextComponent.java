/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.ProgramConstants.FONT;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author cgmp
 */
public abstract class TextComponent extends Component{

    FONT font;    
    int fontSize;
    
    public TextComponent(FONT font, int fontSize) {
        super(300, 300);
        this.font = font;
        this.fontSize = fontSize;
    }

    public FONT getFont() {
        return font;
    }

    public void setFont(FONT font) {
        this.font = font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    
    
        
    @Override
    public ArrayList<File> getMedia(){return null;}
    @Override
    public String getFile(){return null;}
    @Override
    public int getWidth(){return -1;}
    @Override
    public int getLength(){return -1;}
    
    
}
