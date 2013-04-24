<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		
		<script src = "/soft/resources/js/SistemaWeb/Incidencia/registrarIncidencia.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<input id = "inputId" value = "" style="display: none;">
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Incidencia</h1>
					<p>Realice el registro de una incidencia a la base de datos</p>
					<p>
						<a id = "registroIncidencia" href="" role="button" class="btn btn-primary btn-large" data-toggle="modal">Registrar una nueva Incidencia</a>
					</p>
				</div>
    		</div>
    	</div>
    	
    	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; ">
        	<div class="modal-header">
            	<button id = "closeM" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              	<h3 id="myModalLabel">Formulario - Registro Incidencia</h3>
              	<div  id = "cargando" class="progress progress-striped active" style="display:none;">
  					<div class="bar" style="width: 98%;"></div>
				</div>
            </div>
            <div class="modal-body">
            
            	<h4>Descripción detallada</h4>
            	
            	<div class = "form-horizontal">
  					
  					<div class="control-group">
	  					<label class="control-label">Username</label>	
	    				<div class="controls">
	      					<input type="text" id="inputEmailOld" placeholder="Email ó Documento">
	      					<img id = "wait" src = "/soft/resources/png/clock.png" style="display: none;"></img>
	      					<img id = "ok" src = "/soft/resources/png/circle_ok.png" style="display: none;"></img>
	      					<img id = "wrong" src = "/soft/resources/png/circle_remove.png" style="display: none;"></img>
	    				</div>
	  				</div>
	  				
	  				<div class="control-group">
	  					<label class="control-label">Vuelo</label>	
	    				<div class="controls">
	      					<input type="text" id="inputVuelo" placeholder="Nro. Vuelo">
	    				</div>
	  				</div>
  					
  					<div class="control-group">
  						<label class="control-label">Descripción</label>	
    					<div class="controls">
      						<textarea id = "reclamo" style="width: 326px; height: 136px;"></textarea>
    					</div>
  					</div>
  					
  				</div>
           
            </div>
            <div class="modal-footer">
              <button id = "reset" class="btn" data-dismiss="modal">Cerrar Formulario</button>
              <button id = "realizarRegistro" class="btn btn-primary">Registrar Incidencia</button>
          	</div>
          </div>
		
	</body>
	
</html>