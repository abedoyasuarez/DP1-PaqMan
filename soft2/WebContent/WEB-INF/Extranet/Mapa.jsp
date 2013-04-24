<!DOCTYPE html>
<html lang = "es">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
         <%@ include file="../headHome.jsp" %>  
         <script src = "/soft/resources/js/Extranet/cancelarPedido.js"></script>
         
   <style type="text/css">
     
     #map_canvas {
        margin: 0;
        padding: 0;
        height: 600px;
        width: 100%;
      }

    
      
           #flechaDown:hover{
                      -webkit-animation: vibrate 10ms linear infinite alternate;
                      -moz-animation: vibrate 10ms linear infinite alternate;
                      -o-animation: vibrate 10ms linear infinite alternate;
                      animation: vibrate 10ms linear infinite alternate;
              }

              @-webkit-keyframes vibrate{
                      from {
                              -webkit-transform: rotate(-2deg);
                      }
                      to{
                              -webkit-transform: rotate(2deg);
                      }
              }
              @-moz-keyframes vibrate{
                      from {
                              -moz-transform: rotate(-2deg);
                      }
                      to{
                              -moz-transform: rotate(2deg);
                      }
              }
              @-o-keyframes vibrate{
                      from {
                              -o-transform: rotate(-2deg);
                      }
                      to{
                              -o-transform: rotate(2deg);
                      }
              }
              @keyframes vibrate{
                      from {
                              transform: rotate(-2deg);
                      }
                      to{
                              transform: rotate(2deg);
                      }
              }

    </style>
    
    <script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA___eyQMCgVAkwXoNOS8-sX7nznS3NJq0&sensor=true">
    </script>

	<script type="text/javascript">
           $(document).ready(function(){
               //$("#mymodal").hide();
             localStorage.setItem("webActual","extranet")
            
           });
        </script>
</head>
<body>
    
    <%@ include file="navegacionExtranet.jsp" %>
    
                <div id ="flechaDown" style ="float: right;">
                    <!--
                  Ver Almacenes  <img src ="../resources/png/glyphicons_212_down_arrow.png" />
                    -->
                </div>
			        
                <div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h2>Extranet</h2>
					<p>Extranet para los clientes. Vea el estado de sus paquetes, su historial de envíos y muchas opciones más.</p>    
				</div>
    		</div>
    		
    	</div>
                
    	<div class="container-fluid">
			<div class="row-fluid">
				<div class = "span10 offset1">
					<table id = "tablita" class="table table-hover table-bordered table-striped">
						<thead>
                			<tr>
                  				<th>#</th>
                  				<th style="background-color: #7ACF7A">Cliente receptor</th>
                  				<th style="background-color: #CFB39A">Ciudad de Partida</th>
                  				<th style="background-color: #CECE41">Ciudad de Llega</th>
                  				<th style="background-color: #55AFEB">Fecha de Salida</th>
                  				<th style="background-color: #959FC9">Fecha de Llegada</th>
                                                <th style="background-color: #FFCDF9">Estado </th>
                                                <th style="background-color: #F1AF73"> Código de Rastreo </th>
                                                <th> O_O </th>
                  				<!--  <th></th> -->
                			</tr>
              			</thead>
                                                <div id ="progSimulacion" class="progress progress-striped active" style="width: 100%;">
                                            <div class="bar" style="width: 100%;">..Cargando..</div>
                                            </div>
			  			<tbody id = "cuerpoTabla">
                
              			</tbody>
			  
					</table>
				</div>
			</div>
		</div>

          
          
    	
      </body>
</html>