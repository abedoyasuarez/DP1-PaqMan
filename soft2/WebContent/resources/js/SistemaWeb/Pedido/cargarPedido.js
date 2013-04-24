
$(document).ready(main);

function main(){
	
	$("#guardar").click(enviarArchivo);
	
}

function enviarArchivo(){
	
	$.ajaxFileUpload({
		url:"/soft/Pedido/cargarFile",
		secureuri:false,
		fileElementId:'archivo',
		dataType: 'json',
		data:{name:$("#file1").attr("value")}		
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




/*$(document).ready(main);

function main(){
	//cargarAlmacenes();
	//$("#guardar").click(enviarArchivo1);
        document.getElementById('files1').addEventListener('change', handleFileSelect, false);
}
function handleFileSelect(evt) {
    var files = evt.target.files;
    console.log(files);
    var reader = new FileReader();
    for (var i = 0, f; f = files[i]; i++) {
    reader.onload = (function(theFile) {
        return function(e) {
          // Render thumbnail.
          console.log(e.target.result);
          var nombre = escape(theFile.name);
          console.log(nombre);
          var data = {archivo : e.target.result, nombre : nombre };
          var jsonData = JSON.stringify(data);
          $.ajax({
                type: "POST",
                data: jsonData,
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                url:"../Pedido/CargarFile",
                success: function(data){
                    console.log("subido");
                }
           });
          
        };
      })(f);
    reader.readAsDataURL(f);
    }
}

function enviarArchivo1(){
    
    
    var arch = document.getElementById("archivo").files[0];
    console.log(arch);
    console.log("File name: " + arch.name);
    console.log("File name: " + arch.size);
    var nombre = arch.name;
    var tamanho = arch.size;
    var blob = arch.slice(startingByte, endindByte);
    console.log(blob);
       $.ajaxFileUpload({
                
		url:"../Pedido/CargarFile",
		secureuri:false,
		fileElementId:'archivo',
		dataType: 'json',
		data:{
			nombre: nombre
		}
	});
            return false;
}
function enviarArchivo(){
	var formData = new FormData($('*archivo*')[0]);
        console.log(formData);
        
	$.ajaxFileUpload({
                
		url:"../Pedido/CargarFile",
		secureuri:false,
		fileElementId:'archivo',
		dataType: 'json',
		data:{
			nombre: $("#archivo").attr("value")
		}
	});
	
}


function cargarAlmacenes(){
	$.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Pedido/CargarFile",
        beforeSend: function(){
        	
        },        
        success: function(data){
        	console.log(data);
        	
        	if (data.me == ""){
        		
        		var escritor = "";
        		
        		primerCiudad = data.listaAlmacenes[0].id;
        		
        		$.each(data.listaAlmacenes, function (i, item) {
        			escritor += '<option value = "' + item.id + '">' + item.ciudad + '</option>';
        		});
        		
        		$("#ciudadI").html(escritor);
        	}
        	else{
        		alert("Hubo un error en la Base de Datos, se proceder� a recargar la p�gina");
        		$(location).attr('href', '/soft/Pedido/registrarPedido');
        	}
        }
    });
}
*/