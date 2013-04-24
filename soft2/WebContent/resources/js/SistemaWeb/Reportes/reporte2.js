google.load('visualization', '1', {'packages': ['geochart']});
google.setOnLoadCallback(drawMarkersMap);

function drawMarkersMap() {
	
	cargarPaises();
	
	$("#tipoPais").change(function(){
		if($("#tipoPais").val() != ""){
			dibujar();
		}
	});
}

//LLAMAR AL SERVICIO Y DIBJAR EL MAPA RESPECTIVO
function dibujar(){
	var id = $("#tipoPais").val();
		
	var jsonData = JSON.stringify(id);
		
	console.log(jsonData);
		
	$.ajax({
		type: "POST",
	    data: jsonData,
	    dataType: "json",
	    contentType: "application/json; charset=utf-8",
	    url: "/soft/reportes/reporteGeneralPais",
	    beforeSend: function(){
	    	$("#cargando").show("slow");
	    },        
	    success: function(data){
	     	console.log(data);
	     	$("#cargando").hide("slow");
	     	
	     	if(data.me == ""){
		       	var arreglo = new Array();
		       	
		       	var paiscode;
		       	
		       	arreglo[0] = ['Ciudad',   'Cantidad de Paquetes', 'Capacidad'];
		       	
		       	$.each(data.reporte,function(i,item){
		       		paiscode = item.code;
	        		arreglo[i+1] = [item.ciudad, item.cantidad, item.capacidad];
	        	});
		             	
		      	var data = google.visualization.arrayToDataTable(arreglo);
		        	
		      	console.log(arreglo);
		      	
		       	var options = {
		       		region: paiscode,
		        	displayMode: 'markers',
		        	title: "Paquetes recibidos en el d’a de hoy por ciudad" ,
		        	colorAxis: {colors: ['green', 'blue']}
		        };
	
		        var chart = new google.visualization.GeoChart(document.getElementById('chart_div'));
		        chart.draw(data, options);
	     	}
	     	else{
	     		alert(data.me);
	     	}
	    }
	});
}

//CARGAR PAISES
function cargarPaises(){
	var enviar = -1;
	
	var jsonData = JSON.stringify(enviar);
	
	console.log(jsonData);
	
	$.ajax({
        type: "POST",
        data:jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "/soft/Almacen/ListarPaises",
        beforeSend: function(){
        	$("#cargando").show("slow");
        },        
        success: function(data){
        	console.log(data);
        	
        	$("#cargando").hide("slow");
        	if (data.me == ""){
        		
        		var escritor = "";
        		
        		primerPais = data.listaPais[0].id;
        		
        		$.each(data.listaPais, function (i, item) {
        			escritor += '<option value = "' + item.id + '">' + item.nombre + '</option>';
        		});
        		
        		$("#tipoPais").html(escritor);
        		
        		console.log($("#tipoPais").val());
        		
        		if($("#tipoPais").val() != ""){
					dibujar();
				}
        		
        	}
        	else{
        		alert("Hubo un error en la Base de Datos, se procederï¿½ a recargar la pï¿½gina");
        		$(location).attr('href', '/soft/reporte2');
        	}
        	
        }
    });
}