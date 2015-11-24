/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg;

import epg.ProgramConstants.FONT;
import epg.model.HeaderComponent;
import epg.model.Item;
import epg.model.ListComponent;
import java.util.ArrayList;

/**
 *
 * @author cgmp
 */
public class ModelDriver {
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        HeaderComponent hcomp = new HeaderComponent();
        hcomp.setFont(FONT.Iconsolata);
        hcomp.setFontSize(20);
        hcomp.setText("hello this is a test header");
        System.out.println(hcomp.htmlify()+ "\n\n\n\n\n");
        
        ListComponent lcomp = new ListComponent();
        lcomp.setFont(FONT.Iconsolata);
        lcomp.setFontSize(20);
        ArrayList<Item> itemz= new ArrayList<Item>();
        itemz.add(new Item("item 1"));
        itemz.add(new Item("item 2"));
        lcomp.setListItems(itemz);
        System.out.println(lcomp.htmlify());
        
        String a1 = "butts";
        String a2 = a1;
        a1 = "butts2";
        System.out.println(a2);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        
        Portfolio trees = new Portfolio("chris grasing", null, 3, "TreesPortfolio");
        
        ArrayList<Page> pages = new ArrayList<Page>();
        
        Page a = new Page();
        a.setBanner("tree1.jpg");
        a.setColors(COLOR.EDGY);
        a.setFooter("treef1.jpg");
        a.setLayout(LAYOUT.NORMAL);
        a.setTitle("page1");
        a.getComponents().add(new ImageComponent(200,200,"urlherb","herb.png"));
        a.getComponents().add(new VideoComponent(200,200,"urltreevid","treevid.mp4"));
        pages.add(a);
        
        a = new Page();
        a.setBanner("tree2.jpg");
        a.setColors(COLOR.EIGHTIES);
        a.setFooter("treef2.jpg");
        a.setLayout(LAYOUT.a);
        a.setTitle("page2");
        pages.add(a);
        
        a = new Page();
        a.setBanner("tree3.jpg");
        a.setColors(COLOR.NINTIESKIDS);
        a.setFooter("treef3.jpg");
        a.setLayout(LAYOUT.b);
        a.setTitle("page3");
        SlideShow ss = new SlideShow(new ArrayList<Slide>(), 2, "treess");
        ss.getSlides().add(new Slide("hi","hi.jpg","hiurl"));
        ss.getSlides().add(new Slide("hi2","hi2.jpg","hi2url"));
        SlideShow ss2 = new SlideShow(new ArrayList<Slide>(), 2, "treess2");
        ss2.getSlides().add(new Slide("hi3","hi3.jpg","hi3url"));
        ss2.getSlides().add(new Slide("hi4","hi4.jpg","hi4url"));
        a.getComponents().add(new SlideShowComponent(200,200,ss));
        a.getComponents().add(new SlideShowComponent(200,200,ss2));
        a.getComponents().add(new ImageComponent(200,200,"urlherb2","herb2.png"));
        a.getComponents().add(new VideoComponent(200,200,"urltreevid2","treevid2.mp4"));
        a.setSlideshows(new ArrayList<SlideShow>());
        a.getSlideshows().add(ss);
        a.getSlideshows().add(ss2);
        pages.add(a);
        
        
        trees.setPages(pages);
        
        JsonCreator c = new JsonCreator();
        
        
        for(Page k : trees.getPages())
        {
            try {
                c.makePageData(trees, k);
            } catch (IOException ex) {
                Logger.getLogger(EPortfolioGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                */
    }
                
    
}
