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
public abstract class Component {
    
    int width;
    int length;
    
    public Component(int width, int length)
    {
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
           
    public abstract String getFile();
    
    public abstract void editPrompt();
    public abstract void display();
    public abstract String htmlify();    
    
}
