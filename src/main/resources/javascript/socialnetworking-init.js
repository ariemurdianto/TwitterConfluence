jQuery(document).ready(function(){
    jQuery("body").append(
            "<div id='popupTwitter'>"+
            "<div id='temporaryOutput'></div>"+
            "</div>"+
            "<div id='tempStatus'></div>"
            );
    jQuery('#popupTwitter').hide();
	jQuery('#tempStatus').hide();
    alert('testerss');
    jQuery('#confluence-twitter').click(function(){
        popupHtml('/plugins/servlet/socialnetworking-twitter')
    });
});