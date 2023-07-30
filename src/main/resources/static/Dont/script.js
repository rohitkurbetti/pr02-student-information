$(document).ready(function () {
    // Simulate API call with a timeout (replace this with your actual API call)
    // setTimeout(function () {
      // Remove the loading screen

      

      setTimeout(function () {
        // Remove the loading screen
        $(".loading-overlay").fadeIn();
        $(".loading-screen").fadeIn();
        $("#main-content").fadeOut();
      }, 1000); 

      setTimeout(function () {
        // Remove the loading screen
        $(".loading-screen").fadeOut();
        $("#main-content").fadeIn();
        $(".loading-overlay").fadeOut();
      }, 1000); 


      
    // }, 2000); // Replace 2000ms (2 seconds) with the actual API request time



      






  });
  