<!DOCTYPE html>
<html>
	<head>
		
		<%@ include file="../headSistWeb.jsp" %>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<script type="text/javascript" src="/soft/resources/js/SistemaWeb/Reportes/reporte3.js"></script>
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<div  id = "cargando" class="progress progress-striped active" style="display:none;">
  			<div class="bar" style="width: 98%;"></div>
		</div>
		
		<div class="container-fluid">
  			<div class="row-fluid">
   				<div class="span5">
  					<span>Escoja el Año: </span>	
      				<select id = "tipoAnho">
      					<option value = "2012">2012</option>
      					<option value = "2011">2011</option>      					
      				</select>
  				</div>
			</div>
		</div>
		
		<div class="container-fluid">
  			<div class="row-fluid">
   				<div class="span5 offset2">
					<div id="chart_div" style="width: 900px; height: 500px;"></div>
				</div>
			</div>
		</div>
		
	</body>
	
</html>