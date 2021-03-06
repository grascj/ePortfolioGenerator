/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.file;

import epg.model.Component;
import epg.model.Page;
import epg.model.Portfolio;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author cgmp
 */
public class SiteBuilder {

    public static String DOT = ".";
    public static String SLASH = "/";
    public static String TEMPLATE = "./templates/";

    private SiteBuilder() {
    }

    public static String buildSite(Portfolio portfolio, String location) throws IOException {

        //delete possible stuff
        destroy(new File(location));

        //create folders
        //load folders with template files
        createDirectories(new File(location), portfolio);

        //use HTMLWorker to create the pagedata
        //first page url
        return location;
    }

    public static void destroy(File fileToKill) {
        if (fileToKill.isDirectory() && fileToKill.list().length > 0) {
            for (String a : fileToKill.list()) {
                destroy(new File(fileToKill + "/" + a));
            }
        }
        fileToKill.delete();
    }

    public static boolean createDirectories(File nameFolder, Portfolio portfolio) throws IOException {
        //create the folder for the site
        boolean wot = nameFolder.getAbsoluteFile().mkdir();
        //copy the data template
        new File(nameFolder + "/data/").getAbsoluteFile().mkdir();
        recursiveCopy(new File(TEMPLATE + "/data/").getAbsoluteFile(), new File(nameFolder + "/data/").getAbsoluteFile());
        //make each page folder
        for (Page page : portfolio.getPages()) {
            //make page folder
            File pageFolder = new File(nameFolder + SLASH + page.getTitle());
            pageFolder.mkdir();

            /*copy page goodies into folder*/
            //copy html and make media folder
            recursiveCopy(new File(TEMPLATE + "/page/").getAbsoluteFile(), pageFolder.getAbsoluteFile());

            String mediaFolder = pageFolder.toString() + SLASH + "media" + SLASH;

            //copy images
            if (!page.getBannerURL().equals("")) {
                Files.copy(new File(page.getBannerURL()).toPath(), new File(mediaFolder + page.getBanner()).toPath());
            }

            for (Component comp : page.getComponents()) {
                if (comp.getMedia() != null) {
                    for (File media : comp.getMedia()) {
                        if (!(new File(mediaFolder + media.getName()).exists())) {
                            Files.copy(media.toPath(), new File(mediaFolder + media.getName()).toPath());
                        }
                    }
                }
            }

            //make pagedata
            JsonCreator.makePageData(portfolio, page, nameFolder + SLASH + page.getTitle() + SLASH + "pagedata.json");

        }

        return true;
    }

    
    public static void recursiveCopy(File source, File target) throws IOException {
        if (source.isDirectory() && source.list().length > 0) {
            for (File a : source.listFiles()) {
                if (a.isDirectory()) {
                    Files.copy(a.toPath(), new File(target.toString() + "/" + a.getName()).toPath());
                    recursiveCopy(a, new File(target.toString() + "/" + a.getName()));
                } else {
                    Files.copy(a.toPath(), new File(target.toString() + "/" + a.getName()).toPath());
                }
            }
        }
    }

}
