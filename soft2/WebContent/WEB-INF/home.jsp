<!DOCTYPE html>
<html>
	<head>
		<style>
                    
                  
                    
                    .navidad {
                      position: absolute;
                      top:150px;
                      left: 10px;
                       font-size: 20px;
                      padding-left: 10px;
                      padding-bottom: 10px;
                      padding-top: 10px;
                      padding-right: 10px;
                      z-index: 10000000;
                      width: 100px;
                      height: 100px;
                      -webkit-transform: rotate(-28deg);
                      -moz-transform: rotate(-28deg);
                  }

                 #footer {
                    width: 100%;
                    clear: both;
                    background: black;
                    padding-top: 10px;
                    padding-bottom: 10px;
                    font-size: 14px;
                    height: 22px;
                    color: white;
                    }
                    #IrArriba {
                        position: fixed;
                        bottom: 30px; /* Distancia desde abajo */
                        right: 30px; /* Distancia desde la derecha */
                        z-index: 100;
                }

                #IrArriba span {
                        width: 60px; /* Ancho del botón */
                        height: 60px; /* Alto del botón */
                        display: block;
                        z-index: 100;
                        background: url(http://lh5.googleusercontent.com/-luDGEoQ_WZE/T1Ak-gta5MI/AAAAAAAACPI/ojfEGiaNmGw/s60/flecha-arriba.png) no-repeat center center;
                }
                    @-webkit-keyframes vibrate{
                            from {
                                    -webkit-transform: rotate(-20deg);
                            }
                            to{
                                    -webkit-transform: rotate(20deg);
                            }
                    }
                    @-moz-keyframes vibrate{
                            from {
                                    -moz-transform: rotate(-20deg);
                            }
                            to{
                                    -moz-transform: rotate(20deg);
                            }
                    }
                    @-o-keyframes vibrate{
                            from {
                                    -o-transform: rotate(-20deg);
                            }
                            to{
                                    -o-transform: rotate(20deg);
                            }
                    }
                    @keyframes vibrate{
                            from {
                                    transform: rotate(-20deg);
                            }
                            to{
                                    transform: rotate(20deg);
                            }
                    }

                    .navidad:hover{
                            -webkit-animation: vibrate 200ms linear infinite alternate;
                            -moz-animation: vibrate 200ms linear infinite alternate;
                            -o-animation: vibrate 200ms linear infinite alternate;
                            animation: vibrate 200ms linear infinite alternate;
                    }

                  </style>
		<%@ include file="headHome.jsp" %>
		
		
		<script>
		
		$(document).ready(main);
		
		function main(){
			localStorage.clear();
                        localStorage.setItem("paginaActual","soft");
                        localStorage.setItem("tokenUsuario","");
                        $("#WebEmp").addClass("active");
                        $("#IrArriba").hide();
			$('#myCarousel').carousel({  
				interval: 2500 // in milliseconds  
			}); 
			//$('#myCarousel').hover(function () {   
			//	$(this).carousel('pause')
			//});  
                        
                        var posicion = $(".navidad").offset();
                        var margenSuperior = 15;
                         $(window).scroll(function() {
                             if ($(window).scrollTop() > posicion.top) {
                                 $(".navidad").stop().animate({
                                     marginTop: $(window).scrollTop() - posicion.top + margenSuperior
                                 });
                             } else {
                                 $(".navidad").stop().animate({
                                     marginTop: 0
                                 });
                             };
                         });
                        
            jQuery(function(){
		jQuery(window).scroll(function(){
			if (jQuery(this).scrollTop()> 200 ){
				jQuery("#IrArriba").fadeIn();
			}else {
				jQuery("#IrArriba").fadeOut();
			}
                        });
                });
                
        $("#IrArriba").click(function () {
		
		$.scrollTo('#redex',800);
		
		return false;
	});
                        
                        $("#rastreaEnvio").click(function(){
                            var data = {code : $("#codigoRastreo").val()};
                            var jsonData = JSON.stringify(data); 
                            $.ajax({
                            type: "POST",
                            data: jsonData,
                            dataType: "json",
                            contentType: "application/json; charset=utf-8",
                            url: "Pedido/VerificaRastreo",
                            //beforeSend: waitTable,        
                            success: function(data){
                            if (data.me =="") {
                                localStorage.setItem("idEnvio",data.id);
                                $(location).attr("href", "extranet/maparastreo");
                            }else {
                                 $("#mensajeRastreo").html(data.me);
                            }
                        }
                        });
                            
                         
                           return false;
                        });
                        
                         $("#Extranet").click(function(){
                                quitaActiveAllSetActive("#Extranet");
                                $("#carruselBienvenida").hide("slow");
                                $("#LoginExtranet").load("extranet", 
                                    function (responseText, textStatus, XMLHttpRequest) {
                                      if (textStatus == "success") {
                                           // alert("success");
                                           $("#LoginExtranet").show("slow");
                                      }
                                   
                                    });
                                return false;
                            });
                            
                        $("#redex").click(function(){
                            return false;
                        });
                        
                        $("#WebEmp").click(function(){
                            quitaActiveAllSetActive("#WebEmp");
                            $("#LoginExtranet").hide();
                            $("#carruselBienvenida").show("slow");
                            return false;
                        });  
                        
                        $("#contactoWeb").click(function(){
                           quitaActiveAllSetActive("#dropdownWebEmpresarial"); 
                           $("#carruselBienvenida").hide("slow");
                           $("#LoginExtranet").hide("slow");
                                $("#LoginExtranet").load("contacto", 
                                    function (responseText, textStatus, XMLHttpRequest) {
                                      if (textStatus == "success") {
                                           $("#LoginExtranet").show("slow");
                                      }
                                   
                                    });
                                return false;
                           return false;
                        });
			
                        function quitaActiveAllSetActive(activaClass){
                            $("li").removeClass("active");
                            //var cls = "#" + activaClass;
                            $(activaClass).addClass("active");
                        }
		}
               
		</script>
	</head>
	
	<body>
		
		
		<%@ include file="bodyHome.jsp" %>
		
		<script src = "/soft/resources/js/Home/validaciones.js"></script>
		
		<div id = "tituloHead" class="container-fluid">
			<div class="row-fluid">
				<div class = "span12">
				
			
                                    <div class="page-header">
                                       
			<fieldset style ="float: right">
                            
                            <input id ="codigoRastreo" type="text"  placeholder="Codigo de Rastreo">
                            <button id="rastreaEnvio" class="btn btn-success" style ="margin-bottom: 10px;" >Rastrea un Envío!</button>
                        <div id ="mensajeRastreo" class="text-error">  </div>  
                        </fieldset>  	
                                            <h1>Redex! <small>Líder en el mercado</small></h1>
                                            <img src="resources/png/can.png" class="navidad" />
					
                                        </div>
				
				</div>
			</div>
		</div>
		<div id ="LoginExtranet"></div>		
		<div id = "carruselBienvenida" class = "container-fluid">
			<div class = "row-fluid">
				<div class = "span8 offset2">
					<div id="myCarousel" class="carousel slide">
						<div class="carousel-inner">
							<div class="item">
								<img src="/soft/resources/jpeg/bootstrap-mdo-sfmoma-01.jpg" alt="">
								<div class="carousel-caption">
									<h4>Cuidado y seguimiento especial</h4>
									<p>Cuidamos cada uno de sus envÌos como si fuera nuestro. Cuidado especial para aquellos paquetes frágiles, como su corazón.</p>
								</div>
							</div>
							<div class="item active">
								<img src="/soft/resources/jpeg/bootstrap-mdo-sfmoma-02.jpg" alt="">
								<div class="carousel-caption">
									<h4>Rapidez garantizada</h4>
									<p>Nosotros no la hacemos larga, como...</p>
								</div>
							</div>
							<div class="item">
								<img src="/soft/resources/jpeg/bootstrap-mdo-sfmoma-03.jpg" alt="">
								<div class="carousel-caption">
									<h4>Seguridad</h4>
									<p>Usted está dejando su paquete en buenas manos. If you know what i mean.</p>
								</div>
							</div>
						</div>
						<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
						<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class = "form-actions">
		
		<div class = "container-fluid">
		<div class = "row-fluid">
		<span class="label label-inverse">Mantente informado</span>
		</div>
		</div>
		
		</br>
		
		
		
		<div class = "container-fluid">
		<div class = "row-fluid">
		
		
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">x</button>
				<strong><a href = "#">Haz click aquí!</a></strong> &nbsp&nbsp Y entra al sorteo de envíos gratis por un año!
			</div>
		
			<div class="alert alert-warning">
				<button type="button" class="close" data-dismiss="alert">x</button>
				<strong><a href = "#">Entérate!</a></strong> &nbsp&nbsp Envía un paquete! Y luego otro más!
			</div>
			
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">x</button>
				<strong><a href = "#">Cuidado!</a></strong> &nbsp&nbsp Realiza transacciones de forma segura!
			</div>
                    
                    <div class = "row-fluid">
                    <span class="label label-inverse">Nuestras Tarifas</span>
                    </div>
                    <br />
                    <table class="table table-hover">
                          <tr class="success">
                            <td>Continente</td>
                            <td>$5</td>
                            
                          </tr>
                          
                          <tr class="warning">
                            <td>Inter - Continente</td>
                            <td>$10</td>
                            
                          </tr>
                    </table>
                        
		</div>
		</div>
		</div>
		
            
                
	</body>
        
        
        <div id="IrArriba" style="display: block;">
            <a href="#Arriba"><span></span></a>
	</div>
        
        <div id="footer" align="center">
            <span> <a href ="http://squirteam.com">Copyright 2012 - PAQUE-TESIS </a></span>
	</div>
</html>