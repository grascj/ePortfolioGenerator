/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author cgmp
 */
public class ViewHelper {
    
    
    
    static public Button initChildButton(String style, String icon, String tooltip)
    {
        Button node = new Button();
        node.getStyleClass().add(style);
        node.setGraphic(new ImageView(new Image("file:" + icon)));
        node.setTooltip(new Tooltip(tooltip));
        return node;
                
    }    
    
    
    
}
