<!DOCTYPE html>
<html>
	<head>
		
		 <script type="text/javascript"
                    src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA___eyQMCgVAkwXoNOS8-sX7nznS3NJq0&sensor=true">
                </script>
               <script type="text/javascript" src="https://www.google.com/jsapi"></script>
                <style type="text/css">
 #map_canvas {
        margin: 0;
        padding: 0;
        height: 600px;
        width: 100%;
      }
      
      #infotabla {
          background-color: #eee;
          height: 550px;
          width: 400px;
      }    
      #caja_flotante{
        position: absolute;
        top:100px;
        left: 10px;
        border: 1px solid #CCC;
        background-color: #F05D38;
        font-size: 20px;
        padding-left: 10px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-right: 10px;
        z-index: 10000000;
        -moz-border-radius: 10px; /* Firefox*/ 
        -ms-border-radius: 10px; /* IE 8.*/ 
        -webkit-border-radius: 10px; /* Safari,Chrome.*/ 
        border-radius: 10px; /* El estándar.*/
    }
    
    #caja_flotante1{
        position: absolute;
        top:100px;
        left: 10px;
        border: 1px solid #CCC;
        background-color: #F05D38;
        font-size: 20px;
        padding-left: 10px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-right: 10px;
        z-index: 10000000;
        -moz-border-radius: 10px; /* Firefox*/ 
        -ms-border-radius: 10px; /* IE 8.*/ 
        -webkit-border-radius: 10px; /* Safari,Chrome.*/ 
        border-radius: 10px; /* El estándar.*/
    }
</style>
    
                <%@ include file="../headSistWeb.jsp" %>
                
		<script type ="text/javascript">
		
		$(document).ready(function(){
			
			$("#iniciaDiaDia").click(function(){
				alert("oscar");
	        	$.ajax({
	        		  type: "POST",
	        		  data: {},
	        		  dataType: "json",
	        		  contentType: "application/json; charset=utf-8",
	        		  url: "../Simulacion/iniciaDiaDia",      
	        		  success: function(data){
	        			alert("simulando");
	        		  }
	        	}); 
	        });
		});
		
        
        </script>
		
		
	</head>
	 
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<div id="div_diadia">
			   <!-- <button id="iniciaDiaDia" class="btn btn-success" style ="margin-bottom: 10px;" >Simula!</button> --> 
			    <a id = "iniciaDiaDia" href="" role="button" class="btn btn-primary btn-large" data-toggle="modal">Empezar Simulación</a>
		</div>
	</body>
	
</html>