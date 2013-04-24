$(document).ready(function(){
		
	$("#loadReloj").hide();
    $('#fechaSistema').datetimepicker({ dateFormat: 'yy-mm-dd', timeFormat: 'HH:mm' });

    $("#reloj").click(function(){
       $('#modalFecha').modal('toggle');
       setearFecha();
       return false;
    });
    
    $("#cambiarHora").click(function(){
		
		$("#cambiarHora").hide("slow");
		
	    var fecha = $("#fechaSistema").val();
	    var data = {fecha : fecha };
	    var jsonData = JSON.stringify(data); 
	    console.log(jsonData);
	    $.ajax({
	    	type: "POST",
	    	data: jsonData,
	    	dataType: "json",
	    	contentType: "application/json; charset=utf-8",
	    	url: "/soft/Time/CambiarFecha",
			beforeSend:function(){
				$("#loadReloj").show("slow");
			},
	    	success: function(data){
				$("#loadReloj").hide("slow");
	    		if(data.me == ""){
	    			//alert("Exito :)");					
	    			$("#confirma").show("slow");
                                $("#cambiarHora").show("slow");
	    		}
	    		else{
	    			alert(data.me);
	    		}
	    	}
	    });
	    return false;
	         
    });
});

function setearFecha(){
	$.ajax({
		type: "POST",
    	dataType: "json",
    	contentType: "application/json; charset=utf-8",
    	url: "/soft/Time/ObtenerFecha",
    	beforeSend:function(){
    		$("#loadReloj").show("slow");
    	},
    	success: function(data){
    		console.log(data);
    		$("#loadReloj").hide("slow");
    		if(data.me == ""){
    			$("#fechaSistema").val(data.fecha);
    		}
    		else{
    			alert(data.me);
    		}
    	}
	});
}