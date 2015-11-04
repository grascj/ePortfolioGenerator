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
           
    public abstract void editPrompt();
    public abstract void display();
    public abstract void htmlify();    
    
}
