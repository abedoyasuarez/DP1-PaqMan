<!DOCTYPE html>
<html>
	<head>
           
		<script>
		
		$(document).ready(main);
		
		function main(){
                        $("#errorLoginCapa").hide();
			$("#Extranet").addClass("active");
                          $("#registroCliente").click(function(){
                              $("#registroCliente").hide();
                              $("#loginCliente").show("slow");
                           });
                           $("html").click(function(){
                              //alert("pancho");
                              return false;
                           });
                           
                           $("#olvidoClave").click(function(){
                                //return false;
                                $("#myModal").modal("toggle");
                                return false;
                           });
                           
                           $("#ingresarCliente").click(function(){
                              var enviar = {
                                    user: $("#mailCliente").val(),
                                    password: $("#passCliente").val()
                                }
                                
                                
                                
                                    var jsonData = JSON.stringify(enviar);
                                    
                                    $.ajax({
                                        type: "POST",
                                        data: jsonData,
                                        dataType: "json",
                                        contentType: "application/json; charset=utf-8",
                                        url: "Usuario/login",
                                        beforeSend: function (){
                                            
                                        },        
                                        success: function (data){
                                        	if (data.me == ""){
                                                    localStorage.setItem("idcliente",data.id);
                                                    localStorage.setItem("nombreCliente",data.nombre);
                                                    localStorage.setItem("token",data.permisos);
                                                    $(location).attr('href','extranet/mapa');
                                                }else {
                                                    $("#errorLoginCapa").html(data.me);
                                                    $("#errorLoginCapa").show();
                                                
                                                }
                                                
                                                //$(location).attr('href','extranet/mapa');  
                                        }
                                    });    
                              
                           
                         // $("#loginCliente").hide('slow');
                         // $("body").load("extranet/mapa");
                         // return false;
                          // $("#map_canvas").show('slow');
                          // initialize();
                          
                        
                        });
                        
                          $("#recuperarClave").click(function(){
                            var data = {
                                    user : 	$("#user").val()	
                             };

                            var jsonData = JSON.stringify(data);
                          
                          $.ajax({
                            type: "POST",
                            data: jsonData,
                            dataType: "json",
                            contentType: "application/json; charset=utf-8",
                            url: "Usuario/recuperarContra",
                            
                            success: function(data){
                            if (data.me =="") {
                                alert("listo");
                            }else {
                                // $("#mensajeRastreo").html(data.me);
                            }
                        }
                        });
                             return false;
                          });
                        
                }
		
              
                
		</script>
		
	</head>
	
	  <body>
		
		
		
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Extranet</h1>
					<p>Extranet para los clientes. Vea el estado de sus paquetes, su historial de envíos y muchas opciones más.</p>
					<p>
						<button id = "registroCliente" class="btn btn-primary btn-large" >Ingresar</button>
                                         <fieldset id ="loginCliente" style = "display:none">
                                               
                                                <label>Correo</label>
                                                <input id ="mailCliente" type="text" placeholder="su mail ...">
                                                <label>Clave</label>
                                                <input id ="passCliente" type="password" placeholder="su clave...">
                            
                                                <button  id ="ingresarCliente" class="btn btn-success"> Ingresar 
                                                </button>
                                                <a id ="olvidoClave" class ="success"> Olvidó su contraseña?</a>
                                                <div class="alert alert-error" id ="errorLoginCapa">
                                                       <strong><a id="errorLogin"></a></strong> 
                                                </div>
                                            </fieldset>   
                                        </p>
                                       
				</div>
    		</div>
    	</div>
	
                 <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; ">
        	
            <div class="modal-body">
            
            	<h4>Recupere su contraseña</h4>
            	
            	<div class = "form-horizontal">
  		
  					<div class="control-group">
  						<label style ="color:#3873B3" class="control-label">Ingrese su usuario</label>	
    					<div class="controls">
      						<input type="text" id="user" placeholder="Usuario">
                                                <button id="recuperarClave" class="btn btn-success"> Recuperar 
                                                </button>
    					</div>
                                                
  					</div>
  					
  					
  				</div>
           
            </div>
            
          </div>
    	
              
	</body>
	
</html>