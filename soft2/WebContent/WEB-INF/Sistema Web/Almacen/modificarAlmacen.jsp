<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		
		<script src = "/soft/resources/js/bootstrap/jquery.columnfilters.js"></script>
		<script src = "/soft/resources/js/SistemaWeb/Almacen/modificarAlmacen.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<input type = "text" id = "inputId" style="display: none;">
		
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Almacén</h1>
					<p>Modifique los datos de los almacenes registrados en el sistema.</p>
				</div>
    		</div>
    	</div>
    	
    	<div class="container-fluid">
			<div class="row-fluid">
				<div class = "span10 offset1">
					<table id = "tableFilter" class="table table-hover table-bordered table-striped" style="display:none">
						<thead>
                			<tr>
                  				<th>#</th>
                  				<th>Nombre de la ciudad</th>
                  				<th>Continente</th>
                  				<th>Pais</th>
                  				<th>Capacidad</th>
                  				<th>Modificar</th>
                			</tr>
              			</thead>
			  
			  			<tbody id = "cuerpoTabla">
                
              			</tbody>
			  
					</table>
				</div>
			</div>
		</div>
		
		
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; ">
        	<div class="modal-header">
            	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              	<h3 id="myModalLabel">Formulario - Modiicar almacén</h3>
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
              <button class="btn" data-dismiss="modal">Cerrar Formulario</button>
              <button id = "guardarCambios" class="btn btn-primary">Guardar cambios</button>
          	</div>
          </div>
		
		
    	
    </body>
    
</html>