
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
        url: "listarAlmacenes",
        beforeSend: function(){
        	
        },        
        success: imprimirTabla
    });
}

function imprimirTabla(data){
	
	var arregloId = new Array();
	var escritor = "";
	
	$.each(data.listaAlmacenes, function (i, item) {
		
		var n = i + 1;
		
        escritor += "<tr>";
        
        escritor += '<td>'+ n + '</td>';
        escritor += '<td>' + item.ciudad + '</td>';
        escritor += '<td>' + item.continente_nombre + '</td>';
        escritor += '<td>' + item.pais_nombre + '</td>';
        escritor += '<td>' + item.capacidad + '</td>';
        escritor += '<td>';
        escritor += '<button id = "' + item.id +'" type="button" class="btn btn-danger">Eliminar</button></td>';
        
        escritor += "</tr>";
        
        
        arregloId[i] = item.id;
    });
	
	$("#cuerpoTabla").html(escritor);
	
	$("table#tableFilter").columnFilters({excludeColumns:[0,4,5]});
	$("#_filterText1").attr("placeholder","Buscar Ciudad...");
	$("#_filterText2").attr("placeholder","Buscar Continente...");
	$("#_filterText3").attr("placeholder","Buscar Pa’s...");
	
	$("#tableFilter").show("slow");
	
	console.log(arregloId);
	
	arregloId.forEach(function (elemento) {
		$("#" + elemento).click(function (event) {
			
			$("#myModal").modal('toggle');
			
			$("#inputId").attr("value",elemento);
			
			$("#realizarRegistro").click(function (){
				
				if (elemento == $("#inputId").attr("value")){
				
					var enviar = {
						id: elemento
					};
						
					var jsonData = JSON.stringify(enviar);
						
					console.log(jsonData);
				
					$.ajax({
				        type: "POST",
				        data: jsonData,
				        dataType: "json",
				        contentType: "application/json; charset=utf-8",
				        url: "eliminarAlmacen",
				        beforeSend: function(){
				        	        
				        },
				        success: function(data){
				        	if (data.me == ""){
				        		$(location).attr('href', '/soft/Almacen/eliminarAlmacen');
				        	}
				        	else{
				        		alert(data.me);
				        	}
				        }
				    });
				}
			});
		});
	});
}