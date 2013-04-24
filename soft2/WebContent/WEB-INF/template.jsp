<!DOCTYPE html>
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<!-- jquery & jquery ui-->
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.8.2.js" type="text/javascript"></script>
		<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js" type="text/javascript"></script>
		
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
		
		<title>Pacote Soft</title>
	</head>
	
	<body>
		<div id = "barraNavegador" class="navbar">
			<div class="navbar-inner">
				<a class="brand" href="#">Pacote-Soft</a>
				<ul class="nav">
					<li class="active"><a href="#">Web Cliente</a></li>
					<li><a href="extranet">Extranet</a></li>
					<li><a href="#">Móvil</a></li>
					<li class="divider-vertical"></li>
					<li>
						<ul class="nav nav-pills">
							<li class="dropdown">
								<a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">Configuración <b class="caret"></b></a>
								<ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
									<li><a tabindex="-1" href="#">Administrar Perfiles</a></li>
									<li><a tabindex="-1" href="#">Administrar Usuario</a></li>
									<li><a tabindex="-1" href="#">Administrar Vuelos :D</a></li>
									<li class="divider"></li>
									<li><a tabindex="-1" href="#">Salir</a></li>
								</ul>
							</li>				
						</ul>						
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>