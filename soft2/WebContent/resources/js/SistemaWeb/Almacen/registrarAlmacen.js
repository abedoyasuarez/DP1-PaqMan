var primerCont;
var primerPais;
var primeraVez = 1;

$(document).ready(main);

function main(){
	cargarContinentes();
	$("#registroAlmacen").click(function(){
		$("#myModal").modal('toggle');
		$(".modal-backdrop").click(reset);
	});
	$("#realizarRegistro").click(registrarAlmacen);
	$("#tipoCont").change(cargarPaises);
	$("#reset").click(reset);
	$("#closeM").click(reset);
}



function registrarAlmacen(){
	var datosAlmacen = {
			id:0,
			ciudad: $("#inputCiudad").attr("value"),
			capacidad: $("#inputCap").attr("value"),
			estado:1,
			latitud: $("#inputLat").attr("value"),
			longitud: $("#inputLong").attr("value"),
			pais_id: $("#tipoPais").val(),
			continente_id: $("#tipoCont").val()
		};
		
		var jsonData = JSON.stringify(datosAlmacen);
		
		console.log(jsonData);
		
		$.ajax({
	        type: "POST",
	        data: jsonData,
	        dataType: "json",
	        contentType: "application/json; charset=utf-8",
	        url: "../Almacen/registrarAlmacen",
	        beforeSend: function(){
	        	
	        },        
	        success: function(data){
	        	console.log(data);
	        	//alert('Ok :)');
	        	$(location).attr('href', '/soft/sistemaWeb');
	        }
	    });
	
}

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
        		
        		if (primeraVez == 1){
        			primeraVez = 0;
        			primerPais = data.listaPais[0].id;
        		}
        		
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
        		
        		primerCont = data.listaContinente[0].id;
        		
        		$.each(data.listaContinente, function (i, item) {
        			escritor += '<option value = "' + item.id + '">' + item.nombre + '</option>';
        		});
        		
        		$("#tipoCont").html(escritor);
        		cargarPaises();
        	}
        	else{
        		alert("Hubo un error en la Base de Datos, se proceder‡ a recargar la p‡gina");
        		$(location).attr('href', '/soft/Almacen/registrarAlmacen');
        	}
        	
        }
    });
}

function reset(){
	$("#inputCiudad").attr("value","");
	$("#inputLat").attr("value","");
	$("#inputLong").attr("value","");
	$("#inputCap").attr("value","")
	$("#tipoCont option[value=" + primerCont + "]").attr("selected", true);
    $("#tipoCont").trigger('change');
}

