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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class PageEditor extends VBox {
    
    
    Stage primaryStage;
    SiteToolbar siteToolbar;
    PageView pageView;
    Page selectedPage;
    Portfolio portfolio;
    
    
    
    public PageEditor(Portfolio portfolio, Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.portfolio = portfolio;
        initUI();
        placeChildren();
        selectedPage = portfolio.getPages().get(0);
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
        //@todo error handling for out of bounds
        portfolio.getPages().remove(selectedPage);
        selectedPage = portfolio.getPages().get(0);
        updatePage();

    }
    
    public void updatePage()
    {
        pageView.update(selectedPage);
    }
    
    public void update()
    {
        pageView.update(selectedPage);
        siteToolbar.update(portfolio.getPages(), portfolio.getPages().indexOf(selectedPage));
    }
    
    
    public void changePage(int index)
    {
        selectedPage = portfolio.getPages().get(index);
        updatePage();
    }
    
    public Portfolio getPortfolio()
    {
        return portfolio;
    }
    
    public Page getPage()
    {
        return selectedPage;
    }
    
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }
    
    
    public void setSelectedPage(int index)
    {
       selectedPage = portfolio.getPages().get(index);
    }
    
    public void updateTitle()
    {
        siteToolbar.updateCurrentTitle();
    }
    
}
