
$(document).ready(function(){

    //Punktestand merken
    var rating;

	// Variable to set the duration of the animation
	var animationTime = 500;
    var storeRating = false;
	
	// Variable to store the colours
	var colours = ["bd2c33", "e49420", "ecdb00", "3bad54", "1b7db9"];

	// Add rating information box after rating
	var ratingInfobox = $("<div />")
		.attr("id", "ratinginfo")
		.insertAfter($("#rating"));

	// Function to colorize the right ratings
	var colourizeRatings = function(nrOfRatings) {
		$("#rating li a").each(function() {
			if($(this).parent().index() <= nrOfRatings) {
				$(this).stop().animate({ backgroundColor : "#" + colours[nrOfRatings] } , animationTime);
			}
		});
	};
	
	// Handle the hover events
	$("#rating li a").hover(function() {
        if(!storeRating) {

            // Empty the rating info box and fade in
            ratingInfobox
                .empty()
                .stop()
                .animate({ opacity : 1 }, animationTime);

            // Add the text to the rating info box
            $("<p />")
                .html($(this).html())
                .appendTo(ratingInfobox);

            // Call the colourize function with the given index
            colourizeRatings($(this).parent().index());
        }

	}, function() {
        if(!storeRating) {
            // Fade out the rating information box
            ratingInfobox
                .stop()
                .animate({ opacity : 0 }, animationTime);

            // Restore all the rating to their original colours
            $("#rating li a").stop().animate({ backgroundColor : "#595f69" } , animationTime);
        }
	});
	
	// Prevent the click event and show the rating
	$("#rating li a").click(function(e) {
		e.preventDefault();
        storeRating = true;
        $("#rating li a").stop().animate({ backgroundColor : "#595f69" } , animationTime);
        rating = $(this).parent().index();
        $('#ratinginfo').empty();
        $("<p />").html($(this).html()).appendTo(ratingInfobox);
        colourizeRatings(rating);

	});

    $("#saveRating").click(function() {
        if (typeof(rating) != "undefined"){
            window.location.href =  jsRoutes.controllers.RatingController.saveRating(this.value, (rating -2)).url;
            fiveheadsNotify('success', 'Erfolgreich gespeichert!', 'Bewerte einen weiteren Artikel.')
        } else {
            fiveheadsNotify('danger', 'Achtung!', 'Bitte erst eine Bewertung wählen.')
        }
    });

});