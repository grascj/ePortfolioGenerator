package epg.file;

import epg.ProgramConstants.COLOR;
import epg.ProgramConstants.COMPONENTS;
import static epg.ProgramConstants.COMPONENTS.HEADER;
import epg.ProgramConstants.FONT;
import epg.ProgramConstants.LAYOUT;
import static epg.ProgramConstants.PATH_SAVES;
import static epg.ProgramConstants.PATH_SITES;
import epg.model.Component;
import epg.model.HeaderComponent;
import epg.model.ImageComponent;
import epg.model.ListComponent;
import epg.model.Page;
import epg.model.ParagraphComponent;
import epg.model.Portfolio;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import epg.model.Slide;
import epg.model.SlideShow;
import epg.model.SlideShowComponent;
import epg.model.VideoComponent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 * This class uses the JSON standard to read and write slideshow data files.
 *
 * @author McKilla Gorilla & Chris Grasing
 */
public class JsonCreator {

    // JSON FILE READING AND WRITING CONSTANTS
    public static String PAGEDATA = "pagedata.json";

    //PORTFOLIO SPECIFIC
    public static String JSON_NAME = "name";
    public static String JSON_FILENAME = "filename";
    public static String JSON_PAGES = "pages";

    //PAGE SPECIFIC
    public static String JSON_FONT = "font";
    public static String JSON_LAYOUT = "layout";
    public static String JSON_COLORS = "colors";
    public static String JSON_BANNERURL = "bannerurl";
    public static String JSON_BANNER = "banner";//@todo change in js
    public static String JSON_FOOTER = "footer";//@todo change in js
    public static String JSON_TITLE = "title";
    public static String JSON_COMPONENTS = "components";
    public static String JSON_SLIDESHOWS = "slideshows";

    //COMPONENTS
    public static String JSON_TYPE = "type";
    public static String JSON_TEXT = "text";
    public static String JSON_FONT_SIZE = "font_size";
    public static String JSON_FONT_TYPE = "fpnt_type";
    public static String JSON_CAPTION = "caption";
    public static String JSON_WIDTH = "width";
    public static String JSON_HEIGHT = "height";
    public static String JSON_FILE = "file";
    public static String JSON_FILE_URL = "file_url";
    public static String JSON_SLIDESHOW = "slideshow";
    public static String JSON_SLIDES = "slides";
    public static String JSON_ITEMS = "items";

    public static String JSON_NAVBAR = "navbar";

    public static String JSON_INDEX = "index";
    public static String JSON_IMAGES = "images";
    public static String JSON_CAPTIONS = "captions";
    public static String JSON_UPDATER = "updater";

    public static String JSON_EXT = ".json";
    public static String SLASH = "/";

    /**
     * This method saves all the data associated with a slide show to a JSON
     * file.
     *
     * @param slideShowToSave The course whose data we are saving.
     *
     * @throws IOException Thrown when there are issues writing to the JSON
     * file.
     */
/////////////////////SAVING///////////SAVING////////////////////////////////////////////////    
    static public void savePortfolio(Portfolio portfolioToSave) throws IOException {
        //going to need a jsonhelper method in each component to keep polymorphism going
        //make object
        //portfolio data
        //component array -> data for each via polymorphism
        //write to file

        if (portfolioToSave.getFileName().equals("")) {
            portfolioToSave.setFileName(portfolioToSave.getStudentName().replaceAll(" ", "_"));
        }

        JsonObject portfolioJSON = Json.createObjectBuilder()
                .add(JSON_NAME, portfolioToSave.getStudentName())
                .add(JSON_FILENAME, portfolioToSave.getFileName())
                .add(JSON_PAGES, savePages(portfolioToSave.getPages()))
                .build();

        writeToFile(PATH_SAVES + portfolioToSave.getFileName() + ".json", portfolioJSON);
    }

    static private JsonArray savePages(ArrayList<Page> pages) {
        JsonArrayBuilder pagesJSON = Json.createArrayBuilder();

        for (Page page : pages) {
            JsonObject pageJSON = Json.createObjectBuilder()
                    .add(JSON_FONT, page.getFont().ordinal())
                    .add(JSON_LAYOUT, page.getLayout().ordinal())
                    .add(JSON_COLORS, page.getColors().ordinal())
                    .add(JSON_BANNERURL, page.getBannerURL())
                    .add(JSON_BANNER, page.getBanner())
                    .add(JSON_FOOTER, page.getFooter())
                    .add(JSON_TITLE, page.getTitle())
                    .add(JSON_COMPONENTS, saveComponents(page.getComponents()))
                    .add(JSON_SLIDESHOWS, saveSlideShow(page.getSlideshows()))
                    .build();

            pagesJSON.add(pageJSON);
        }

        return pagesJSON.build();
    }

    static private JsonArray saveComponents(ArrayList<Component> comps) {
        JsonArrayBuilder compsJSON = Json.createArrayBuilder();

        for (Component comp : comps) {
            compsJSON.add(comp.jsonify());
        }

        return compsJSON.build();
    }

    static private JsonArray saveSlideShow(ArrayList<SlideShow> slideshows) {
        JsonArrayBuilder slideshowsJSON = Json.createArrayBuilder();
        for (SlideShow ss : slideshows) {
            slideshowsJSON.add(saveSlideShow(ss));
        }
        return slideshowsJSON.build();
    }

    static public JsonObject saveSlideShow(SlideShow ss) {
        JsonObjectBuilder ssJSON = Json.createObjectBuilder()
                .add(JSON_TITLE, ss.title);

        JsonArrayBuilder slidesJSON = Json.createArrayBuilder();

        for (Slide s : ss.getSlides()) {
            JsonObject sJSON = Json.createObjectBuilder()
                    .add(JSON_CAPTION, s.caption)
                    .add(JSON_FILE, s.image)
                    .add(JSON_FILE_URL, s.imageURL)
                    .build();
            slidesJSON.add(sJSON);
        }

        ssJSON.add(JSON_SLIDES, slidesJSON);

        return ssJSON.build();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
////LOADING//////////LOADING//////////LOADING//////////LOADING//////////LOADING//////////LOADING//////////LOADING//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static public Portfolio loadPortfolio(String filepath) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(filepath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();

        return loadPortfolioObject(json);
    }

    static public Portfolio loadPortfolioObject(JsonObject portfolioJSON) {

        Portfolio portfolio = new Portfolio(
                portfolioJSON.getString(JSON_NAME),
                loadPages(portfolioJSON.getJsonArray(JSON_PAGES)),
                portfolioJSON.getString(JSON_FILENAME)
        );

        return portfolio;
    }

    private static ArrayList<Page> loadPages(JsonArray pagesJSON) {
        ArrayList<Page> pages = new ArrayList<>();

        for (int i = 0; i < pagesJSON.size(); i++) {
            JsonObject pJSON = pagesJSON.getJsonObject(i);
            pages.add(new Page(
                    FONT.values()[pJSON.getInt(JSON_FONT)],
                    LAYOUT.values()[pJSON.getInt(JSON_LAYOUT)],
                    COLOR.values()[pJSON.getInt(JSON_COLORS)],
                    pJSON.getString(JSON_TITLE),
                    pJSON.getString(JSON_BANNER),
                    pJSON.getString(JSON_BANNERURL),
                    pJSON.getString(JSON_FOOTER),/////////////////THE ARRRAYS NOWS
                    loadComponents(pJSON.getJsonArray(JSON_COMPONENTS)),
                    loadSlideShows(pJSON.getJsonArray(JSON_SLIDESHOWS))
            ));

        }

        return pages;
    }

    private static ArrayList<Component> loadComponents(JsonArray compsJSON) {
        ArrayList<Component> comps = new ArrayList<>();
        if (compsJSON != null) {
            for (int i = 0; i < compsJSON.size(); i++) {
                JsonObject cJSON = compsJSON.getJsonObject(i);

                switch (COMPONENTS.values()[cJSON.getInt(JSON_TYPE)]) {
                    case HEADER:
                        comps.add(new HeaderComponent(cJSON));
                        break;
                    case IMAGE:
                        comps.add(new ImageComponent(cJSON));
                        break;
                    case LIST:
                        comps.add(new ListComponent(cJSON));
                        break;
                    case PARAGRAPH:
                        comps.add(new ParagraphComponent(cJSON));
                        break;
                    case SLIDESHOW:
                        comps.add(new SlideShowComponent(cJSON));
                        break;
                    case VIDEO:
                        comps.add(new VideoComponent(cJSON));
                        break;
                }
            }
        }
        return comps;
    }

    private static ArrayList<SlideShow> loadSlideShows(JsonArray slideshowsJSON) {
        ArrayList<SlideShow> slideshows = new ArrayList<SlideShow>();
        for (int i = 0; i < slideshowsJSON.size(); i++) {
            slideshows.add(loadSlideShow(slideshowsJSON.getJsonObject(i)));

        }

        return slideshows;
    }

    static public SlideShow loadSlideShow(JsonObject ssJSON) {

        ArrayList<Slide> slides = new ArrayList<Slide>();

        for (int i = 0; i < ssJSON.getJsonArray(JSON_SLIDES).size(); i++) {
            JsonObject sJSON = ssJSON.getJsonArray(JSON_SLIDES).getJsonObject(i);
            slides.add(new Slide(
                    sJSON.getString(JSON_CAPTION),
                    sJSON.getString(JSON_FILE),
                    sJSON.getString(JSON_FILE_URL)));
        }

        SlideShow ss = new SlideShow(slides, ssJSON.getJsonArray(JSON_SLIDES).size(), ssJSON.getString(JSON_TITLE));

        return ss;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
///////////////////////////SITES///////////SITES/////////////////////////////////////////////////
    static public void makePageData(Portfolio portfolio, Page page, String path) throws IOException {
        String jsonFilePath = path;

        JsonObject data = Json.createObjectBuilder()
                .add(JSON_NAME, portfolio.getStudentName())
                .add(JSON_TITLE, page.getTitle())
                .add(JSON_LAYOUT, page.getLayout().ordinal())
                .add(JSON_BANNER, page.getBanner())
                .add(JSON_FOOTER, page.getFooter())
                .add(JSON_NAVBAR, makeNavbarHTML(portfolio.getPages()))
                .add(JSON_COMPONENTS, makeComponentHTML(page.getComponents()))
                .add(JSON_SLIDESHOWS, makeSlideShowData(page.getSlideshows()))
                .build();

        writeToFile(jsonFilePath, data);
    }

    //@todo make current stick out
    static private JsonArray makeNavbarHTML(ArrayList<Page> pages) {
        JsonArrayBuilder navbar = Json.createArrayBuilder();
        //make a link for each page to use in the navbar
        for (Page page : pages) {
            navbar.add(HTMLWorker.navBarBuilderHTML(page));
        }
        return navbar.build();
    }

    static private JsonArray makeComponentHTML(ArrayList<Component> comps) {
        //get the html for each component to inject in the javascript
        JsonArrayBuilder compList = Json.createArrayBuilder();
        for (Component comp : comps) {
            compList.add(comp.htmlify());
        }
        return compList.build();
    }

    static void writeToFile(String jsonFilePath, JsonObject obj) throws FileNotFoundException {
        StringWriter sw = new StringWriter();
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(obj);
        jsonWriter.close();

        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(obj);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(jsonFilePath);
        pw.write(prettyPrinted);
        pw.close();
    }

    static private JsonArray makeSlideShowData(ArrayList<SlideShow> slideshows) {
        JsonArrayBuilder ssList = Json.createArrayBuilder();
        if (slideshows != null) {
            //making slide show object

            for (SlideShow ss : slideshows) {
                //making json arrays for images and captions for the slides
                JsonArrayBuilder images = Json.createArrayBuilder();
                JsonArrayBuilder captions = Json.createArrayBuilder();
                for (Slide slides : ss.getSlides()) {
                    images.add(slides.image);
                    captions.add(slides.caption);
                }

                //making the slideshow object itself
                JsonObject ssobj = Json.createObjectBuilder()
                        .add(JSON_TITLE, ss.getTitle())
                        .add(JSON_INDEX, "0")
                        .add(JSON_IMAGES, images)
                        .add(JSON_CAPTIONS, captions)
                        .add(JSON_UPDATER, "0")
                        .build();

                ssList.add(ssobj);
            }
        }
        return ssList.build();

    }

}
