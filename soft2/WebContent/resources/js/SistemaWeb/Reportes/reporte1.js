google.load('visualization', '1', {'packages': ['geochart']});
google.setOnLoadCallback(drawRegionsMap);

function drawRegionsMap() {
		
	var dato = {
		id:-1	
	};
	
	var jsonData = JSON.stringify(dato);
	
	console.log(jsonData);
	
	$.ajax({
        type: "POST",
        data: jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "/soft/reportes/reporteGeneral",
        beforeSend: function(){
        	$("#cargando").show("slow");
        },        
        success: function(data){
        	console.log(data);
        	$("#cargando").hide("slow");
        	if(data.me == ""){
	        	var arreglo = new Array();
	        	arreglo[0] = ['Pais', 'Cantidad de Paquetes'];
	        	
	        	$.each(data.reporte,function(i,item){
	        		arreglo[i+1] = [item.nombre, item.cantidad];
	        	});
	        	
	        	console.log(arreglo);
	        	var data = google.visualization.arrayToDataTable(arreglo);
	        	var options = {
	        		title: "Paquetes recibidos en el d’a de hoy por pais" ,
	        		colorAxis: {colors: ['pink' ,'red']}
	        	};
	            var chart = new google.visualization.GeoChart(document.getElementById('chart_div'));
	            chart.draw(data, options);
        	}
        	else{
        		alert(data.me);
        	}
        }
    });	
 };