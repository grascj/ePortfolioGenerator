//CONSTANTS
var BANNER = "#banner";
var TITLE = "#title";
var NAVBAR = "#navbar";
var COMPONENTS = "#components";
var FOOTER = "#footer";
var NAMETXT = "#nametxt";
var DATALOC = "pagedata.json";


//DATA
var pagedata;

function initPage()
{
    getData();
}

function getData()
{
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function ()
    {
        if (xmlhttp.readyState == 4)
        {
            pagedata = JSON.parse(xmlhttp.responseText);
            init();
        }
    }

    xmlhttp.open("GET", DATALOC, true);
    xmlhttp.send();
}


function init()
{

    staticPageSetup();
    layoutSetup();
    pagefontSetup();
    colorSetup();
    processComponents();
    processNavBar();
    initSlideShows();
}

function staticPageSetup()
{
    $(NAMETXT).html(pagedata.name);
    $(TITLE).html(pagedata.title);
    if (pagedata.banner != null && pagedata.banner != "")
        $("#bannerImage").attr("src", "./media/" + pagedata.banner);
    if (pagedata.footer != null && pagedata.footer != "")
        $("#footerText").html(pagedata.footer);
}

function pagefontSetup()
{
    var font = pagedata.font;

    if (font == 0)
    {
        $("body").addClass("Fjalla_One");
    }
    if (font == 1)
    {
        $("body").addClass("Bree_Serif");
    }
    if (font == 2)
    {
        $("body").addClass("Muli");
    }
    if (font == 3)
    {
        $("body").addClass("Vollkorn");
    }
    if (font == 4)
    {
        $("body").addClass("Inconsolata");
    }

}

function layoutSetup()
{
    var layout = pagedata.layout;

    if (layout == 0)//lownav
    {
        $("head").append("<link href =\"../data/layouts/lownav.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    else if (layout == 1)//sidenav
    {
        $("head").append("<link href =\"../data/layouts/sidenav.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    else if (layout == 2)//gaps
    {
        $("head").append("<link href =\"../data/layouts/gaps.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    else if (layout == 3)//topnav
    {
        $(NAVBAR).prependTo($("#body"));
        $("head").append("<link href =\"../data/layouts/topnav.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    else if (layout == 4)//fixedname
    {
        $("head").append("<link href =\"../data/layouts/fixedname.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
}

//colors setup
function colorSetup()
{
    var color = pagedata.colors;

    if (color == 0)
    {
        $("head").append("<link href =\"../data/colors/beach.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    else if (color == 1)
    {
        $("head").append("<link href =\"../data/colors/campfire.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    else if (color == 2)
    {
        $("head").append("<link href =\"../data/colors/personal.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    else if (color == 3)
    {
        $("head").append("<link href =\"../data/colors/sbured.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    else if (color == 4)
    {
        $("head").append("<link href =\"../data/colors/vintage.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
}


function processNavBar()
{
    var size = pagedata.navbar.length;
    for (var i = 0; i < size; i++)
    {
        $(NAVBAR).append(pagedata.navbar[i]);
    }
}



function processComponents()
{
    var size = pagedata.components.length;
    for (var i = 0; i < size; i++)
    {
        $(COMPONENTS).append(pagedata.components[i]);
    }
}



function initSlideShows() {
    for (var i = 0; i < pagedata.slideshows.length; i++)
    {
        update(i);
    }
}

//slideshowfunctionality
function playSlideShow(index) {
    var slideshow = pagedata.slideshows[index];
    var mode = $("#" + index + "button");
    if (mode.attr("src").match("../data/button_images/play.png")) {
        mode.attr("src", "../data/button_images/pause.png");
        pagedata.slideshows[index].updater = setInterval(nextButton, 3000, index);
    }
    else {
        mode.attr("src", "../data/button_images/play.png");
        clearInterval(pagedata.slideshows[index].updater);
    }
}

function nextButton(index)
{
    var slideshow = pagedata.slideshows[index];
    slideshow.index = (slideshow.index + 1) % slideshow.images.length;
    update(index);
}

function previousButton(index)
{
    var slideshow = pagedata.slideshows[index];
    slideshow.index--;
    if (slideshow.index < 0)
    {
        slideshow.index = slideshow.images.length - 1;
    }
    update(index);
}

function update(index)
{
    var slideshow = pagedata.slideshows[index];
    var img = $("#" + index + "img");
    var caption = $("#" + index + "caption");
    img.attr("src", "media/" + slideshow.images[slideshow.index]);
    caption.html(slideshow.captions[slideshow.index]);
}


