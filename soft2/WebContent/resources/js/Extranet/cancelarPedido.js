$(document).ready(main);

function main(){
	
	cargarPedidos();
	$("#tablita").hide();
	funciones();
}

function cargarPedidos(){
	
	var s = localStorage.getItem("idcliente");

	
	var jsonData = JSON.stringify(s);

	console.log(jsonData);
	
	$.ajax({
        type: "POST",
        data:jsonData,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: "../Pedido/ListarPedidos",
        //url: "../Simulacion/simular",
        beforeSend: function(){
        	
        },        
        success: imprimirTabla
    });
}

function imprimirTabla(data){
	
	console.log(data);

	
	var arregloId = new Array();
	var escritor = "";
	
	
	$.each(data.listaPedidos, function (i, item) {
		
		var n = i + 1;
		
        escritor += "<tr>";
        
        escritor += '<td>'+ n + '</td>';
        escritor += '<td>' + item.clienteRecibe + '</td>';
        escritor += '<td>' + item.ciudadPartida + '</td>';
        escritor += '<td>' + item.ciudadLLegada + '</td>';
        escritor += '<td>' + item.fechaSalida + '</td>';
        escritor += '<td>' + item.fechaLLegada + '</td>';
        escritor += '<td>' + item.estado_mensaje + '</td>';
        escritor += '<td>' + item.code + '</td>';
        escritor += '<td>' + '<a href ="" class = "mapita" id=mapa-' +item.code +'>'+ '<img alt ="ver mapa" src = "../resources/png/glyphicons_340_globe.png" /> </a>' + '</td>';
        //escritor += '<td>';
        //escritor += '<button id = "' + item.id +'" type="button" class="btn btn-danger">Cancelar</button></td>';
        
        escritor += "</tr>";
        
        arregloId[i] = item.id;
    });
	
	$("#cuerpoTabla").html(escritor);
	$("#tablita").show("slow");
        $("#progSimulacion").hide();
        funciones();
	
	//arregloId.forEach(function (elemento) {
	
}

function funciones(){
    $(".mapita").click(function(){
        var arrID = ((this.id).split("-"))[1];
        localStorage.setItem("idEnvio",arrID);
        $(location).attr("href","maparastreo");
        return false;
    });
}