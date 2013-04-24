<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<!-- jquery & jquery ui-->
		<link rel="stylesheet" href="/soft/resources/css/jquery/ui-lightness/jquery-ui-1.9.1.custom.css" />
		<script src="/soft/resources/js/jquery/jquery-1.8.2.js" type="text/javascript"></script>
		<script src="/soft/resources/js/jquery/jquery-ui-1.9.1.custom.js" type="text/javascript"></script>
		
		<!-- bootstrap -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="/soft/resources/css/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="/soft/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
		<link href="/soft/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet">
		<script src="/soft/resources/js/bootstrap/bootstrap.js"></script>
		<script src="/soft/resources/js/bootstrap/bootstrap.min.js"></script>
		
		<!-- Tools javascript del bootstrap, deberia incluirlos todos en un solo .js -->
		<script src="/soft/resources/js/bootstrap/bootstrap-dropdown.js"></script>
		<script src="/soft/resources/js/bootstrap/bootstrap-carousel.js"></script>
		<script src="/soft/resources/js/bootstrap/bootstrap-modal.js"></script>
		
                <!-- jquery scroll -->
               
		<title>Paque-Tesis</title>
		<!--
		<script src = "/soft/resources/js/Extranet/editarNombre.js"></script>
		-->
		<script src="/soft/resources/js/Home/snow.js"></script>
		<script src = "/soft/resources/js/Home/fokus.min.js"></script>
		<script type="text/javascript" src="/soft/resources/js/jquery/jquery.scrollTo-1.4.3.1.js"></script>
		<script type = "text/javascript">
		
			var datos;
			
			
			
			$(document).ready(function(){
				$("#editarDatos").click(function(){
	                //$(".mo").modal('toggle');
	                //$("#modalCliente").show();
	                
	               $('#modalCliente').modal('show');
	               cargarLala();
	                return false;
	            });
				
				$("#sistemaWeb").click(function(){
					$(location).attr("href","/soft/loginWeb");
				});
				
				$("#byebye").click(function(){
					$(location).attr("href","/soft/");
				});
				
			});
			
			
			function cargarLala(){
				
				if(1 != 0){
				
					
					
					$("#guardarMoficiados").click(function(){
						
						var datosCliente = {
							id: datos.id,
							nombre: datos.nombre,
							apellidos: datos.apellidos,
							sexo: datos.sexo,
							estado: datos.estado,
							user:datos.user,
							email: datos.email,
							password: $("#inputCont").attr("value"),
							telefono: $("#inputTelef").attr("value"),
							direccion: $("#inputDireccion").attr("value"),
							rol_id : datos.rol_id,
							pais_id : datos.pais_id,
							documento_id: datos.documento_id,
							numero_documento: datos.numero_documento
						};
						
						var jsonData = JSON.stringify(datosCliente);
						
						console.log(jsonData);
						
						$.ajax({
					        type: "POST",
					        data: jsonData,
					        dataType: "json",
					        contentType: "application/json; charset=utf-8",
					        url: "../Usuario/modificarUsuario",
					        beforeSend: function(){
					        	
					        },        
					        success: function(data){
					        	if(data.me==""){
					        		$(location).attr("href","/soft/extranet/mapa");
					        	}
					        	else{
					        		alert(data.me);
					        	}
					        	
					        }
					    });
					
					});
					
					
					//$("#modalCliente").modal();
				
					var data = {
						id: localStorage.getItem("idcliente")
					};
					
					var jsonData = JSON.stringify(data);
					
					console.log(jsonData);
					$.ajax({
				        type: "POST",
				        data: jsonData,
				        dataType: "json",
				        contentType: "application/json; charset=utf-8",
				        url: "../Usuario/buscarUsuario",
				        beforeSend: function(){
				        	
				        },        
				        success: function(data){
				        	datos = data.listaUser[0];
				        	$("#inputCont").attr("value",datos.password);
				        	$("#inputCont2").attr("value",datos.password);
				        	$("#inputTelef").attr("value",datos.telefono);
				        	$("#inputDireccion").attr("value",datos.direccion);
				        }
				    });
					
					
					
					
				}
				
				
				
				
			}
			
		
		</script>
		
		<script src = "/soft/resources/js/Home/validaciones.js"></script>
		
		 <div id="modalCliente" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; ">
        	<div class="modal-header">
            	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              	<h3 id="myModalLabel">Formulario - Modificar datos</h3>
            </div>
            <div class="modal-body">
            
            	<h4>Datos Personales</h4>
            	
            	
            	
            	<div class = "form-horizontal">
            	  					
  					<div class="control-group">
  						<label class="control-label">Nueva Contraseña</label>	
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
  					
  				</div>
           
            </div>
            <div class="modal-footer">
              <button class="btn" data-dismiss="modal">Cancelar Cambios</button>
              <button id = "guardarMoficiados" class="btn btn-primary">Guardar Cambios</button>
            </div>
          </div>
		
