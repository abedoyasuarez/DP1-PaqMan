<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		<script src = "/soft/resources/js/SistemaWeb/Pedido/registrarPedido.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<input id = "inputId" value = "">
		<!--<input id = "inputIdR">-->
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Pedidos</h1>
					<p>Registre un nuevo pedido de uno de nuestros clientes o de un cliente nuevo</p>
					<p>
						<a id = "registroPedido" href="" role="button" class="btn btn-primary btn-large" data-toggle="modal">Registrar un nuevo Pedido</a>
					</p>
				</div>
    		</div>
    	</div>
    	
    	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; ">
        	<div class="modal-header">
            	<button id = "cerrar" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              	<h3 id="myModalLabel">Formulario - Registrar envío</h3>
              	<div  id = "cargando" class="progress progress-striped active">
  					<div class="bar" style="width: 40%;"></div>
				</div>
            </div>
            
            <div class="modal-body">
            
            	<div class = "form-horizontal">
            		
            		<h4>Datos del Envío</h4>
            		
            		<div class="control-group">
  						<label class="control-label">Cantidad de paquetes</label>	
    					<div class="controls">
      						<input type="text" id="inputCant" placeholder="Nro. Paquetes">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Ciudad de partida</label>	
    					<div class="controls">
      						<select id = "ciudadI">
  								
                                                </select>
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Ciudad de destino</label>	
    					<div class="controls">
      						<select id = "ciudadF">
  								
							</select>
    					</div>
  					</div>
  					
  					<hr>
            		
            		<h4>Datos Cliente Receptor</h4>
  					
  					<div class="control-group">
  						<label class="control-label">Tipo de documento</label>	
    					<div class = "controls">
    					<select id = "tipoDocR">
  							<option value = "1">DNI</option>
  							<option value = "2">Pasaporte</option>
						</select>
						</div>
  					</div>
            	
            		<div class="control-group">
  						<label class="control-label">Nro. de Documento</label>	
    					<div class="controls">
      						<input type="text" id="inputNroR" placeholder="Documento">
      						<img id = "waitR" src = "/soft/resources/png/clock.png"></img>
	      					<img id = "okR" src = "/soft/resources/png/circle_ok.png"></img>
	      					<img id = "wrongR" src = "/soft/resources/png/circle_remove.png"></img>
    					</div>
  					</div>
  					
  					
  					<div id = "divNombreR" class="control-group">
  						<label class="control-label">Nombre</label>	
    					<div class="controls">
      						<input type="text" id="inputNombreR" placeholder="Nombre">
    					</div>
  					</div>
  					
  					<div id = "divApellR" class="control-group">
  						<label class="control-label">Apellidos</label>	
    					<div class="controls">
      						<input type="text" id="inputApellR" placeholder="Apellidos">
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Teléfono</label>	
    					<div class="controls">
      						<input type="text" id="inputTelefR" placeholder="Teléfono">
    					</div>
  					</div>
  					
            		<div id = "groupEmail">
            			
            			<hr>
            			
	            		<h4>Cliente realizando el envío</h4>
	  					
	  					<div class="control-group">
	  						<label class="control-label">Username</label>	
	    					<div class="controls">
	      						<input type="text" id="inputEmailOld" placeholder="Email ó Documento">
	      						<img id = "wait" src = "/soft/resources/png/clock.png"></img>
	      						<img id = "ok" src = "/soft/resources/png/circle_ok.png" ></img>
	      						<img id = "wrong" src = "/soft/resources/png/circle_remove.png"></img>
	      						<input type = "text" id = "inputId" style="display: none;">
	    					</div>
	  					</div>
  					</div>
            		
            	</div>
            	
            	<div id = "formDatos" class = "form-horizontal">
            		
            		<hr>
            		
            		<h4>Datos Personales</h4>
            		
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
  								<option value = "1">Empleado</option>
  								<option value = "2">Cliente</option>
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
  								
							</select>
    					</div>
  					</div>
  				</div>
           
            	
            	</div>
           
            <div class="modal-footer">
            	<button id = "cancelNuevo" class="btn btn-large btn-inverse" type="button">Cancelar</button>
            	<button id = "regUser" class="btn btn-large btn-info" type="button">Cliente nuevo</button>
              	<button id = "regPedidoOld" class="btn btn-large btn-warning" type="button">Registrar pedido</button>
              	<button id = "regPedidoNew" class="btn btn-large btn-success" type="button">Registrar pedido</button>
              	
          	</div>
          </div>
    	
    	
    	
    	<div id="myModal2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" style="display: none; ">
        	<div class="modal-header">
              	<h3 id="myModalLabel">Datos del Pedido registrado</h3>
            </div>
            
            <div class="modal-body">
            
            	<div class = "form-horizontal">
            		
            		<h4>Datos del Envío</h4>
            		
            		
            		<div class="control-group">
  						<label class="control-label">Usuario</label>	
    					<div class="controls">
      						<label id = "mostrarUsuario"></label>
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Código de rastreo</label>	
    					<div class="controls">
      						<label id = "mostrarCode"></label>
    					</div>
  					</div>
            		
            		
            		
            	</div>
            	
            	
    		</div>
    		 <div class="modal-footer">
              	<button id = "finish" class="btn btn-large btn-success" type="button">Aceptar</button>
          	</div>
          </div>
    	
    	
		
	</body>
	
</html>