
$(document).ready(main);

function main(){
	
	$("#guardar").click(enviarArchivo);
	
}

function enviarArchivo(){
	
	$.ajaxFileUpload({
		url:"/soft/Vuelos/cargarVuelos",
		secureuri:false,
		fileElementId:'archivo',
		dataType: 'json',
		
	});
	
}















/*
$(document).ready(function(){
    
    $("#guardar").click(function(){
         var formData = new FormData($('form')[0]);
         console.log(formData);
    });
   
   $(':file').change(function(){
    var file = this.files[0];
    name = file.name;
    size = file.size;
    type = file.type;
    //alert(type);
});
   
   function progressHandlingFunction(e){
        if(e.lengthComputable){
            $('progress').attr({value:e.loaded,max:e.total});
        }
    }
});
*/