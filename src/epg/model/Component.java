/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import javax.json.JsonObject;

/**
 *
 * @author cgmp
 */
public abstract class Component {
    
    int width;
    int height;
    
    public Component(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return height;
    }

    public void setLength(int length) {
        this.height = length;
    }
           
    public abstract String getFile();
    
    public abstract String htmlify();
    public abstract JsonObject jsonify();
    public abstract String getDisplayText();
}
