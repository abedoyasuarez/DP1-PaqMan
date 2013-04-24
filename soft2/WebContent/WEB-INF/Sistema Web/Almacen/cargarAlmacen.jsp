<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		<script src = "/soft/resources/js/SistemaWeb/Vuelos/cargarVuelos.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<div class = "container-fluid">
			<div class = "row-fluid">
				<div class="hero-unit">
					<h1>Archivos - Almacenes</h1>
					<p>Escoja un archivo para almacenar...</p>
			 
					<form enctype="multipart/form-data">
                    	<input name="archivo" id='archivo' class = "input" type="file" />
                    </form>

					<div class = "span2 offset10">
						<button id = "guardar" class="btn btn-inverse" type="button">Guardar archivo</button>
					</div>
				</div>
			</div>
		</div>
		
		
	</body>
	
</html>