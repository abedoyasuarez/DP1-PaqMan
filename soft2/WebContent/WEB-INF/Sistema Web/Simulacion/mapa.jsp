<!DOCTYPE html>
<html lang = "es">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        
<style type="text/css">
 #map_canvas {
        margin: 0;
        padding: 0;
        height: 600px;
        width: 100%;
      }
</style>
    
    <script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA___eyQMCgVAkwXoNOS8-sX7nznS3NJq0&sensor=true">
    </script>
    <script type ="text/javascript" src ="http://code.jquery.com/jquery-1.8.3.js">
    </script>
    <script type="text/javascript">
             $(document).ready(function() {
                initialize(); 
             });
             function initialize() {
                    
                    var mapOptions = {
                      zoom: 4,
                      center: new google.maps.LatLng(-12.039321,-77.07074),
                      mapTypeId: google.maps.MapTypeId.ROADMAP
                    };
                    map = new google.maps.Map(document.getElementById("map_canvas"),
                        mapOptions);
              }
             
    </script>   
</head>
	  <body>
		<div id="map_canvas"></div>       
          </body>
</html>