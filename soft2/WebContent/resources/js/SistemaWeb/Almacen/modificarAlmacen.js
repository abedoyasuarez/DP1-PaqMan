
var enviado = 0;

$(document).ready(main);

function main(){
	
	var data = {
		id :-1
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
	
	console.log(data);
	
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
        escritor += '<button id = "' + item.id +'" type="button" class="btn btn-inverse">Modificar</button></td>';
        
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
		        url: "listarAlmacenes",
		        beforeSend: function(){
		        	
		        },        
		        success: imprimirAlmacen
		    });
		});
	});
	
	cargarContinentes();
	
}


function imprimirAlmacen(data){
	
	console.log(data);
	
	var datos = data.listaAlmacenes[0];
	
	$("#inputId").attr("value",datos.id);
	
	$("#tipoCont option[value=" + datos.continente_id + "]").attr("selected", true);
    $("#tipoCont").trigger('change');
    
    $("#tipoPais option[value=" + datos.pais_id + "]").attr("selected", true);
    $("#tipoPais").trigger('change');
    
    $("#inputCiudad").attr("value",datos.ciudad);
    
    $("#inputLat").attr("value",datos.latitud);
    
    $("#inputLong").attr("value",datos.longitud);
    
    $("#inputCap").attr("value",datos.capacidad);
	
    $("#myModal").modal('toggle');
    
	$("#guardarCambios").click(guardarModif);
    
}

function guardarModif(){
	
	if (enviado != 1){
		enviado = 1;
	
		var datos = {
			id: $("#inputId").attr("value"),
			ciudad: $("#inputCiudad").attr("value"),
			capacidad: $("#inputCap").attr("value"),
			latitud: $("#inputLat").attr("value"),
			longitud: $("#inputLong").attr("value"),
			pais_id: $("#tipoPais").val(),
			continente_id: $("#tipoCont").val()
		};
		
		var jsonData = JSON.stringify(datos);
		
		console.log(jsonData);
		
		$.ajax({
	        type: "POST",
	        data: jsonData,
	        dataType: "json",
	        contentType: "application/json; charset=utf-8",
	        url: "modificarAlmacen",
	        beforeSend: function(){
	        	
	        },        
	        success: function(data){
	        	console.log(data);
	        	
	        	if (data.me == ""){
	        		$(location).attr('href', '/soft/Almacen/modificarAlmacen');
	        	}
	        	else{
	        		alert(data.me);
	        	}
	        }
	    });
	}
}




//CARGAR DE CONTINENTES Y PAISES


function cargarPaises(){
	var enviar = $("#tipoCont").val();
	
	var jsonData = JSON.stringify(enviar);
	
	console.log(jsonData);
	
	$.ajax({
        type: "POST",
        data:jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Almacen/ListarPaises",
        beforeSend: function(){
        	
        },        
        success: function(data){
        	console.log(data);
        	if (data.me == ""){
        		
        		var escritor = "";
        		
        		$.each(data.listaPais, function (i, item) {
        			escritor += '<option value = "' + item.id + '">' + item.nombre + '</option>';
        		});
        		
        		$("#tipoPais").html(escritor);
        	}
        	else{
        		alert(data.me);
        	}
        	
        }
    });
}

function cargarContinentes(){
	$.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Almacen/ListarContinentes",
        beforeSend: function(){
        	
        },        
        success: function(data){
        	console.log(data);
        	if (data.me == ""){
        		
        		var escritor = "";
        		
        		$.each(data.listaContinente, function (i, item) {
        			escritor += '<option value = "' + item.id + '">' + item.nombre + '</option>';
        		});
        		
        		$("#tipoCont").html(escritor);
        		cargarPaises();
        	}
        	else{
        		alert(data.me);
        	}
        	
        }
    });
}







