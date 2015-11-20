/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_COMPONENTPANE;
import static epg.ProgramConstants.CSS_COMPONENTVIEW;
import static epg.ProgramConstants.CSS_COMPONENTVIEW_SELECTED;
import epg.model.Component;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cgmp
 */
class ComponentViewer extends ScrollPane{
    //@todo
    //has a list of components that it turns into component views
    
    VBox componentPane;
    ArrayList<Component> componentList;
    Component selection;            
    
    public ComponentViewer()
    {
        componentPane = new VBox();
        componentPane.getStyleClass().add(CSS_COMPONENTPANE);
        
        this.getStyleClass().add(CSS_COMPONENTPANE);
        this.setContent(componentPane);
    }
    
    public void update(ArrayList<Component> componentList)
    {
        this.componentList = componentList;
        
        if(!componentList.isEmpty())
        {
            componentPane.getChildren().clear();
            populateComponents();
        }
    }
    

    
    public void populateComponents()
    {
        for(Component comp : componentList)
        {
            if(comp == selection)
                componentPane.getChildren().add(new ComponentView(comp, CSS_COMPONENTVIEW_SELECTED));
            else
                componentPane.getChildren().add(new ComponentView(comp, CSS_COMPONENTVIEW));

            
        }
    }
    
    
    
    
    
    
    
    
    
}
