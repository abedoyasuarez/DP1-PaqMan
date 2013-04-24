
var enviado = 0;

$(document).ready(main);

function main(){
	
	var data = {
			id:-1
	};
	
	var jsonData = JSON.stringify(data);
	
	console.log(jsonData);
	$.ajax({
        type: "POST",
        data: jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "buscarIncidencia",
        beforeSend: function(){
        	
        },        
        success: imprimirTabla
    });
	
}

function imprimirTabla(data){
	
	console.log(data);
	
	if (data.me != ""){
		alert("Hubo un error en la Base de Datos, se proceder‡ a recargar la p‡gina");
		$(location).attr('href', '/soft/Incidencia/modificarIncidencia');
	}
	
	var arregloId = new Array();
	var escritor = "";
	
	$.each(data.ListaIncidencia, function (i, item) {
		
		var n = i + 1;
		
        escritor += "<tr>";
        
        escritor += '<td>'+ n + '</td>';
        escritor += '<td>' + item.incidencia_fecha_registro + '</td>';
        escritor += '<td>' + item.incidencia_descripcion + '</td>';
        escritor += '<td>';
        escritor += '<button id = "' + item.incidencia_id +'" type="button" class="btn btn-inverse">Modificar</button></td>';
        
        escritor += "</tr>";
        
        
        arregloId[i] = item.incidencia_id;
    });
	
	$("#cuerpoTabla").html(escritor);
	
	$("table#tableFilter").columnFilters({excludeColumns:[0,3]});
	$("#_filterText2").attr("placeholder","Buscar Descripci—n...");
	$("#_filterText1").attr("placeholder","Buscar Fecha de registro...");
	
	$("#tableFilter").show("slow");
	
	console.log(arregloId);
	
	arregloId.forEach(function (elemento) {
		$("#" + elemento).click(function (event) {	
		
			var enviar = {
				id:elemento
			};
				
			var jsonData = JSON.stringify(enviar);
			
			console.log(jsonData);
			
			$.ajax({
		        type: "POST",
		        data: jsonData,
		        dataType: "json",
		        contentType: "application/json; charset=utf-8",
		        url: "buscarIncidencia",
		        beforeSend: function(){
		        	
		        },        
		        success: imprimirIncidencia
		    });
			
		});
	});	
}


function imprimirIncidencia(data){
	
	console.log(data);
	
	var dato = data.ListaIncidencia[0];
	
	$("#inputId").attr("value",dato.incidencia_id);
	
	$("#reclamo").attr("value",dato.incidencia_descripcion);
	
	$("#myModal").modal('toggle');
	
	$("#guardarCambios").click(guardarModif);
}

function guardarModif(){
	
	if (enviado != 1){
		enviado = 1;
		
		var enviar = {
			incidencia_id: $("#inputId").attr("value"),
			incidencia_descripcion: $("#reclamo").attr("value")
		};
		
		var jsonData = JSON.stringify(enviar);
		
		console.log(jsonData);
		
		$.ajax({
	        type: "POST",
	        data: jsonData,
	        dataType: "json",
	        contentType: "application/json; charset=utf-8",
	        url: "modificarIncidencia",
	        beforeSend: function(){
	        	
	        },        
	        success: function(data){
	        	console.log(data);
	        	
	        	if (data.me == ""){
	        		$(location).attr('href', '/soft/Incidencia/modificarIncidencia');
	        	}
	        	else{
	        		alert(data.me);
	        	}
	        }
	    });
	}
}


