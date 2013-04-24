<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		<script src = "/soft/resources/js/SistemaWeb/Pedido/checkoutpedido.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Check Out</h1>
					<p>Realice el Check Out de un pedido</p>
					<p>
						<a id = "checkOut" href="" role="button" class="btn btn-primary btn-large" data-toggle="modal">Check Out</a>
					</p>
				</div>
    		</div>
    	</div>
    	
    	
    	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; ">
        	<div class="modal-header">
            	<button id = "closeM" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              	<h3 id="myModalLabel">Formulario - Check Out</h3>
              	<div  id = "cargando" class="progress progress-striped active" style="display:none;">
  					<div class="bar" style="width: 98%;"></div>
				</div>
            </div>
            <div class="modal-body">
            
            	<h4>Datos del Pedido</h4>
            	
            	<div class = "form-horizontal">
  					
  					
  					<div class="control-group">
  						<label class="control-label">Código del Pedido</label>	
    					<div class="controls">
      						<input type="text" id="inputPedido" placeholder="Pedido">
    					</div>
  					</div>
  					
                                        <section id ="datosPedido" style="display:none">
  					<div class="control-group">
  						<label class="control-label">Tipo de Documento</label>	
    					<div class="controls">
      					<input type ="text" id = "tipoDoc" placeholder="tipo doc" readonly/>	
                                           
    					</div>
  					</div>
  					
  					
  					
  					<div class="control-group">
  						<label class="control-label">Nro. de Documento</label>	
    					<div class="controls">
      						<input type="text" id="inputNroDoc" placeholder="Documento" readonly>
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Nombre</label>	
    					<div class="controls">
      						<input type="text" id="inputNombre" placeholder="Nombre" readonly>
    					</div>
                                    <div style="display:none" id ="mensajeCheck" class="alert alert-success">
				
                                    </div>
  					</div>
                                        </section>
  					
  				</div>
           
            </div>
            <div class="modal-footer">
              <button id = "reset" class="btn" data-dismiss="modal">Cerrar Formulario</button>
              <button id = "checkcheck" class="btn btn-primary">Check Out</button>
              <button id ="confirmarCheckOut" style ="display:none" type="button" class="btn btn-success">Confirmar</button>
          	</div>
          </div>
    	
    	
		
	</body>
	
</html>