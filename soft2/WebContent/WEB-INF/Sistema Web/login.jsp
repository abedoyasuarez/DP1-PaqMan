<!DOCTYPE html>
<html>
  
	<head>
		
		<%@ include file="headSistWeb.jsp" %>
		<script src = "/soft/resources/js/SistemaWeb/login.js"></script>
		
		
	</head>
	
	<body>
			
		<%@ include file="bodySistWeb.jsp" %>
               
       
                
                     
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Sistema Web</h1>
					<p>Paque-Tesis</p>
				</div>
    		</div>
    	</div>
    	
    	
    	
    	<div class="modal" style="position: relative; top: auto; left: auto; margin: 0 auto 20px; z-index: 1; max-width: 100%;display: block;">
              <div class="modal-header">
                <h3>Login</h3>
              </div>
              <div class="modal-body">
                <div class = "form-horizontal">
            		
            		<div class="control-group">
  						<label class="control-label">Usuario</label>	
    					<div class="controls">
      						<input type="text" id="inputUser" placeholder="Email o Documento">
    					</div>
  					</div>
  						
  					<div class="control-group">
  						<label class="control-label">Contraseña</label>	
    					<div class="controls">
      						<input type="password" id="inputPass" placeholder="Contraseña" onkeypress="return runScript(event)">
    					</div>
  					</div>
  					
  					<div id = "alertaPass" class="alert alert-block alert-error fade in">
            			<button type="button" class="close" data-dismiss="alert">×</button>
           				<h4 class="alert-heading">Usuario o contraseña incorrecta</h4>
            			<p>¿No recuerda su contraseña?</p>
          			</div>
  					
  					
  				</div>
              </div>
              <div class="modal-footer">
              	<a id = "recordarPass" class="btn btn-danger">Recordar Contraseña</a>
                <a id = "login" class="btn btn-inverse">Login</a>
              </div>
              
          </div>
  
	
	</body>
	
</html>


