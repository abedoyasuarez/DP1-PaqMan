$(document).ready(function(){
	
	
	 if ($("#codigoRastreo").length > 0) {
		$("#codigoRastreo").keyup(function(){
			validarSoloNumeros($(this));
		});
		$("#codigoRastreo").change(function(){
			validarSoloNumeros($(this));
		});
	 }
	 
	 
	if ($("#inputEmail").length > 0) {
		 $("#inputEmail").change(function(){
			 validarEmail($(this));
		 });
	 }
	 
	
	if ($("#inputTelef").length > 0) {
		 $("#inputTelef").keyup(function(){
			 validarSoloNumeros($(this));
		 });
		 $("#inputTelef").change(function(){
			 validarSoloNumeros($(this));
		 });
	 }
	 
	
});

function validarSoloNumeros(data){
	var str = data.attr("value");
	
	for(var i = 0;i<str.length;i++){
		if(!(str[i] in { '1': 1, '2': 1, '3': 1, '4': 1, '5': 1, '6': 1, '7': 1, '8': 1, '9': 1, '0': 1 })){
			var str2 = str.substr(0,i);
			data.attr("value",str2);
			i = str.length;
		}
	}
}


function validarEmail(data){	
	var valor = data.attr("value");
    
    var filter = /[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
    
    if(!filter.test(valor)){
    	data.attr("value","");
    }
}