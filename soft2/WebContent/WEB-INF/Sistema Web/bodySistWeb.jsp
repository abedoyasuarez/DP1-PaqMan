<div id = "barraNavegador" class="navbar navbar-inverse" >
			<div class="navbar-inner">
				<a class="brand" href="/soft/sistemaWeb">PT - Sistema Web</a>
				<ul class="nav">
					<li id = "divisor1" class="divider-vertical"></li>
					<li id = "barraOpc1">
						<ul class="nav nav-pills">
							<li class="dropdown">
								<a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">Usuario <b class="caret"></b></a>
								<ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
									<li><a tabindex="-1" href="/soft/Usuario/registrarUsuario">Registrar</a></li>
									<li><a tabindex="-1" href="/soft/Usuario/modificarUsuario">Modificar</a></li>
									<li><a tabindex="-1" href="/soft/Usuario/eliminarUsuario">Eliminar</a></li>
								</ul>
							</li>				
						</ul>						
					</li>
					<li id = "barraOpc2">
						<ul class="nav nav-pills">
							<li class="dropdown">
								<a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">Almacenes <b class="caret"></b></a>
								<ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
									<li><a tabindex="-1" href="/soft/Almacen/registrarAlmacen">Registrar</a></li>
									<li><a tabindex="-1" href="/soft/Almacen/modificarAlmacen">Modificar</a></li>
									<li><a tabindex="-1" href="/soft/Almacen/eliminarAlmacen">Eliminar</a></li>
								</ul>
							</li>				
						</ul>						
					</li>
					<li id = "barraOpc3">
						<ul class="nav nav-pills">
							<li class="dropdown">
								<a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">Archivos <b class="caret"></b></a>
								<ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
									<li><a tabindex="-1" href="/soft/Vuelos/cargarVuelos">Vuelos</a></li>
									<li><a tabindex="-1" href="/soft/Almacen/cargarAlmacen">Almacenes</a></li>
									<li><a tabindex="-1" href="/soft/Pedido/cargarPedido">Pedido</a></li>
								</ul>
							</li>				
						</ul>						
					</li>
					<li id = "barraOpc4">
						<ul class="nav nav-pills">
							<li class="dropdown">
								<a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">Pedidos <b class="caret"></b></a>
								<ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
									<li><a tabindex="-1" href="/soft/Pedido/registrarPedido">Registrar</a></li>
									<li><a tabindex="-1" href="/soft/Pedido/CheckOut">Check Out</a></li>
									<li><a tabindex="-1" href="http://www.correos.es/contenido/02E-EnviarPaq/03-PaqInternacional/DECLARACION_JURADA_VALOR.pdf">Declaración Jurada</a></li>
                                    
									
								</ul>
							</li>				
						</ul>						
					</li>
					<li id = "barraOpc5">
						<ul class="nav nav-pills">
							<li class="dropdown">
								<a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">Incidencia <b class="caret"></b></a>
								<ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
									<li><a tabindex="-1" href="/soft/Incidencia/registrarIncidencia">Registrar</a></li>
									<li><a tabindex="-1" href="/soft/Incidencia/modificarIncidencia">Modificar</a></li>
									<li><a tabindex="-1" href="/soft/Incidencia/eliminarIncidencia">Eliminar</a></li>
								</ul>
							</li>				
						</ul>						
					</li>
					<li id = "barraOpc6">
						<ul class="nav nav-pills">
							<li class="dropdown">
								<a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">Reportes <b class="caret"></b></a>
								<ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
									<li><a tabindex="-1" href="/soft/reporte1">Paquetes por Pais - Hoy</a></li>
									<li><a tabindex="-1" href="/soft/reporte2">Paquetes por Ciudad - Hoy</a></li>
									<li><a tabindex="-1" href="/soft/reporte3">Estadisticas anuales</a></li>
									<li><a tabindex="-1" href="/soft/gerente/simular">Simulacion</a></li>
									<li><a tabindex="-1" href="/soft/diadia/simularDiaDia">Dia - Dia</a></li>
								 </ul>
							</li>				
						</ul>						
					</li>
					<li id = "divisor2" class="divider-vertical"></li>
					<li id = "barraOpc7">
						<ul class="nav nav-pills">
							<li class="dropdown">
								<a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">Opciones <b class="caret"></b></a>
								<ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                                    <li><a id ="reloj" tabindex="-1" href="#">Reloj</a></li>
									<li id = "divReloj" class="divider"></li>
									<li><a href = "#" id = "salirWeb" tabindex="-1">Salir</a></li>
								</ul>
							</li>				
						</ul>						
					</li>
				</ul>
			</div>
		</div>
		
		
		
		
		 <div id ="modalFecha" class="modal hide fade">
         	<div class="modal-header">
         		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
         		<h3>Hora sistema</h3>
				<div  id = "loadReloj" class="progress progress-striped active">
  					<div class="bar" style="width: 40%;"></div>
				</div>
         	</div>
	         <div class="modal-body">
	         	<fieldset>
	         		<label>Fecha Sistema</label>
	         		<input type="text" name="fecha" id="fechaSistema" value="" />
	         	</fieldset>
	         </div>
	         <div id = "confirma" class="alert alert-success" style = "display:none;">
              		<button id = "cierrateSesamo" type="button" class="close" data-dismiss="alert">×</button>
              		<strong>Exito!</strong> Se cambio la hora del sistema.
            	</div>
	         <div class="modal-footer">
	         	<a id ="cambiarHora" href="#" class="btn btn-primary">Cambiar!</a>
	         	
	         </div>
         </div>