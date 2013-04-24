<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		
		<script src = "/soft/resources/js/bootstrap/jquery.columnfilters.js"></script>
		<script src = "/soft/resources/js/SistemaWeb/Incidencia/modificarIncidencia.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<input type = "text" id = "inputId" style="display: none;">
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Incidencia</h1>
					<p>Modifique una incidencia registrada en la base de datos</p>
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
                  				<th>Fecha de registro</th>
                  				<th>Descripción</th>
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
              	<h3 id="myModalLabel">Formulario - Modificar Incidencia</h3>
            </div>
            <div class="modal-body">
            
            	<h4>Descripción detallada</h4>
            	
            	<div class = "form-horizontal">
  					
  					<div class="control-group">
  						<label class="control-label">Descripción</label>	
    					<div class="controls">
      						<textarea id = "reclamo"></textarea>
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