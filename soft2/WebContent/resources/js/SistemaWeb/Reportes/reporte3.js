google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);
function drawChart() {
	
	dibujar();
	
	$("#tipoAnho").change(function(){
		dibujar();
	});
	
    
}

function dibujar(){
var id = $("#tipoAnho").val();
	
	var jsonData = JSON.stringify(id);
	
	console.log(jsonData);
	
	$.ajax({
		type: "POST",
	    data: jsonData,
	    dataType: "json",
	    contentType: "application/json; charset=utf-8",
	    url: "/soft/reportes/reporteGeneralMes",
	    beforeSend: function(){
	    	$("#cargando").show("slow");
	    },        
	    success: function(data){
	     	console.log(data);
	     	$("#cargando").hide("slow");
	     	
	     	if(data.me == ""){
		       	var arreglo = new Array();
		       	
		       	arreglo[0] = ['Mes', 'Cantidad'];
		       	
		       	$.each(data.reporte,function(i,item){
		       		
		       		var mes;
		       		
		       		switch (item.mes){
		       			case 1:
		       				mes = "Enero";
		       				break;
		       				
		       			case 2:
		       				mes = "Febrero";
		       				break;
		       				
		       			case 3:
		       				mes = "Marzo";
		       				break;
		       				
		       			case 4:
		       				mes = "Abril";
		       				break;
		       				
		       			case 5:
		       				mes = "Mayo";
		       				break;
		       				
		       			case 6:
		       				mes = "Junio";
		       				break;
		       				
		       			case 7:
		       				mes = "Julio";
		       				break;
		       				
		       			case 8:
		       				mes = "Agosto";
		       				break;
		       				
		       			case 9:
		       				mes = "Setiembre";
		       				break;
		       				
		       			case 10:
		       				mes = "Octubre";
		       				break;
		       				
		       			case 11:
		       				mes = "Noviembre";
		       				break;
		       				
		       			case 12:
		       				mes = "Diciembre";
		       				break;
		      
		       		}
		       		
		       		
	        		arreglo[i+1] = [mes, item.cantidad];
	        	});
		        
		       	console.log(arreglo);
		       	
		      	var data = google.visualization.arrayToDataTable(arreglo);
		        	
		      	var options = {
		      	  	title: "Pedidos realizados en el " + $("#tipoAnho").val()
		      	};

		      	var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
		      	chart.draw(data, options);
	     	}
	     	else{
	     		alert(data.me);
	     	}
	    }
	});
}