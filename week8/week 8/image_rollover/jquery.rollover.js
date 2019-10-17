(function($){
    $.fn.changeImage = function() {
        return this.each(function() {

     //in the parent selector, select the child
     //with a image and change its image
          $("img").each(function() {
              // get old and new urls
              var oldURL = $(this).attr("src");
              var newURL = $(this).attr("id");

              // preload images
              var rolloverImage = new Image();
              rolloverImage.src = newURL;

              // set up event handlers
              $(this).hover(
                  function() {
                      $(this).attr("src", newURL);
                  },
                  function() {
                      $(this).attr("src", oldURL);
                  }
              ); // end hover
          }); // end each


        });
    };
})(jQuery);
