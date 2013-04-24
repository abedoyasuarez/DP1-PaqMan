
$(document).ready(main);

function main(){
	$("#myModal").modal('hide');
	$("#inputId").hide();
	$("#tableFilter").hide();
	
	var data = {
		id: -1,
		nombre: "a",
		apellidos: "b",
		sexo: 1,
		estado: 1,
		email: "a",
		password: "a",
		telefono: "1",
		direccion: "A",
		rol_id: 3,
		pais_id: 4,
		documento_id: 1,
		numero_documento: "1"		
	};
	
	var jsonData = JSON.stringify(data);
	
	
	$.ajax({
        type: "POST",
        data: jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "buscarUsuario",
        beforeSend: waitTable,        
        success: imprimirTabla
    });
}

function waitTable(){
	
}

function imprimirTabla(data){
	
	if(data.me != ""){
		alert("Hubo un error en la Base de Datos, se proceder‡ a recargar la p‡gina");
		$(location).attr('href', '/soft/Usuario/eliminarUsuario');
	}
	
	var arregloId = new Array();
	var escritor = "";
	
	
	$.each(data.listaUser, function (i, item) {
		
		var n = i + 1;
		
        escritor += "<tr>";
        
        escritor += '<td>'+ n + '</td>';
        escritor += '<td>' + item.nombre + '</td>';
        escritor += '<td>' + item.apellidos + '</td>';
        escritor += '<td>' + item.email + '</td>';
        escritor += '<td>';
        escritor += '<button id = "' + item.id +'" type="button" class="btn btn-danger">Eliminar</button></td>';
        
        escritor += "</tr>";
        
        arregloId[i] = item.id;
    });
	
	$("#cuerpoTabla").html(escritor);
	
	$("table#tableFilter").columnFilters({excludeColumns:[0,4]});
	$("#_filterText1").attr("placeholder","Buscar Nombre...");
	$("#_filterText2").attr("placeholder","Buscar Apellido...");
	$("#_filterText3").attr("placeholder","Buscar Email...");
	
	$("#tableFilter").show("slow");
	
	console.log(arregloId);
	
	arregloId.forEach(function (elemento) {
		$("#" + elemento).click(function (event) {
			
			$("#myModal").modal('toggle');
			
			$("#inputId").attr("value",elemento);
			
			$("#realizarRegistro").click(function (){
				
				if (elemento == $("#inputId").attr("value")){
				
					var enviar = {
						id: elemento,
						nombre: "a",
						apellidos: "b",
						sexo: 0,
						estado: 0,
						email: "a",
						password: "a",
						telefono: 1,
						direccion: "A",
						rol_id: 3,
						pais_id: 4,
						documento_id: 1,
						numero_documento: 1,
					};
					
					var jsonData = JSON.stringify(enviar);
					
					console.log(jsonData);
				
					$.ajax({
				        type: "POST",
				        data: jsonData,
				        dataType: "json",
				        contentType: "application/json; charset=utf-8",
				        url: "eliminarUsuario",
				        beforeSend: waitConfirmacion,        
				        success: imprimirConfirmacion
				    });
				}
			});
			
		});
	});
	
}

function waitConfirmacion(){
	
}

function imprimirConfirmacion(data){
	if (data.me == ""){
		$(location).attr('href', '/soft/Usuario/eliminarUsuario');
	}
	else{
		alert(data.me);
	}
}


