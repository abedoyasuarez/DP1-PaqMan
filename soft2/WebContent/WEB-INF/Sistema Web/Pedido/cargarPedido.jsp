<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		<script src = "/soft/resources/js/SistemaWeb/Pedido/cargarPedido.js"></script>
		<script src = "/soft/resources/js/jquery/ajaxfileupload.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<div class = "container-fluid">
			<div class = "row-fluid">
				<div class="hero-unit">
					<h1>Archivos - Pedidos</h1>
					<p>Escoja un archivo para almacenar...</p>
			 
					<form id="form_archivo" enctype="multipart/form-data">
					
						
					
                    	<input name="archivo" id='archivo' class = "input" type="file" />
                         
                         <!--
                         <input type="file" id="files1" name="files1[]" multiple="">
                    </form>-->
                                        
					<div class = "span2 offset10">
						<button id = "guardar" class="btn btn-inverse" type="button">Guardar archivo</button>
					</div>
                                        
				</div>
			</div>
		</div>
		
		
	</body>
	
</html>