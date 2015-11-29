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
        $("#banner").attr("src", pagedata.banner);
    if (pagedata.footer != null && pagedata.footer != "")
        $("#footer").html(pagedata.footer);
}

function layoutSetup()
{
    var layout = pagedata.layout;

    if (layout == 0)//lownav
    {
        $("head").append("<link href =\"../data/layouts/lownav.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    if (layout == 1)//sidenav
    {
        $("head").append("<link href =\"../data/layouts/sidenav.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    if (layout == 2)//gaps
    {
        $("head").append("<link href =\"../data/layouts/gaps.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    if (layout == 3)//topnav
    {
        $(NAVBAR).prependTo($("#body"));
        $("head").append("<link href =\"../data/layouts/topnav.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    if (layout == 4)//fixedname
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
    if (color == 1)
    {
        $("head").append("<link href =\"../data/colors/campfire.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    if (color == 2)
    {
        $(NAVBAR).prependTo($("#body"));
        $("head").append("<link href =\"../data/colors/personal.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    if (color == 3)
    {
        $("head").append("<link href =\"../data/colors/sbured.css\" rel=\"stylesheet\" type=\"text/css\">");
    }
    if (color == 4)
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
        update(pagedata.slideshows[i]);
    }
}

//slideshowfunctionality
function playSlideShow(slideshow) {
    var mode = $("#" + slideshow.title + "button");
    if (mode.attr("src").match("../data/button_images/play.png")) {
        mode.attr("src", "../data/button_images/pause.png");
        pagedata.updater = setInterval(nextButton, 3000, slideshow);
    }
    else {
        mode.attr("src", "../data/button_images/play.png");
        clearInterval(pagedata.updater);
    }
}

function nextButton(slideshow)
{
    slideshow.index = (slideshow.index + 1) % slideshow.images.length;
    update(slideshow);
}

function previousButton(slideshow)
{
    slideshow.index--;
    if (slideshow.index < 0)
    {
        slideshow.index = slideshow.images.length
    }
    update(slideshow);
}

function update(slideshow)
{
    var img = $("#" + slideshow.title + "img");
    var caption = $("#" + slideshow.title + "caption");
    img.attr("src", "media/" + slideshow.images[slideshow.index]);
    caption.html(slideshow.captions[slideshow.index]);
}


