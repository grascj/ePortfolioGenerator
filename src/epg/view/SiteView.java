/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.ProgramConstants.CSS_SITEVIEW_WEBVIEW;
import static epg.file.JsonCreator.SLASH;
import epg.file.SiteBuilder;
import epg.model.Portfolio;
import java.io.File;
import java.io.IOException;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.WebView;

/**
 *
 * @author cgmp
 */
public class SiteView extends GridPane {

    //need to do some BS to stop javafx webview caching, its really dumb
    static public String CURRENTVIEW_FOLDER = "./currentview/";
    static public String currentfolder;
    static public String view_startpoint;

    WebView wv;
    boolean flag;

    public SiteView() {
        currentfolder = CURRENTVIEW_FOLDER + "a";
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

    public void load(Portfolio portfolio) throws IOException {
        //url of the site folder is then placed into a newly generated folder so its not cached
        updateFolder();
        
        SiteBuilder.buildSite(portfolio, currentfolder);
        
        view_startpoint = currentfolder + SLASH + portfolio.getPages().get(0) + SLASH + "index.html";
        
        wv.getEngine().load("file://" + new File(view_startpoint).getAbsolutePath());
    }

    public void updateFolder() {
        
        
        if(new File(currentfolder).exists())
            SiteBuilder.destroy(new File(currentfolder));
        
        currentfolder += "a";
        
        File current = new File(currentfolder);
        
        if(current.exists())
            SiteBuilder.destroy(current);
        
        current.getAbsoluteFile().mkdir();
    }

}
