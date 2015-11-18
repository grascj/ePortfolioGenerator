/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg;

/**
 *
 * @author cgmp
 */
public class ProgramConstants {
    
    //@TODO add enums
    public enum FONT        {Fjalla_One, Bree_Serif, Muli, Vollkorn, Iconsolata};
    public enum LAYOUT      {lownav, sidenav, gaps, topnav, fixedname};
    public enum COLOR       {beach, campfire, personal, SBUred, vintage};
    public enum TEXT_TYPE   {HEADER, PARAGRAPH, LIST};

    //Defaults
    
    
    //UI text and such
    public static String OKAY = "OK";
    public static String NEWIMAGE = "Pick an image.";
    public static String NEWVIDEO = "Pick a video.";
   
    //Paths
    public static String PATH_SAVES = "./saves";
    public static String PATH_SITES = "./sites";
    
}
