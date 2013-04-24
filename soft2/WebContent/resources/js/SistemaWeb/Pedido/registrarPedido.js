
var primerCiudad;
var primerRol;
var primerPais;
var tipoDeViaje;

var continentesXCiudad = new Array();

$(document).ready(main);

function main(){
	
	cargarPaises();
	
	$("#regPedidoNew").click(registrarUsuario);
	$("#regPedidoOld").click(registrarPedido);
	$("#cargando").hide();
	$("#cancelNuevo").hide();
	$("#myModal").hide();
	$("#inputId").hide();
	$("#inputIdR").hide();
	$("#wait").hide();
	$("#ok").hide();
	$("#wrong").hide();
	$("#waitR").hide();
	$("#okR").hide();
	$("#wrongR").hide();
	
	$("#regPedidoOld").hide();
	
	$("#registroPedido").click(mostrarFormulario);
	
	$("#formDatos").hide();
	$("#regPedidoNew").hide();
	
	//$("#divNombreR").hide();
	//$("#divApellR").hide();
	
	//$("#inputNroR").change(comprobarCliente2);
	
	$("#regUser").click(function(){
		$("#inputEmailOld").attr("value","");
		comprobarCliente();
		$("#formDatos").show("slow");
		$("#regUser").hide("slow");
		$("#regPedidoNew").show("slow");
		$("#regPedidoOld").hide("slow");
		$("#groupEmail").hide("slow");
		$("#cancelNuevo").show("slow");
		
	});
	
	$("#cancelNuevo").click(function(){
		$("#formDatos").hide("slow");
	    $("#groupEmail").show("slow");
	    $("#regPedidoOld").hide("slow");
		$("#regPedidoNew").hide("slow");
		$("#regUser").show("slow");
		$("#cancelNuevo").hide("slow");
		
		$("#tipoDoc option[value=" + "1" + "]").attr("selected", true);
	    $("#tipoDoc").trigger('change');
	    $("#inputNro").attr("value","");
	    $("#tipoRol option[value=" + primerRol + "]").attr("selected", true);
	    $("#tipoRol").trigger('change');
	    $("#inputNombre").attr("value","");
	    $("#inputApell").attr("value","");
	    $("#tipoSexo option[value=" + "1" + "]").attr("selected", true);
	    $("#tipoSexo").trigger('change');
	    $("#inputEmail").attr("value","");
		$("#inputCont").attr("value","");
		$("#inputCont2").attr("value","");
		$("#inputTelef").attr("value","");
		$("#inputDireccion").attr("value","");
		$("#tipoPais option[value=" + primerPais + "]").attr("selected", true);
	    $("#tipoPais").trigger('change');
	});
	
	$("#inputEmailOld").change(comprobarCliente);
	
	
	
}


//*** Comprobaci�n si existe el cliente emisor
function comprobarCliente(){
	var buscarEmail = $("#inputEmailOld").attr("value");
	
	var jsonData = JSON.stringify(buscarEmail);
	
	console.log(jsonData);
	
	$.ajax({
        type: "POST",
        data: jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Usuario/verificarCliente",
        beforeSend: waitConfirmacion,        
        success: llegadaConfirmacion
    });
	
}

function waitConfirmacion(){
	$("#regPedidoOld").hide("slow");
	$("#wait").show("slow");
	$("#regUser").show("slow");
}

function llegadaConfirmacion(data){
	if ($("#inputEmailOld").attr("value") != ""){
		console.log(data);
		$("#wait").hide("slow");
		if (data.me == ""){
			$("#regPedidoOld").show("slow");
			$("#wrong").hide("slow");
			$("#ok").show("slow");
			$("#inputId").attr("value",data.id);
			$("#regUser").hide("slow");
		}
		else{
			$("#regPedidoOld").hide("slow");
			$("#ok").hide("slow");
			$("#wrong").show("slow");
			$("#regUser").show("slow");
			//$("#inputEmailOld").attr("value","");
			$("#inputId").attr("value","");
			//alert(data.me);
		}
	}
	else{
		
		$("#wait").hide("slow");
		$("#ok").hide("slow");
		$("#wrong").hide("slow");
		$("#inputId").attr("value","");
	}
}
//***Fin comprocaci�n si existe el cliente


function mostrarFormulario(){
	$("#myModal").modal('toggle');
	
	
	$("#cerrar").click(reset);
	$(".modal-backdrop").click(reset);
}


//*** Registro del usaurio emisor
function registrarUsuario(){
	
	$("#inputId").attr("value","");
	
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
        url: "../Usuario/registrarUsuario",
        beforeSend: waitRegistro,        
        success: continuarRegistros
    });
	
}

function waitRegistro(){
	
}

function continuarRegistros(data){
	if (data.me == ""){
		alert("Usuario Registrado");
		$("#inputId").attr("value",data.id);
		registrarPedido();
	}
	else{
		alert(data.me);
	}
}
//*** fin registro nuevo usaurio emisor


function registrarPedido(){
	
	tipoDeViaje = 1;
	if(continentesXCiudad[$("#ciudadI").val()] == continentesXCiudad[$("#ciudadF").val()]){
		tipoDeViaje = 0;
	}
	
	
	var dataPedido = {
		cantidad: $("#inputCant").attr("value"),
		almacen_partida: $("#ciudadI").val(),
		almacen_entrega: $("#ciudadF").val(),
		viaje: tipoDeViaje, //cerificar con los almacenes si pertenece al continente
		cliente_envia: $("#inputId").attr("value"),
		receptor_nombre: $("#inputNombreR").attr("value"),
		receptor_apellidos: $("#inputApellR").attr("value"),
		receptor_documento_id: $("#tipoDocR").val(),
		receptor_numero_documento: $("#inputNroR").attr("value"),
		receptor_telefono: $("#inputTelefR").attr("value")
	};
	
	var jsonData = JSON.stringify(dataPedido);
	
	console.log(jsonData);
	
	$.ajax({
        type: "POST",
        data: jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Pedido/registrarPedido",
        beforeSend: waitFin,        
        success: continuarFin
    });
	
}

function waitFin(){
	$("#myModalLabel").hide("slow");
	$("#cargando").show("slow");
}

function continuarFin(data){
	console.log(data);
	$("#cargando").hide("slow");
	$("#myModalLabel").show("slow");
	if (data.me == ""){
		$("#myModal").modal('hide');
		mostrarRespuesta(data);
	}
	else{
		alert(data.me);
	}
}

function mostrarRespuesta(data){
	
	console.log(data);
	
	$("#myModal2").modal('toggle');
	
	$("#mostrarUsuario").text(data.user);
	$("#mostrarCode").text(data.code);
	
	$(".modal-backdrop").click(function(){
		$(location).attr('href', '/soft/Pedido/registrarPedido');
	});
	
	$("#finish").click(function(){
		$(location).attr('href', '/soft/Pedido/registrarPedido');
	});
}

//CARgar el combobox
function cargarAlmacenes(){
	$.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Almacen/listarAlmacenesActivos",
        beforeSend: function(){
        	
        },        
        success: function(data){
        	console.log(data);
        	
        	if (data.me == ""){
        		
        		var escritor = "";
        		
        		primerCiudad = data.listaAlmacenes[0].id;
        		
        		$.each(data.listaAlmacenes, function (i, item) {
        			escritor += '<option value = "' + item.id + '">' + item.ciudad + '</option>';
        			continentesXCiudad[item.id] = item.continente_id;
        		});
        		
        		$("#ciudadI").html(escritor);
        		$("#ciudadF").html(escritor);
        		console.log(continentesXCiudad);
        		cargarRol();
        	}
        	else{
        		alert("Hubo un error en la Base de Datos, se proceder� a recargar la p�gina");
        		$(location).attr('href', '/soft/Pedido/registrarPedido');
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
        		alert("Hubo un error en la Base de Datos, se proceder� a recargar la p�gina");
        		$(location).attr('href', '/soft/Pedido/registrarPedido');
        	}
        	
        }
    });
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
        		cargarAlmacenes();
        	}
        	else{
        		alert("Hubo un error en la Base de Datos, se proceder� a recargar la p�gina");
        		$(location).attr('href', '/soft/Pedido/registrarPedido');
        	}
        	
        }
    });
}

function reset(){
	$("#regUser").show();
	$("#inputEmailOld").attr("value","");
	comprobarCliente();
	$("#regPedidoOld").hide();
	$("#regPedidoNew").hide();
	
	$("#inputCant").attr("value","");
	$("#ciudadI option[value=" + primerCiudad + "]").attr("selected", true);
    $("#ciudadI").trigger('change');
    $("#ciudadF option[value=" + primerCiudad + "]").attr("selected", true);
    $("#ciudadF").trigger('change');
	$("#tipoDocR option[value=" + "1" + "]").attr("selected", true);
    $("#tipoDocR").trigger('change');
    $("#inputNroR").attr("value","");
    $("#inputNombreR").attr("value","");
    $("#inputApellR").attr("value","");
    $("#inputTelefR").attr("value","");
    
    $("#tipoDoc option[value=" + "1" + "]").attr("selected", true);
    $("#tipoDoc").trigger('change');
    $("#inputNro").attr("value","");
    $("#tipoRol option[value=" + primerRol + "]").attr("selected", true);
    $("#tipoRol").trigger('change');
    $("#inputNombre").attr("value","");
    $("#inputApell").attr("value","");
    $("#tipoSexo option[value=" + "1" + "]").attr("selected", true);
    $("#tipoSexo").trigger('change');
    $("#inputEmail").attr("value","");
	$("#inputCont").attr("value","");
	$("#inputCont2").attr("value","");
	$("#inputTelef").attr("value","");
	$("#inputDireccion").attr("value","");
	$("#tipoPais option[value=" + primerPais + "]").attr("selected", true);
    $("#tipoPais").trigger('change');
    
    $("#formDatos").hide();
    $("#groupEmail").show();
    $("#cancelNuevo").hide();
}
