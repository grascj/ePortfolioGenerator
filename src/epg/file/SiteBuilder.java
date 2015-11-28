/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.file;

import static epg.ProgramConstants.PATH_SITES;
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
    public static String TEMPLATE =  "./templates/";

    private SiteBuilder() {
    }

    public static String buildSite(Portfolio portfolio) throws IOException {
        String nameFolder = portfolio.getStudentName();
        if ("".equals(nameFolder)) {
            nameFolder += "newsite";
        }
        nameFolder = PATH_SITES + nameFolder.replaceAll(" ", "_");

        //delete possible stuff
        destroy(new File(nameFolder));
        //create folders
        createDirectories(new File(nameFolder), portfolio);
        //load folders with template files

        //first page url
        return ".";
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
        //copy site data
        System.out.println(nameFolder);
        boolean wot = nameFolder.getAbsoluteFile().mkdir();
        System.out.println(wot);
        
        new File(nameFolder + "/data/").getAbsoluteFile().mkdir();
        recursiveCopy(new File(TEMPLATE + "/data/").getAbsoluteFile(), new File(nameFolder + "/data/").getAbsoluteFile());
        
        
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
