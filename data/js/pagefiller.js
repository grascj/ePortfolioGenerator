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

	xmlhttp.onreadystatechange = function()
	{	
		if(xmlhttp.readyState == 4)
		{			
			pagedata = JSON.parse(xmlhttp.responseText);
			init();
		}
	}

	xmlhttp.open("GET", DATALOC, true);
	xmlhttp.send();
}

/*we can assume at this point:
	-that the 2 css files pertaining to color and layour are present
	-that the html is all setup
	-that the json is fully loaded to go
*/
function init()
{
	
	staticPageSetup();
	layoutSetup();
	processComponents();
	processNavBar();
	initSlideShows();
}

function staticPageSetup()
{
	$(NAMETXT).html(pagedata.name);
	$(TITLE).html(pagedata.title);
	if(pagedata.bannerImage != null && pagedata.bannerImage != "")
		$("#bannerImage").attr("src", pagedata.bannerImage);
	if(pagedata.footerImage != null && pagedata.footerImage != "")
		$("#footerImage").attr("src", pagedata.footerImage);
}

function layoutSetup()
{
var layout = pagedata.layout;
//
//1 lownav -no changes necessary
//2 sidenav -no changes necessary
//3 topnav -need to move navbar div above the banner
//4 fixedname -no changes necessary
//5 gaps -nochanges necessary
if(layout==3)
{$(NAVBAR).prependTo($("#body"));}


}

function processNavBar()
{
	var size = pagedata.navbar.length;
	for(var i = 0; i < size; i++)
	{
		$(NAVBAR).append(pagedata.navbar[i]);
	}
}



function processComponents()
{
	var size = pagedata.components.length;
	for(var i = 0; i < size; i++)
	{$(COMPONENTS).append(pagedata.components[i]);}
}



function initSlideShows(){
for(var i = 0; i < pagedata.slideshows.length; i++)
{update(pagedata.slideshows[i]);}
}

//slideshowfunctionality
function playSlideShow(slideshow){
var mode = $("#"+slideshow.title+"button");
if(mode.attr("src").match("media/button_images/play.png")){
mode.attr("src", "media/button_images/pause.png");
pagedata.updater = setInterval(nextButton,3000,slideshow);
}
else {
mode.attr("src", "media/button_images/play.png");
clearInterval(pagedata.updater);
}
}

function nextButton(slideshow)
{
slideshow.index=(slideshow.index+1)%slideshow.images.length;
update(slideshow);
}

function previousButton(slideshow)
{
slideshow.index--;
if(slideshow.index < 0)
{slideshow.index=slideshow.images.length}
update(slideshow);
}

function update(slideshow)
{
	var img = $("#"+slideshow.title+"img");
	var caption = $("#"+slideshow.title+"caption");
	img.attr("src","media/"+slideshow.images[slideshow.index]);
	caption.html(slideshow.captions[slideshow.index]);
}


