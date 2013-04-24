
var primerRol;
var primerPais;

$(document).ready(main);

function main(){
	
	cargarRol();
	cargarPaises();
	$("#myModal").modal('hide');
	$("#registroCliente").click(function(){
		$("#myModal").modal('toggle');
		$(".modal-backdrop").click(reset);
	});
	$("#realizarRegistro").click(registrarCliente);
	$("#reset").click(reset);
	$("#closeM").click(reset);
}

function registrarCliente(){
	
	var datosCliente = {
		id: 0,
		nombre: $("#inputNombre").attr("value"),
		apellidos: $("#inputApell").attr("value"),
		sexo: $("#tipoSexo").val(),
		estado: 1,
		user: $("#inputEmail").attr("value"),
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
        url: "registrarUsuario",
        beforeSend: function(){
        	$("#cargando").show("slow");
        },        
        success: function(data){
        	$("#cargando").hide("slow");
        	if (data.me == ""){
        		//alert("Ok :)");
        		$(location).attr('href', '/soft/sistemaWeb');
        	}
        	else{
        		alert(data.me);
        	}
        }
    });
	
}

function reset(){
	$("#inputNombre").attr("value","");
	$("#inputApell").attr("value","");
	$("#inputEmail").attr("value","");
	$("#inputCont").attr("value","");
	$("#inputCont2").attr("value","");
	$("#inputTelef").attr("value","");
	$("#inputDireccion").attr("value","");
	$("#inputNro").attr("value","");
	$("#tipoSexo option[value=" + "1" + "]").attr("selected", true);
    $("#tipoSexo").trigger('change');
    $("#tipoDoc option[value=" + "1" + "]").attr("selected", true);
    $("#tipoDoc").trigger('change');
    $("#tipoRol option[value=" + primerRol + "]").attr("selected", true);
    $("#tipoRol").trigger('change');
    $("#tipoPais option[value=" + primerPais + "]").attr("selected", true);
    $("#tipoPais").trigger('change');
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
        		
        		primerPais = data.listaPais[0].id;
        		
        		$.each(data.listaPais, function (i, item) {
        			escritor += '<option value = "' + item.id + '">' + item.nombre + '</option>';
        		});
        		
        		$("#tipoPais").html(escritor);
        	}
        	else{
        		alert("Hubo un error en la Base de Datos, se proceder‡ a recargar la p‡gina");
        		$(location).attr('href', '/soft/Usuario/registrarUsuario');
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
        		
        		primerRol = data.lRol[0].rol_id;
        		
        		$.each(data.lRol, function (i, item) {
        			escritor += '<option value = "' + item.rol_id + '">' + item.rol_nombre + '</option>';
        		});
        		
        		$("#tipoRol").html(escritor);
        	}
        	else{
        		alert("Hubo un error en la Base de Datos, se proceder‡ a recargar la p‡gina");
        		$(location).attr('href', '/soft/Usuario/registrarUsuario');
        	}
        	
        }
    });
}