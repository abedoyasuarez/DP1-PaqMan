
var enviado = 0;


$(document).ready(main);

function main(){
	
	$("#myModal").modal('hide');
	$("#inputId").hide();
	$("#inputRol").hide();
	$("#tableFilter").hide();
	
	var data = {
		id: -1	
	};
	
	var jsonData = JSON.stringify(data);
	
	console.log(jsonData);
	$.ajax({
        type: "POST",
        data: jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "buscarUsuario",
        beforeSend: function(){
        	
        },        
        success: imprimirTabla
    });
}

function imprimirTabla(data){
	cargarRol();
	if(data.me != ""){
		alert("Hubo un error en la Base de Datos, se proceder‡ a recargar la p‡gina");
		$(location).attr('href', '/soft/Usuario/modificarUsuario');
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
        escritor += '<button id = "' + item.id +'" type="button" class="btn btn-inverse">Modificar</button></td>';
        
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
			
			var enviar = {
				id: elemento,
				nombre: "a",
				apellidos: "b",
				sexo: 1,
				estado: 1,
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
		        url: "buscarUsuario",
		        beforeSend: waitUser,        
		        success: imprimirUser
		    });
			
		});
	});
	
}

function waitUser(){
	$("#cargando").show("slow");
}

function imprimirUser(data){
	
	
	console.log(data);
	
	$("#cargando").hide("slow");
	
	var datos = data.listaUser[0];
	//Asigno los valores a mi formulario
	$("#tipoDoc option[value=" + datos.documento_id + "]").attr("selected", true);
    $("#tipoDoc").trigger('change');
	
    $("#inputNro").attr("value",datos.numero_documento);
    
    $("#tipoRol option[value=" + datos.rol_id + "]").attr("selected", true);
    $("#tipoRol").trigger('change');
    
    $("#inputNombre").attr("value",datos.nombre);
    
    $("#inputApell").attr("value",datos.apellidos);
    
    $("#tipoSexo option[value=" + datos.sexo + "]").attr("selected", true);
    $("#tipoSexo").trigger('change');
    
    $("#inputEmail").attr("value",datos.email);
    
    $("#inputCont").attr("value",datos.password);
    
    $("#inputCont2").attr("value",datos.password);
    
    $("#inputTelef").attr("value",datos.telefono);
    
    $("#inputDireccion").attr("value",datos.direccion);
    
    $("#tipoPais option[value=" + datos.pais_id + "]").attr("selected", true);
    $("#tipoPais").trigger('change');
    
    $("#inputId").attr("value",datos.id);
    
    $("#inputRol").attr("value",datos.rol_id);
    
	$("#myModal").modal('toggle');
	
	$("#realizarRegistro").click(actualizarDatos);
}

function actualizarDatos(){
	
	if (enviado != 1){
		enviado = 1;
	
		var datosCliente = {
			id: $("#inputId").attr("value"),
			nombre: $("#inputNombre").attr("value"),
			apellidos: $("#inputApell").attr("value"),
			sexo: $("#inputSexo").val(),
			estado: 1,
			user:$("#inputNro").attr("value"),
			email: $("#inputEmail").attr("value"),
			password: $("#inputCont").attr("value"),
			telefono: $("#inputTelef").attr("value"),
			direccion: $("#inputDireccion").attr("value"),
			rol_id : $("#tipoRol").val(),
			pais_id : $("#tipoPais").val(),
			documento_id: $("#tipoDoc").val(),
			numero_documento: $("#inputNro").attr("value")
		};
		
		var jsonData = JSON.stringify(datosCliente);
		
		console.log(jsonData);
		
		$.ajax({
	        type: "POST",
	        data: jsonData,
	        dataType: "json",
	        contentType: "application/json; charset=utf-8",
	        url: "modificarUsuario",
	        beforeSend: waitActualizacion,        
	        success: successActualizacion
	    });
	}
}

function waitActualizacion(){
	
}

function successActualizacion(data){
	if (data.me == ""){
		$(location).attr('href', '/soft/Usuario/modificarUsuario');
	}
	else{
		alert(data.me);
	}
	
}

function cargarPaises(){
	var enviar = -1;
	
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
        		alert("Hubo un error en la Base de Datos, se proceder‡ a recargar la p‡gina");
        		$(location).attr('href', '/soft/Almacen/registrarAlmacen');
        	}
        	
        }
    });
}

function cargarRol(){
	$.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Usuario/roles",
        beforeSend: function(){
        	
        },        
        success: function(data){
        	console.log(data);
        	if (data.me == ""){
        		
        		var escritor = "";
        		
        		$.each(data.lRol, function (i, item) {
        			escritor += '<option value = "' + item.rol_id + '">' + item.rol_nombre + '</option>';
        		});
        		
        		$("#tipoRol").html(escritor);
        		cargarPaises();
        	}
        	else{
        		alert("Hubo un error en la Base de Datos, se proceder‡ a recargar la p‡gina");
        		$(location).attr('href', '/soft/Usuario/registrarUsuario');
        	}
        	
        }
    });
}

