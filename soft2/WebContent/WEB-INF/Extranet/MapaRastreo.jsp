<!DOCTYPE html>
<html lang = "es">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
         <%@ include file="../headHome.jsp" %>
         
   <style type="text/css">
     
     #map_canvas {
        margin: 0;
        padding: 0;
        height: 600px;
        width: 100%;
      }

      h3 {
        margin-top: 100px;
        margin-left: 200px;
        font-size: 300px;
      }
      .avion {
        font-size: 30px;
      }
      
         
    </style>
    <script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA___eyQMCgVAkwXoNOS8-sX7nznS3NJq0&sensor=true">
    </script>

	<script type="text/javascript">

 var citymap = {};
 var rutasMapa = {};
 var overlay;
 USGSOverlay.prototype = new google.maps.OverlayView();
 
 $(document).ready(function() {
     
     $("#flechaDown").click(function(){
        //alert("lol");
        $.scrollTo('#destino',800);
     });
     
     $("#flechaUp").click(function(){
          $.scrollTo('#redex',800);
     });
     
     function dameColorGlobo(flag){
        switch (flag) { 
   	case 0 :
      	 return "FE7569";
      	 break 
   	case 1 :
         return "66FF00";   
      	 break 
   	case 2 : 
         return "eee";
      	 break 
   	default: break;
        } 
    }
   var idEnvio = 0;
   
  if (localStorage.getItem("idEnvio") == null){
      $(location).attr("href","../");
  }else {
      /*var data = {
                                    id : localStorage.getItem("idEnvio")		
                 };

                        var jsonData = JSON.stringify(data);
       $.ajax({
                        type: "POST",
                        data: jsonData,
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        url: "../Pedido/ConsultarRuta",
                        //beforeSend: waitTable,        
                        success: function(data){
                            if (data.me =="") {*/
                            	pachito = "";
                                //console.log(data);
                                //google.maps.LatLng(-12.22276,-76.94439)
                                data={
                                	    me: "",
                                	    lRuta:[
                                	    [{longitud:-76.94439, latitud:-12.22276, paso  : 1, msje :"HOLA"}],
                                	    [{longitud:-76.94439, latitud:-12.22276, paso  : 0, msje :"CHAU"}],
                                	    [{longitud:-76.94439, latitud:-12.22276, paso  : 2, msje :"PEDON"}]
                                	      ]

                                	  } ;
                                var arrRuta = new Array();
                                var arrRuta = data.lRuta;
                                //var arrR = new Array();
                                //arrR = arrRuta[0];
                                //console.log(arrR[0].latitud);
                                
                                var mensajex = "";
                                $.each(arrRuta,function(i){
                                	var pintaRuta = new Array();
                                    var pintaRuta = arrRuta[i];
                                	for(j = 0; j < pintaRuta.length;j++){
                                		mensajex += pintaRuta[j].msje + "<br>";
                                	}
                                });
                                
                                
                                var cont = 0;
                                $.each(arrRuta, function(i) {
                                    //pintamos toda la ruta
                                    var pintaRuta = new Array();
                                    var pintaRuta = arrRuta[i];
                                   // console.log(pintaRuta.length);
                                    var arrRutaMapa = {};
                                    
                                    
                                    
                                    
                                   for (j = 0; j < pintaRuta.length; j++){
                                       //console.log(pintaRuta[j].latitud);
                                       //console.log("Mensaje "+ j + " : " + pintaRuta[j].msje + "</br>");
                                       var bactual = false;
                                       if (pintaRuta[j].paso == 2) bactual = true;
                                       citymap[cont] = {
                                        center: new google.maps.LatLng(pintaRuta[j].latitud,pintaRuta[j].longitud),
                                        color : dameColorGlobo(pintaRuta[j].paso),
                                        //mensaje: pintaRuta[j].msje,
                                        mensaje: mensajex,
                                        now : bactual
                                      };
                                      var unaRuta = {
                                             latitud : pintaRuta[j].latitud,
                                             longitud : pintaRuta[j].longitud
                                            };
                                      arrRutaMapa[j] = unaRuta; 
                                      cont++;
                                   }
                                   rutasMapa[i] = arrRutaMapa;
                                });
                                
                                
                                //console.log("Mensajexxxxx: " + mensajex);
                                
                                
                                
                                   //console.log(rutasMapa); 
                                   initialize();
                            /*}else {
                                 alert("hubo un problema");
                            }
                        }
                    });*/
  }
   
   $("#regresarWebEmpresarial").click(function(){

       if (localStorage.getItem("webActual") == "extranet"){
          $(location).attr("href","../extranet/mapa");
    }else{
        $(location).attr("href","../"); 
      }
      return false;
    });
  
 });

function toggleBounce() {

        if (marker.getAnimation() != null) {
          marker.setAnimation(null);
        } else {
          marker.setAnimation(google.maps.Animation.BOUNCE);
        }

}


/*
var citymap = {};
      citymap['chicago'] = {
        center: new google.maps.LatLng(-12.039321,-77.07074),
        color : "66FF00",
        mensaje: "Paquete: 200",
        now : true
      };
      
      citymap['newyork'] = {
        center: new google.maps.LatLng(-16.720385,-64.755066),
        color : "FE7569",
        mensaje: "Paquete : 100",
        now : false
      };
      citymap['losangeles'] = {
        center: new google.maps.LatLng(-8.385431,-52.957397),
        color : "FE7569",
        mensaje: "Paquete : 80",
        now : false
      }
*/
  function attachSecretMessage(marker, mensaje) {
       /*
       var infowindow = new google.maps.InfoWindow({
          content: mensaje
        });

        
        */
        
        
        
       google.maps.event.addListener(marker, 'click', function() {
                $("#modalGlobito").modal('toggle');
                $("#mensajeModal").html(mensaje);
        });
       
      }

   function initialize() {
        
        var mapOptions = {
          zoom: 16,
          center: new google.maps.LatLng(-12.22276,-76.94439),
          //center: new google.maps.LatLng(-12.039321,-77.07074),
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);
        
        var swBound = new google.maps.LatLng(-12.22276,-76.94439);
        var neBound = new google.maps.LatLng(-12.22276,-76.94439);
        var bounds = new google.maps.LatLngBounds(swBound, neBound);

        // Photograph courtesy of the U.S. Geological Survey
        var srcImage = '../../resource/jpg/mapa_paquete.jpg';
        overlay = new USGSOverlay(bounds, srcImage, map);
       
       $.each(rutasMapa, function(i) {
           //cada ruta creo una ruta
           var r = {};
           r = rutasMapa[i];
           //console.log(r.length);//bien
           var ruta = [];
           var m = 0;
           while (r[m]){
               ruta[m] =  new google.maps.LatLng(r[m].latitud,r[m].longitud);
               m++;
           }
           console.log(m);
           /*
           for (j = 0; j < 2; j++){
               ruta[j] =  new google.maps.LatLng(r[j].latitud,r[j].longitud);
           }
        */
           console.log(ruta);
           var lineas = new google.maps.Polyline({
                     path: ruta,
                     map: map,
                     strokeColor: '#222000',
                     strokeWeight: 4,
                     strokeOpacity: 0.6,
                     clickable: false
                });

       });
        /*  
        var ruta = [
        new google.maps.LatLng(-12.039321,-77.07074),
        new google.maps.LatLng(-16.720385,-64.755066),
        new google.maps.LatLng(-8.385431,-52.957397)
        ];
        */
       
     console.log(citymap);
     for (var city in citymap) {
      
        var pinColor = citymap[city].color;
        var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
        new google.maps.Size(21, 34),
        new google.maps.Point(0,0),
        new google.maps.Point(10, 34));
        var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
        new google.maps.Size(40, 37),
        new google.maps.Point(0, 0),
        new google.maps.Point(12, 35));
          
         var marker = new google.maps.Marker({
            position: citymap[city].center,
            map: map,
            icon: pinImage,
            shadow: pinShadow
          });

         attachSecretMessage(marker, citymap[city].mensaje);
         if (citymap[city].now)
          marker.setAnimation(google.maps.Animation.BOUNCE);
      
      }
  }
   
   //////////////////////////////////////////////////////////////////////////
   

/** @constructor */
function USGSOverlay(bounds, image, map) {

  // Now initialize all properties.
  this.bounds_ = bounds;
  this.image_ = image;
  this.map_ = map;

  // We define a property to hold the image's div. We'll
  // actually create this div upon receipt of the onAdd()
  // method so we'll leave it null for now.
  this.div_ = null;

  // Explicitly call setMap on this overlay
  this.setMap(map);
}

USGSOverlay.prototype.onAdd = function() {

  // Note: an overlay's receipt of onAdd() indicates that
  // the map's panes are now available for attaching
  // the overlay to the map via the DOM.

  // Create the DIV and set some basic attributes.
  var div = document.createElement('div');
  div.style.borderStyle = 'none';
  div.style.borderWidth = '0px';
  div.style.position = 'absolute';

  // Create an IMG element and attach it to the DIV.
  var img = document.createElement('img');
  img.src = this.image_;
  img.style.width = '100%';
  img.style.height = '100%';
  img.style.position = 'absolute';
  div.appendChild(img);

  // Set the overlay's div_ property to this DIV
  this.div_ = div;

  // We add an overlay to a map via one of the map's panes.
  // We'll add this overlay to the overlayLayer pane.
  var panes = this.getPanes();
  panes.overlayLayer.appendChild(div);
}

USGSOverlay.prototype.draw = function() {

  // Size and position the overlay. We use a southwest and northeast
  // position of the overlay to peg it to the correct position and size.
  // We need to retrieve the projection from this overlay to do this.
  var overlayProjection = this.getProjection();

  // Retrieve the southwest and northeast coordinates of this overlay
  // in latlngs and convert them to pixels coordinates.
  // We'll use these coordinates to resize the DIV.
  var sw = overlayProjection.fromLatLngToDivPixel(this.bounds_.getSouthWest());
  var ne = overlayProjection.fromLatLngToDivPixel(this.bounds_.getNorthEast());

  // Resize the image's DIV to fit the indicated dimensions.
  var div = this.div_;
  div.style.left = sw.x + 'px';
  div.style.top = ne.y + 'px';
  div.style.width = (ne.x - sw.x) + 'px';
  div.style.height = (sw.y - ne.y) + 'px';
}

USGSOverlay.prototype.onRemove = function() {
  this.div_.parentNode.removeChild(this.div_);
  this.div_ = null;
}

google.maps.event.addDomListener(window, 'load', initialize);
   
   
   /////////////////////////////////////////////////////////////////////////

</script>
</head>
	  <body>
      <%@ include file="../NavegacionMapaRastreo.jsp" %>
               <span style ="margin-left: 50px; color: red;">Ver Sumilla </span><img id ="flechaDown" src ="../resources/png/flecha-down.png" alt ="Ver Almacenes" /> 
		<div id="map_canvas"></div>	        
                <div class="container-fluid" id ="destino">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h2>Sumilla</h2>
					<table class="table table-hover">
                                             <tr class="success">
                                                <td><img src ="../resources/png/chartVerde.png" /></td>
                                                <td>Posici�n Actual</td>
                                                </tr>
                                                
                                                <tr class="error">
                                                <td> <img src ="../resources/png/chartRojo.png" /> </td>
                                                <td>Todav�a no pasa</td>
                                                </tr> 
                                        </table>
                                        <legend> Estado : </legend>
                                        <legend> Punto de Recojo : </legend>
                                        
                                        <span style ="margin-left: 50px; color: red;">Subir </span><img id ="flechaUp" src ="../resources/png/flechaup.png" alt ="Ver Almacenes" /> 
                                        
				</div>
    		</div>
    	</div>
         <div id="modalGlobito" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style=" display: none; ">
        	
                        <div class="modal-body">

                            
                          <h1> Informaci&oacute;n env�o</h1>
                          <div id ="mensajeModal">
                          </div>
                                
                        </div>

          </div>        
                
      </body>
</html>