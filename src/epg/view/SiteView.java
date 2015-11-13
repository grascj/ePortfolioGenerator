/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

/**
 *
 * @author cgmp
 */
public class SiteView extends HBox{
    
    WebView wv;
    
    public SiteView()
    {
        wv = new WebView();
        this.getChildren().add(wv);
    }
    
    public void loadURL(String url)
    {
        wv.getEngine().load(url);
    }
   
    
    
    
}
