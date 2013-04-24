<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		
		<script src = "/soft/resources/js/SistemaWeb/Almacen/registrarAlmacen.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Almacén</h1>
					<p>Realice el registro de un almacén a la base de datos</p>
					<p>
						<a id = "registroAlmacen" href="" role="button" class="btn btn-primary btn-large" data-toggle="modal">Registrar un nuevo Almacén</a>
					</p>
				</div>
    		</div>
    	</div>
    	
    	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; ">
        	<div class="modal-header">
            	<button id = "closeM" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              	<h3 id="myModalLabel">Formulario - Registro almacén</h3>
            </div>
            <div class="modal-body">
            
            	<h4>Datos del Almacén</h4>
            	
            	<div class = "form-horizontal">
  					
  					<div class="control-group">
  						<label class="control-label">Continente</label>	
    					<div class="controls">
      						<select id = "tipoCont">
							</select>
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">País</label>	
    					<div class="controls">
      						<select id = "tipoPais">
							</select>
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Nombre de la ciudad</label>	
    					<div class="controls">
      						<input type="text" id="inputCiudad" placeholder="Ciudad">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Latitud</label>	
    					<div class="controls">
      						<input type="text" id="inputLat" placeholder="Latitud">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Longitud</label>	
    					<div class="controls">
      						<input type="text" id="inputLong" placeholder="Longitud">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Capacidad</label>	
    					<div class="controls">
      						<input type="text" id="inputCap" placeholder="Capacidad">
    					</div>
  					</div>
  					
  					
  				</div>
           
            </div>
            <div class="modal-footer">
              <button id = "reset" class="btn" data-dismiss="modal">Cerrar Formulario</button>
              <button id = "realizarRegistro" class="btn btn-primary">Registrar Almacén</button>
          	</div>
          </div>
    	
	
	</body>
	
</html>