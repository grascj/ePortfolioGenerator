/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.model.Component;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author cgmp
 */
class ComponentViewer extends HBox{
    //@todo
    //has a list of components that it turns into component views
    
    ScrollPane componentPane;
    ArrayList<Component> componentList;
    Component Selection;            
    
    public ComponentViewer()
    {
        componentPane = new ScrollPane(this);
        
    }
    
    public void update(ArrayList<Component> componentList)
    {
        this.componentList = componentList;
        
        if(!componentList.isEmpty())
            populateComponents();
        
    }
    

    
    public void populateComponents()
    {
        for(Component comp : componentList)
        {
            this.getChildren().add(new ComponentView(comp));
        }
    }
    
    
    
    
    
    
    
    
    
}
