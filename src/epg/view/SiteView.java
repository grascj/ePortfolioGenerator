/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_SITEVIEW_WEBVIEW;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.WebView;

/**
 *
 * @author cgmp
 */
public class SiteView extends GridPane{
    
    WebView wv;
    
    public SiteView()
    {
        wv = new WebView();
        wv.getStyleClass().add(CSS_SITEVIEW_WEBVIEW);
        RowConstraints rows = new RowConstraints();
        ColumnConstraints col = new ColumnConstraints();
        rows.vgrowProperty().set(Priority.ALWAYS);
        col.hgrowProperty().set(Priority.ALWAYS);
        this.getColumnConstraints().add(col);
        this.getRowConstraints().add(rows);
        this.getChildren().add(wv);
    }
    
    public void loadURL(String url)
    {
        wv.getEngine().load(url);
    }
   
    
    
    
}
