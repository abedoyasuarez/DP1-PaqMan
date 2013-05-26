<!DOCTYPE html>
<html>
	<head>
		
		 <script type="text/javascript"
                    src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA___eyQMCgVAkwXoNOS8-sX7nznS3NJq0&sensor=true">
                </script>
               <script type="text/javascript" src="https://www.google.com/jsapi"></script>
                <style type="text/css">
 #map_canvas {
        margin: 0;
        padding: 0;
        height: 600px;
        width: 100%;
      }
      
      #infotabla {
          background-color: #eee;
          height: 550px;
          width: 400px;
      }    
      #caja_flotante{
        position: absolute;
        top:100px;
        left: 10px;
        border: 1px solid #CCC;
        background-color: #F05D38;
        font-size: 20px;
        padding-left: 10px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-right: 10px;
        z-index: 10000000;
        -moz-border-radius: 10px; /* Firefox*/ 
        -ms-border-radius: 10px; /* IE 8.*/ 
        -webkit-border-radius: 10px; /* Safari,Chrome.*/ 
        border-radius: 10px; /* El estándar.*/
    }
    
    #caja_flotante1{
        position: absolute;
        top:100px;
        left: 10px;
        border: 1px solid #CCC;
        background-color: #F05D38;
        font-size: 20px;
        padding-left: 10px;
        padding-bottom: 10px;
        padding-top: 10px;
        padding-right: 10px;
        z-index: 10000000;
        -moz-border-radius: 10px; /* Firefox*/ 
        -ms-border-radius: 10px; /* IE 8.*/ 
        -webkit-border-radius: 10px; /* Safari,Chrome.*/ 
        border-radius: 10px; /* El estándar.*/
    }
</style>
    
                <%@ include file="../headSistWeb.jsp" %>
                
		<script type ="text/javascript">
                    
                    function armaAlertaVuelo(mensaje){
                        
                    }
                    
                    function cargarAlmacenes(){
                                $.ajax({
                                type: "POST",
                                dataType: "json",
                                contentType: "application/json; charset=utf-8",
                                url: "../Almacen/listarAlmacenesActivos",
                                     
                                success: function(data){
                                        console.log(data);

                                        if (data.me == ""){

                                                var escritor = "";
                                                 $.each(data.listaAlmacenes, function (i, item) {
                                                        escritor += '<option value = "' + item.id + '">' + item.ciudad + '</option>';
                                                        
                                                });

                                                $("#ciudadI").html(escritor);
                                                $("#ciudadF").html(escritor);
                                                
                                                
                                        }
                                        else{
                                                
                                                $(location).attr('href', '/soft/Pedido/registrarPedido');
                                        }
                                }
                            });
                    }

                    function appendAlerta(divSection, mensaje){
                        var html = '<div class="alert alert-success">';
                        html += '<button type="button" class="close" data-dismiss="alert">x</button>';
                        html += mensaje;
                        html += '</div>';
                        $(divSection).append(html);
                    }
                    function armaAlerta(divSection,mensaje){
                        var html = '<div class="alert alert-success">';
                        html += '<button type="button" class="close" data-dismiss="alert">x</button>';
                        html += mensaje;
                        html += '</div>';
                        $(divSection).html(html);
                    }
                    
                    function stopstart(mensaje){
                        var html = '<div class="alert alert-success">';
                        html += '<button type="button" class="close" data-dismiss="alert">x</button>';
                        html += mensaje;
                        html += '</div>';
                        $("#logdetail").html(html);
                    }
                    
                    function dameColorGlobo(porcentajeLleno){
                        //var porcentaje = parseFloat(porcentajeLleno);
                            if ( porcentajeLleno == 0 ){
                                return "FFFFFF";
                            }else if ( porcentajeLleno > 0 && porcentajeLleno <= 10 ){
                                return "E4F4F3";
                            }else if ( porcentajeLleno > 10 && porcentajeLleno <= 20 ){
                                return "0ADCCE";
                            }else if ( porcentajeLleno > 20 && porcentajeLleno <= 30 ){
                                return "0ADC65";
                            }else if ( porcentajeLleno > 30 && porcentajeLleno <= 40 ){
                                return "65DC0A";
                            }else if ( porcentajeLleno > 40 && porcentajeLleno <= 50 ){
                                return "A4DC0A";
                            }else if ( porcentajeLleno > 50 && porcentajeLleno <= 60 ){
                                return "CEDC0A";
                            }else if ( porcentajeLleno > 60 && porcentajeLleno <= 70 ){
                                return "DCBD0A";
                            }else if ( porcentajeLleno > 70 && porcentajeLleno <= 80 ){
                                return "D7820C";
                            }else if ( porcentajeLleno > 80 && porcentajeLleno <= 90 ){
                                return "D7490C";
                            }else if ( porcentajeLleno > 90 && porcentajeLleno < 100 ){
                                //armaAlerta("sape");
                                return "FC0606";
                            }else if ( porcentajeLleno >= 100){
                                return 0;
                            }
                           return "FC0606";
                        }
                    $("#controles").hide();
                    var objListaBubu = [];
                    var almacenes = {};
                    var objListaCaidos = {};
                    var vuelos = 0;
                    var listaVuelos = [];
                    var listaVuelosCaidos = [];
                    $(document).ready(function(){
                        //cargarAlmacenes();
                        $("#addVuelos").click(function(){
                            if (vuelos < 3){
                            $("#vuelosForm").show("slow");
                            }
                            else{
                                $(".maximoVuelos").html("Solo se permite 3!");
                                console.log(listaVuelos);
                            }
                            return false;
                        });
                        
                        $("#terminarAdd").click(function(){
                            vuelos++;
                            var id1 = $("#ciudadI").val();
                            var id2 = $("#ciudadF").val();
                           var options = {
                                idPaisEntrada: id1,
                                idPaisSalida : id2
                            };
                            listaVuelos.push(options);
                            $(".vuelosAdd").html(vuelos +"  agregados");
                            $("#vuelosForm").hide("slow");
                        });
                        
                        
                        
                        var posicion = $("#caja_flotante").offset();
                        var margenSuperior = 15;
                         $(window).scroll(function() {
                             if ($(window).scrollTop() > posicion.top) {
                                 $("#caja_flotante").stop().animate({
                                     marginTop: $(window).scrollTop() - posicion.top + margenSuperior
                                 });
                             } else {
                                 $("#caja_flotante").stop().animate({
                                     marginTop: 0
                                 });
                             };
                         });
                        var timerId = 0;
                        localStorage.setItem("itemSimulacion",timerId);
                        armaAlerta("#logdetail","Pausado");
                        
                        $("#disminuirDias").click(function(){
                            if (!IsNumeric($("#diasmas").val())){
                                return false;
                            }
                        
                            var index = parseInt(localStorage.getItem("itemSimulacion"));
                            var result = index - parseInt($("#diasmas").val());
                            if ( result <= 0 ){
                                localStorage.setItem("itemSimulacion",0);
                            }else{
                                localStorage.setItem("itemSimulacion",result);
                            }
                            $("#log").html(localStorage.getItem("itemSimulacion"));
                            llenaAlmacenes(objListaBubu[parseInt(localStorage.getItem("itemSimulacion"))].almacenes);
                            prevInitialize();
                            initialize();
                        })
                        $("#aumentarDias").click(function(){
                            if (!IsNumeric($("#diasmas").val())){
                                return false;
                            }
                            var index = parseInt(localStorage.getItem("itemSimulacion"));
                         
                            var result = index + parseInt($("#diasmas").val());
                            if ( result >= objListaBubu.length ){
                                localStorage.setItem("itemSimulacion",objListaBubu.length-1);
                            }else{
                                localStorage.setItem("itemSimulacion",result);
                            }
                            $("#log").html(localStorage.getItem("itemSimulacion"));
                            llenaAlmacenes(objListaBubu[parseInt(localStorage.getItem("itemSimulacion"))].almacenes);
                            prevInitialize();
                            initialize();
                        });
                        
                        function IsNumeric(input)
                        {
                            return (input - 0) == input && input.length > 0;
                        }
                        
                        function verificaVuelos(dia){
                            //console.log(listaVuelosCaidos);
                            //console.log(dia);
                            var sizearr = 0;
                            if (listaVuelosCaidos)
                                 var sizearr = listaVuelosCaidos.length;
                            var j = 0;
                            var caido = false;
                            for (j = 0; j < listaVuelosCaidos.length; j++){
                                if (parseInt(listaVuelosCaidos[j].dia_caida) == dia){
                                   // alert(dia+" - " + listaVuelosCaidos[j].almacen_llegada);
                                   //armaAlerta("#logvuelos",'<span style="color:red">Vuelos: </span>' + '<span style="color:green"> Caído </span>')
                                   $("#estadovuelosValor").html("Problemas!!")
                                   appendAlerta("#logvuelos","Vuelo[" + listaVuelosCaidos[j].padre_id + "]" + listaVuelosCaidos[j].almacen_partida + "-"+ listaVuelosCaidos[j].almacen_llegada +' <span style ="color:red">colaps&oacute;!</span>' );
                                   caido = true;
                                }
                            }
                            if (caido)
                            $('#stop').trigger('click');
                            /*
                            var sizearr = 0;
                            if (listaVuelosCaidos)
                                 var sizearr = listaVuelosCaidos.length;
                            var j = 0;
                            for (j = 0; j < sizearr; i++){
                                if (parseInt(listaVuelosCaidos[j].dia_caida) == dia){
                                    alert(dia+" - " + listaVuelosCaidos[j].almacen_llegada);
                                }
                            }
                            
                            /*
                            for (j = 0; j < arrVuelos.length; j++){
                                var vuelo = arrVuelos[j];
                                //cantidadLleno: 68
                                //capacidad: 300
                                if (vuelo.cantidadLleno >= vuelo.capacidad){
                                    console.log("vuelo " + j);
                                }
                            }
                             */
                        }
                        
                        function llenaAlmacenes(arrAlmacenes){
                            //alert(arrAlmacenes.length);
                            var j = 0;
                            for (j = 0; j<arrAlmacenes.length; j++){
                                //almacenes[j] = arrAlmacenes[j]
                                if (arrAlmacenes[j].porcentajeLlenado == 100){
                                   $('#stop').trigger('click');
                                   localStorage.setItem("indiceColapsoAlmacenes",j);
                                   $("#estadoalmacenesValor").html("Problemas!!");
                                   appendAlerta("#logalmacenes","Almacen "+ arrAlmacenes[j].nombre +' <span style ="color:red">colaps&oacute;!</span>' );
                                   $("#performance").show();
                                }
                                
                                almacenes[j] = {
                                        center: new google.maps.LatLng(arrAlmacenes[j].latitud,arrAlmacenes[j].longitud),
                                        color : dameColorGlobo(arrAlmacenes[j].porcentajeLlenado),
                                        porcentajeLlenado : arrAlmacenes[j].porcentajeLlenado,
                                        mensaje: "Capacidad Actual: " + arrAlmacenes[j].cantidadLleno+ "/" + arrAlmacenes[j].capacidad  + " Porcentaje LLenado %" + arrAlmacenes[j].porcentajeLlenado,
                                        now : false
                                      };
                            }
                        }
                        $("#stop").click(function(){
                            clearInterval(timerId);
                            //alert("stopeo el : " + localStorage.getItem("itemSimulacion"));
                            armaAlerta("#logdetail","Pausado");
                        });
                        
                        $("#start").click(function(){
                           //prevInitialize();
                           var i = parseInt(localStorage.getItem("itemSimulacion"));
                           armaAlerta("#logdetail","Corriendo");
                           timerId = setInterval(function() {
                               if (i == objListaBubu.length) {
                                   clearInterval(timerId);
                                   return false;
                               }
                               //console.log(i);
                               verificaVuelos( i );
                               llenaAlmacenes(objListaBubu[i].almacenes);
                               
                               initialize();
                               $("#log").html(i);
                               i++;
                               localStorage.setItem("itemSimulacion",i);
                               
                          }, 2000);

                        });
                        
                        function formaArregloGrafico(itemCaido,itemSimu, tope){
                            var arreglo = new Array();
                            arreglo[0] = ['Dia', 'Lleno(%)'];
                            //alert(itemCaido + " " + itemSimu);
                            var cont = 1;
                            for (i = 0; i <= itemSimu; i++){
                                //recorre la simulacion 
                                var porcentaje  = parseInt((objListaBubu[i].almacenes)[itemCaido].porcentajeLlenado);
                                if (porcentaje >= tope){
                                  arreglo[cont] = [i, porcentaje];
                                  cont++;
                                }
                            }
                            
                            return arreglo;
                        }
                        
                        $("#terminarSimulacion").click(function(){
                            if (!IsNumeric($("#topePorcentaje").val())) return false;
                            var tope = parseInt($("#topePorcentaje").val());
                            var itemCaido = parseInt(localStorage.getItem("indiceColapsoAlmacenes"));
                            var itemSimu = parseInt(localStorage.getItem("itemSimulacion"));
                            var arr = formaArregloGrafico(itemCaido, itemSimu,tope);
                           $("#modalReporte").modal('toggle');
                           $("#loader").show("slow");
                           setTimeout(function(){
                               google.load('visualization', '1', 
                               {'callback': drawChart, 'packages':['corechart']})}, 2000);

                            function drawChart() {
                              var data = google.visualization.arrayToDataTable(arr);

                              var options = {
                                title: 'Performance: Almacen ' + (objListaBubu[0].almacenes)[itemCaido].nombre,
                                hAxis: {title: 'Día', titleTextStyle: {color: 'red'}},
                                vAxis: {title: 'Porcentaje', titleTextStyle: {color: 'red'}}
                              };

                              var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
                              chart.draw(data, options);
                            }
                            $("#loader").hide("slow");
                            return false;
                        });
                        
                        $("#simularPrev").click(function(){
                            $("#simularSig").show();
                            var iactual = localStorage.getItem("actualAlmacenIndice");
                            var intactual = parseInt(iactual);
                            var intMostrar = intactual - 1;
                            if (intactual == 1){
                                //si es 1, se muestra el actual
                                localStorage.setItem("actualAlmacenIndice", intMostrar);
                                //pintamos
                                llenaAlmacenes(objListaBubu[intMostrar].almacenes);
                                $("#simularPrev").hide("slow");
                            }
                            if (intactual > 1){
                                localStorage.setItem("actualAlmacenIndice", intactual-1);
                                //pintamos
                                 llenaAlmacenes(objListaBubu[intMostrar].almacenes);
                              }
                              $("#diaSimulado").html(objListaBubu[intMostrar].fechaCad);
                                initialize();
                              return false;
                        });
                        
                        $("#simularSig").click(function(){
                            $("#simularPrev").show();
                           var iactual = localStorage.getItem("actualAlmacenIndice");
                           var intactual = parseInt(iactual);
                           var intMostrar = intactual + 1; 
                            if (intactual < objListaBubu.length){
                                localStorage.setItem("actualAlmacenIndice", intMostrar);
                                //pintamos
                                llenaAlmacenes(objListaBubu[intMostrar].almacenes);
                              }
                            if (intMostrar + 1 == objListaBubu.length){
                                  localStorage.setItem("actualAlmacenIndice", intMostrar);
                                  $("#simularSig").hide("slow");
                                  //pintamos
                                  llenaAlmacenes(objListaBubu[intMostrar].almacenes);
                            }
                           /*
                             if (intactual < objListaBubu.length){
                                localStorage.setItem("actualAlmacenIndice", intactual+1);
                              }
                              if (intactual + 1 == objListaBubu.length){
                                  $("#simularSig").hide("slow");
                              }
                         */
                            $("#diaSimulado").html(objListaBubu[intMostrar].fechaCad);
                            initialize();
                              return false;
                        });
                        
                        
                        function levantaALosCaidos(arr){
                            if (arr){
                                for (i = 0; i < arr.length; i++){
                                    //console.log(arr[i].nombre_almacen + " " + arr[i].dia_caida);
                                    
                                }
                            }
                        }
                        
                        $("#fechaSimulacion" ).datepicker({ dateFormat: 'yy-mm-dd' });
                            $("#btnSimulacion").click(function(){
                               $("#errorSimulacion").hide();
                               //$("#map_canvas").hide("slow");
                               var ff =  $("#fechaSimulacion1").val();
                               if (ff == "") {
                                   $("#errorSimulacion").html("Ingresa una fecha válida");
                                   $("#errorSimulacion").show();
                                   
                                }
                               else {
                                  $("#progSimulacion").show("slow");

                                  var data = {ff : ff, listaAlmacenes : listaVuelos };
                                   var jsonData = JSON.stringify(data); 
                                        $.ajax({
                                        type: "POST",
                                        data: jsonData,
                                        dataType: "json",
                                        contentType: "application/json; charset=utf-8",
                                        url: "../Simulacion/hardcode2",
                                        //beforeSend: waitTable,        
                                        success: function(data){
                                            objListaCaidos = data.listaCaidos;
                                            //levantaALosCaidos(objListaCaidos);
                                            $("#progSimulacion").hide("slow");
                                            //$("#mapa").load("mapa");
                                            objListaBubu = data.listaSim;
                                            console.log(data);
                                            //pintamos el primero Fecha actual
                                            if (objListaBubu.length > 0){
	                                            var actual = objListaBubu[0];
	                                            //console.log(actual);
	                                            var i = 0;
	                                            var arrAlmacenes = actual.almacenes;
	                                            var arrVuelos = actual.vuelos;
	                                            listaVuelosCaidos = data.listaCaidosVuelos;
	                                            //console.log(arrAlmacenes);
	                                            llenaAlmacenes(arrAlmacenes);
	                                            //console.log(almacenes);
	                                            localStorage.setItem("actualAlmacenIndice",0);
                                            
                                            }
                                            $("#controles").show("slow");
                                            $("#tableroInfo").show("slow");
                                            $("#simularPrev").hide();
                                            $("#diaSimulado").html(objListaBubu[0].fechaCad);
                                            $("#map_canvas").show();
                                            prevInitialize();
                                            initialize();
                                            $("#caja_flotante").show("slow");
                                            
                                        }
                                    }); 
                               }
                            });
                           return false;
                        });
                   
                   function toggleBounce() {

                             if (marker.getAnimation() != null) {
                               marker.setAnimation(null);
                             } else {
                               marker.setAnimation(google.maps.Animation.BOUNCE);
                             }

                     }

                     function attachSecretMessage(marker, mensaje) {
                       
                        var infowindow = new google.maps.InfoWindow({
                          content: mensaje
                        });
                        
                        google.maps.event.addListener(marker, 'click', function() {
                          infowindow.open(marker.get('map'), marker);
                          //alert(mensaje);
                          
                        });
                      }
 
 
 
 
                      //iniiiasdcializa
                      
                      function prevInitialize(){
                      var mapOptions = {
                        zoom: 2,
                        center: new google.maps.LatLng(52.48,13.46),
                        mapTypeId: google.maps.MapTypeId.ROADMAP
                      };
                        map = new google.maps.Map(document.getElementById("map_canvas"),
                        mapOptions);
                      }
                      
                    function initialize(){
                        
                        var mapOptions = {
                            zoom: 2,
                         center: new google.maps.LatLng(52.48,13.46),
                         mapTypeId: google.maps.MapTypeId.ROADMAP
                        };
                         map = new google.maps.Map(document.getElementById("map_canvas"),
                        mapOptions);
                    
                       
                      
                         for (var city in almacenes) {
                            //console.log(city);
                            var porcenta = parseInt(almacenes[city].porcentajeLlenado);  

                            var pinColor =dameColorGlobo(porcenta);// dameColorGlobo(almacenes[city].porcentajeLleno);
                            //console.log(porcenta + " color " +pinColor);
                            var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
                            new google.maps.Size(21, 34),
                            new google.maps.Point(0,0),
                            new google.maps.Point(10, 34));
                            var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
                            new google.maps.Size(40, 37),
                            new google.maps.Point(0, 0),
                            new google.maps.Point(12, 35));

                             var marker = new google.maps.Marker({
                                position: almacenes[city].center,
                                map: map,
                                icon: pinImage,
                                shadow: pinShadow
                              });

                             attachSecretMessage(marker, almacenes[city].mensaje);
                             if (almacenes[city].now)
                              marker.setAnimation(google.maps.Animation.BOUNCE);

                          }
                    }
                    
                    
                      
                </script>
		
		
	</head>
	
	<body>
			
		<%@ include file="../bodySistWeb.jsp" %>
		
		<div class="container-fluid">
			<div class="row-fluid">
    			<div class="hero-unit">
					<h1>Simulación</h1>
					<p>Estime los almacenes a través del tiempo</p>
					<p>
						<a id = "btnSimulacion" href="" role="button" class="btn btn-primary btn-large" data-toggle="modal">Empezar Simulación</a>
                                                <input type="text" id="fechaSimulacion1" />
                                                <div id ="errorSimulacion" class="alert alert-error" style="display:none">
                                                
                                                </div>
                                        </p>
                                        <p> <a id ="addVuelos" href ="#">Agregar vuelos! </a> <span style ="color: red" class="vuelosAdd">Todos agregados</span> <span class ="maximoVuelos" style="color:orange;"> </span></p> 
                                        
                                        <div id ="vuelosForm" style ="display:none">
                                        <div class="control-group">
  						<label class="control-label">Ciudad de partida</label>	
    					<div class="controls">
      						<select id = "ciudadI">
  								
                                                </select>
    					</div>
  					</div>
  					
  					<div class="control-group">
  						<label class="control-label">Ciudad de destino</label>	
    					<div class="controls">
      						<select id = "ciudadF">
  								
							</select>
    					</div>
  					</div>
                                              <button type="button" id ="terminarAdd" class="btn btn-success">Agregar</button>
  					</div>
                                        
                                         <div id ="progSimulacion" class="progress progress-striped active" style="width: 400px; display:none">
                                            <div class="bar" style="width: 100%;">..Cargando..</div>
                                          </div>
                                        <!--
                                        <div id ="controles" style="float:right; display:none;">
                                             <img id ="simularPrev" src ="../resources/png/boton_prev.png">

                                            <img id ="stop" src ="../resources/png/glyphicons_174_pause.png">
                                            <img id ="start" src ="../resources/png/glyphicons_173_play.png">
                                        </div>  
                                        -->
                                       
                                        <div id ="glosario" style ="float:right">
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|FFFFFF" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|E4F4F3" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|0ADCCE" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|0ADC65" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|65DC0A" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|A4DC0A" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|CEDC0A" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|DCBD0A" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|D7820C" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|D7490C" alt ="Vacio"/>
                                            <img src ="http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|FC0606" alt ="Vacio"/>
                                        </div>
				</div>
                        </div>
                    <div id="caja_flotante" style ="display:none">
                        <div id="cont_caja_flotante">
                            <div id ="controles" style="float:left; display:none;">
                                            <img style ="cursor: pointer" id ="stop" src ="../resources/png/glyphicons_174_pause.png">
                                            <img style ="cursor: pointer" id ="start" src ="../resources/png/glyphicons_173_play.png">
                                            Día: <span id ="log">0</span>
                                            
                                            <img style ="cursor: pointer" id ="aumentarDias" src ="../resources/png/bullet_add.png" />
                                            <input type ="text"  style="width:30px;" id ="diasmas" placeholder="0"/>
                                            <img style ="cursor: pointer" id ="disminuirDias" src ="../resources/png/minus.png" />
                                            
                                            <section id = "logdetail"></section>
                                            
                                            <section id = "estadoalmacenes">
                                                <div class="alert alert-success">
                                                    <button type="button" class="close" data-dismiss="alert">x</button>
                                                    <span style="color:red">Almacenes </span>
                                                    <span style="color:green" id ="estadoalmacenesValor"> Ok </span>
                                                </div>
                                                
                                            </section>
                                            
                                            <section id = "logalmacenes"> </section>
                                            
                                            <div id ="performance" style ="display:none">
                                            <button type="button" id ="terminarSimulacion" class="btn btn-success">Performance</button>
                                            <input type ="text"  style="width:40px;" id="topePorcentaje" placeholder="tope%" />
                                            <br />
                                            
                                            </div>
                                            <section id = "estadovuelos">
                                                <div class="alert alert-success">
                                                    <button type="button" class="close" data-dismiss="alert">x</button>
                                                    <span style="color:red">Vuelos: </span>
                                                    <span style="color:green" id ="estadovuelosValor"> Ok </span>
                                                </div>
                                            </section>
                                            
                                            <section id = "logvuelos"> </section>
                                            
                            </div>  
                        </div>
                 </div>
                    
                    
                    <div id="caja_flotante1" style ="display:none">
                        <div id="cont_caja_flotante1">
                            aaaa
                        </div>
                 </div>
                    <!--
                    <section id="tableroInfo" style="position:absolute; top:400px; width: 200px; display:none;"> 
                        <div id ="infotabla" class="container-fluid">
                            <h2>Información - Almacenes</h2>
                            <h3> Dia: <span id ="diaSimulado"></span></h3>
                        </div>
                    </section>
                    -->
                    <section id ="map_canvas" style="display:none">
                    </section>
                    <!--
                    <div class="container-fluid" style="background-color: #eee; height: 200px;">
                        <div class="row-fluid">
                          <div class="span2">
                          
                          </div>
                          
                         <div id ="mapa" class="span10 " style="background-color: #ccc; height: 200px;">
                            
                          </div>
                        </div>
                      </div>
                   -->
                   
                   

                   <div id="modalReporte" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style=" display: none; ">
        	
                        <div class="modal-body">

                            <img id ="loader" src="../resources/gif/loading3.gif" style="display:none" />
                                <div id="chart_div" style="width: 500px; height: 350px;"></div>
                          
                        </div>

                      </div>
                </div>
    	
	</body>
	
</html>