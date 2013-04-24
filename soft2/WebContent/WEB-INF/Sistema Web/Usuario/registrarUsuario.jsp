<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		
		<script src = "/soft/resources/js/SistemaWeb/Usuario/registrarUsuario.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Usuarios</h1>
					<p>Realice el registro de un usuario a la base de datos y br�ndele numerosos beneficios al instante</p>
					<p>
						<a id = "registroCliente" href="" role="button" class="btn btn-primary btn-large" data-toggle="modal">Registrar un nuevo Usuario</a>
					</p>
				</div>
    		</div>
    	</div>
    	
    	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; ">
        	<div class="modal-header">
            	<button id = "closeM" type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
              	<h3 id="myModalLabel">Formulario - Registro usuario</h3>
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
  						<label class="control-label">Contrase�a</label>	
    					<div class="controls">
      						<input type="password" id="inputCont" placeholder="Contrase�a">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Confirmar Contrase�a</label>	
    					<div class="controls">
      						<input type="password" id="inputCont2" placeholder="Contrase�a">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Tel�fono</label>	
    					<div class="controls">
      						<input type="text" id="inputTelef" placeholder="Tel�fono">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Direcci�n</label>	
    					<div class="controls">
      						<input type="text" id="inputDireccion" placeholder="Direcci�n">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Pa�s</label>	
    					<div class="controls">
      						<select id = "tipoPais">
  								<option value = "1">Per�</option>
  								<option value = "2">Colombia</option>
							</select>
    					</div>
  					</div>
  					
  					
  				</div>
           
            </div>
            <div class="modal-footer">
              <button id = "reset" class="btn" data-dismiss="modal">Cancelar registro</button>
              <button id = "realizarRegistro" class="btn btn-primary">Registrar Usuario</button>
            </div>
          </div>
	</body>
	
</html>