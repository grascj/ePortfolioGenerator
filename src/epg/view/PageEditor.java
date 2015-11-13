/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.controller.PageViewController;
import epg.controller.SiteToolbarController;
import epg.model.Page;
import epg.model.Portfolio;
import javafx.scene.layout.HBox;

/**
 *
 * @author cgmp
 */
public class PageEditor extends HBox {
    
    
    
    SiteToolbar siteToolbar;
    PageView pageView;
    Page selectedPage;
    Portfolio portfolio;
    
    
    
    public PageEditor(Portfolio portfolio)
    {
        this.portfolio = portfolio;
        initUI();
        placeChildren();
    }
    
    public void initUI()
    {
        siteToolbar = new SiteToolbar(new SiteToolbarController(this));
        pageView = new PageView(new PageViewController(this));
    }
    public void placeChildren()
    {
        this.getChildren().addAll(siteToolbar, pageView);
    }
    
    
    public void removeSelectedPage()
    {
        portfolio.getPages().remove(selectedPage);
    }
    
    public void updatePage()
    {
        
    }
    
    public void changePage(Page page)
    {
        
    }
    
    
    
}
