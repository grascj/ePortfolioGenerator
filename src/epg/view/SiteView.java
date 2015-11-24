/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_SITEVIEW_WEBVIEW;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
        

        try {
            //System.out.println(getClass().getResource("/sites/site/homepage/index.html").toExternalForm());

            wv.getEngine().load(new File("./sites/site/homepage/index.html").toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(SiteView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Throwable exception = wv.getEngine().getLoadWorker().getException();
    }
    
    public void loadURL(String url)
    {
        wv.getEngine().load(url);
    }
   
    
    
    
}
