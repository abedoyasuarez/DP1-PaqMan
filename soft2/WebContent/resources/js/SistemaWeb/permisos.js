var locationHref = $(location).attr("href");
		
if ((locationHref.substring(locationHref.length-8,locationHref.length)) != 'loginWeb'){
	if (localStorage.getItem('idCliente') == null){
		$(location).attr("href",'/soft/loginWeb');
	}
}else{
	if (localStorage.getItem('idCliente') != null){
		$(location).attr("href",'/soft/sistemaWeb');
	}
}

$(document).ready(function(){
	$("#salirWeb").click(function(){
		localStorage.removeItem("idCliente");
		$(location).attr("href","/soft/");
	});
        
   
                   

});