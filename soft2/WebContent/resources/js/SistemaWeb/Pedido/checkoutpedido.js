$(document).ready(main);

function main(){
	$("#checkOut").click(function(){
		$("#myModal").modal('toggle');
		$(".modal-backdrop").click(reset);
                $("#datosPedido").hide();
                $("#confirmarCheckOut").hide();
                $("#checkcheck").show();
                $("#mensajeCheck").hide();
                $("#mensajeCheck").html("");
	});
		
	$("#reset").click(reset);
	$("#closeM").click(reset);
	
	$("#checkcheck").click(registrarCheck);
        $("#confirmarCheckOut").click(confirmarCheck);
}

function confirmarCheck(){
    var data = {
		id:parseInt($("#inputPedido").attr("value"))         
	};
    var jsonData = JSON.stringify(data);
    
    $.ajax({
        type: "POST",
        data:jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Pedido/RetirarPedido",
        beforeSend: function(){
        	$("#cargando").show("slow");
        },        
        success: function(data){
        	$("#cargando").hide("slow");
        	if(data.me == ""){
                       $("#mensajeCheck").html("Realizado correctamente!");
                       $("#mensajeCheck").show("slow");
        	}
        	else{
        		$("#mensajeCheck").html(data.me);
                        $("#mensajeCheck").show("slow");
        	}
        }
    });
}

function registrarCheck(){
	
	var data = {
		pedido_code:$("#inputPedido").attr("value")
		//contacto_documento:$("#tipoDoc").val(),
		//contacto_tipo_doc:$("#inputNroDoc").attr("value"),
		//contacto_nombre:$("#inputNombre").attr("value")
	};
	
	var jsonData = JSON.stringify(data);

	console.log(jsonData);
	
	$.ajax({
        type: "POST",
        data:jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Pedido/CheckOutPedido",
        beforeSend: function(){
        	
        },        
        success: function(data){
        	if(data.me == ""){
                        $("#checkcheck").hide("slow");
                        $("#tipoDoc").val(data.receptor_tipo_doc);
                        $("#inputNroDoc").val(data.receptor_numero_documento);
                        $("#inputNombre").val(data.receptor_nombre + " " + data.receptor_apellidos);
        		$("#datosPedido").show("slow");
                        $("#confirmarCheckOut").show("slow");
        	}
        	else{
        		alert(data.me);
        	}
        }
    });
	
}

function reset(){
	$("#inputPedido").attr("value","");
	$("#tipoDoc option[value=" + "1" + "]").attr("selected", true);
    $("#tipoDoc").trigger('change');
    $("#inputNroDoc").attr("value","");
    $("#inputNombre").attr("value","");
}

