

$(document).ready(main);


function main(){
	$("#barraOpc1").hide();
	$("#barraOpc2").hide();
	$("#barraOpc3").hide();
	$("#barraOpc4").hide();
	$("#barraOpc5").hide();
	$("#barraOpc6").hide();
	$("#divisor2").hide();
	$("#reloj").hide();
	$("#divReloj").hide();
								
	$("#myModal").modal('toggle');
	
	$("#alertaPass").hide();
	$("#recordarPass").hide();
	
	eventos();
}

function logearse(){
	
	var enviar = {
		user: $("#inputUser").attr("value"),
		password: $("#inputPass").attr("value")
	};
	
	var jsonData = JSON.stringify(enviar);
	
	console.log(jsonData);
	
	$.ajax({
        type: "POST",
        data: jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "Usuario/login",
        beforeSend: function(){
        	$("#alertaPass").hide("slow");
        	$("#recordarPass").hide("slow");
        },        
        success: function(data){
        	console.log(data);
        	if (data.me == ""){
        		localStorage.setItem("idCliente",data.id);
        		$(location).attr('href', '/soft/sistemaWeb');
        	}
        	else{
        		$("#alertaPass").show("slow");
            	$("#recordarPass").show("slow");
        	}
        }
    });
}

function eventos(){
	$("#login").click(logearse);
	
	$("#inputUser").keyup(function(){
		$("#alertaPass").hide("slow");
    	$("#recordarPass").hide("slow");
		$("#inputPass").attr("value","");
	});
	
	$("#recordarPass").click(function(){
		var data = {
			user : $("#inputUser").attr("value")	
        };

        var jsonData = JSON.stringify(data);
      
        $.ajax({
        	type: "POST",
        	data: jsonData,
        	dataType: "json",
        	contentType: "application/json; charset=utf-8",
        	url: "Usuario/recuperarContra",
        
        	success: function(data){
        		if (data.me =="") {
        			alert("Se ha enviado su contrse–a. Revise su correo electr—nico");
        		}
        		else {
        			alert(data.me);
        		}
        	}
        });
	});
}

function runScript(e){
	if (e.keyCode == 13) {
		logearse();
	}
}