
$(document).ready(main);

function main(){
	
	$("#registroIncidencia").click(function(){
		$("#myModal").modal('toggle');
		$(".modal-backdrop").click(reset);
	});
	
	$("#realizarRegistro").click(registrarIncidencia);
	$("#realizarRegistro").hide();
	
	
	$("#inputEmailOld").change(comprobarCliente);
	$("#reset").click(reset);
	$("#closeM").click(reset);
	
}
function registrarIncidencia(){
	
	var dato = {
		usuario_id : $("#inputId").attr("value"),
		vuelo_id : $("#inputVuelo").attr("value"),
		incidencia_descripcion : $("#reclamo").attr("value")
	};
	
	var jsonData = JSON.stringify(dato);
	
	console.log(jsonData);
		
	$.ajax({
		type: "POST",
	    data: jsonData,
	    dataType: "json",
	    contentType: "application/json; charset=utf-8",
        url: "registrarIncidencia",
        beforeSend: function(){
        	$("#cargando").show("slow");
        },        
        success: function(data){
        	console.log(data);
        	$("#cargando").hide("slow");
        	if (data.me == ""){
        		alert("Ok :)");
        		$(location).attr('href', '/soft/Incidencia/registrarIncidencia');
        	}
        	else{
        		alert(data.me);
        	}
        	
        }
    });
	
}


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
	
	$("#wait").show("slow");

}

function llegadaConfirmacion(data){
	if ($("#inputEmailOld").attr("value") != ""){
		console.log(data);
		$("#wait").hide("slow");
		if (data.me == ""){
			
			$("#wrong").hide("slow");
			$("#ok").show("slow");
			$("#inputId").attr("value",data.id);
			$("#realizarRegistro").show("slow");
		}
		else{
			$("#ok").hide("slow");
			$("#wrong").show("slow");
			
			$("#inputId").attr("value","");
			$("#realizarRegistro").hide("slow");
			
		}
	}
	else{
		
		$("#wait").hide("slow");
		$("#ok").hide("slow");
		$("#wrong").hide("slow");
		$("#inputId").attr("value","");
		$("#realizarRegistro").hide("slow");
	}
}


function reset(){
	$("#inputEmailOld").attr("value","");
	$("#inputId").attr("value","");
	comprobarCliente();
	$("#realizarRegistro").hide();
	$("#reclamo").attr("value","");
	$("#inputVuelo").attr("value","");
	$("#wait").hide();
	$("#wrong").hide();
	$("#ok").hide();
}