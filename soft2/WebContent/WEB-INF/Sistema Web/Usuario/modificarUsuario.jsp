<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		
		<script src = "/soft/resources/js/bootstrap/jquery.columnfilters.js"></script>
		<script src = "/soft/resources/js/SistemaWeb/Usuario/modificarUsuario.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<input type = "text" id = "inputId">
		
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Usuarios</h1>
					<p>Modifique los datos de los usuario registrados en el sistema.</p>
				</div>
    		</div>
    	</div>
    	
		<div class="container-fluid">
			<div class="row-fluid">
				<div class = "span10 offset1">
					<table id = "tableFilter" class="table table-hover table-bordered table-striped">
						<thead>
                			<tr>
                  				<th>#</th>
                  				<th>Nombre</th>
                  				<th>Apellidos</th>
                  				<th>Email</th>
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
              	<h3 id="myModalLabel">Formulario - Modificar usuario</h3>
              	<div  id = "cargando" class="progress progress-striped active" style="display:none;">
  					<div class="bar" style="width: 98%;"></div>
				</div>
            </div>
            <div class="modal-body">
            
            	<h4>Datos Personales</h4>
            	
            	
            	
            	<div class = "form-horizontal">
            	
            		<div class="control-group">
  						<label class="control-label">Tipo de documento</label>	
    					<div class = "controls">
    					<select id = "tipoDoc">
  							<option value = "1">DNI</option>
  							<option value = "2">Pasaporte</option>
						</select>
						</div>
  					</div>
  					
  					
            	
            		<div class="control-group">
  						<label class="control-label">Nro. de Documento</label>	
    					<div class="controls">
      						<input type="text" id="inputNro" placeholder="Documento">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Rol</label>	
    					<div class="controls">
      						<select id = "tipoRol">
  								
							</select>
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Nombre</label>	
    					<div class="controls">
      						<input type="text" id="inputNombre" placeholder="Nombre">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Apellidos</label>	
    					<div class="controls">
      						<input type="text" id="inputApell" placeholder="Apellidos">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Sexo</label>	
    					<div class="controls">
      						<select id = "tipoSexo">
  								<option value = "1">Masculino</option>
  								<option value = "2">Femenino</option>
							</select>
    					</div>
  					</div>
  					
  					
  				
  					<hr>
  				
  					<h4>Datos de contacto</h4>
  				
  					<div class="control-group">
  						<label class="control-label">Email</label>	
    					<div class="controls">
      						<input type="text" id="inputEmail" placeholder="Email">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Contraseña</label>	
    					<div class="controls">
      						<input type="password" id="inputCont" placeholder="Contraseña">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Confirmar Contraseña</label>	
    					<div class="controls">
      						<input type="password" id="inputCont2" placeholder="Contraseña">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Teléfono</label>	
    					<div class="controls">
      						<input type="text" id="inputTelef" placeholder="Teléfono">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Dirección</label>	
    					<div class="controls">
      						<input type="text" id="inputDireccion" placeholder="Dirección">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">País</label>	
    					<div class="controls">
      						<select id = "tipoPais">
  							<option value = "1">Perú</option>
  							<option value = "2">Colombia</option>
						</select>
    					</div>
  					</div>
  				</div>
           
            </div>
            <div class="modal-footer">
              <button class="btn" data-dismiss="modal">Cancelar Cambios</button>
              <button id = "realizarRegistro" class="btn btn-primary">Guardar Cambios</button>
            </div>
          </div>
		
		
		
	</body>
	
</html>