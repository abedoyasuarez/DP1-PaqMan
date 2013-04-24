<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headHome.jsp" %>
		<script src = "/soft/resources/js/Extranet/cancelarPedido.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodyHome.jsp" %>
	
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Pedidos del usuario</h1>
					<p>Pedidos del usuario en línea {Nombre del Usuario}</p>
				</div>
    		</div>
    	</div>
    	
    	<div class="container-fluid">
			<div class="row-fluid">
				<div class = "span10 offset1">
					<table id = "tablita" class="table table-hover table-bordered table-striped">
						<thead>
                			<tr>
                  				<th>#</th>
                  				<th style="background-color: green">Cliente receptor</th>
                  				<th>Ciudad de Partida</th>
                  				<th>Ciudad de Llega</th>
                  				<th>Fecha de Salida</th>
                  				<th>Fecha de Llegada</th>
                  				<!--  <th></th> -->
                			</tr>
              			</thead>
			  
			  			<tbody id = "cuerpoTabla">
                
              			</tbody>
			  
					</table>
				</div>
			</div>
		</div>
		
		
		<!-- 
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; ">
        	<div class="modal-header">
            	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              	<h3 id="myModalLabel">Advertencia - CANCELANDO PEDIDO</h3>
            </div>
            <div class="modal-body">
            
            	<h4>Está seguro que desea cancelar dicho pedido?. Se procederá
            	a buscar una ruta de retorno</h4>
            	
            </div>
            <div class="modal-footer">
              <button class="btn" data-dismiss="modal">Cancelar</button>
              <button id = "cancelarPedido" class="btn btn-danger">Eliminar</button>
            </div>
          </div>
			-->
	
	</body>
	
</html>