<%-- 
    Document   : Contacto
    Created on : 11/11/2012, 11:42:34 PM
    Author     : jpcode
--%>
<script type="text/javascript">
 $("#formContacto").show();
 $("#errorMensaje").hide();
$("#EnviarContacto").click(function(){
   var nombre = $("#inputNombre").val();
   var email  = $("#inputEmail").val();
   var mensaje = $("#inputMensaje").val();

   var data = {nombre : nombre,
               email  : email,
               mensaje: mensaje
               };
                            var jsonData = JSON.stringify(data); 
                            $.ajax({
                            type: "POST",
                            data: jsonData,
                            dataType: "json",
                            contentType: "application/json; charset=utf-8",
                            url: "Usuario/contactenos",
                            //beforeSend: waitTable,        
                            success: function(data){
                            if (data.me =="") {
                                //alert("enviado");
                                 $("#formContacto").hide("slow");
                                 $("#mensajeContacto").html("Gracias Por Contactar con Nosotros!!");
                            }else {
                                 $("#errorMensaje").html(data.me);
                                 $("#errorMensaje").show("slow");
                            }
                        }
                        });
   return false;
});
</script>

<script src = "/soft/resources/js/Home/validaciones.js"></script>

<div class="hero-unit">
  <h2 class ="text-success" id ="mensajeContacto">Tienes dudas? envíanos un mensaje!</h2>
 <form class="form-horizontal" id ="formContacto">
 <div class="control-group">
    <label class="control-label" for="inputNombre">Nombre</label>
    <div class="controls">
      <input type="text" id="inputNombre" placeholder="Su nombre">
    </div>
  </div>    
     
  <div class="control-group">
    <label class="control-label" for="inputEmail">Email</label>
    <div class="controls">
      <input type="text" id="inputEmail" placeholder="Email">
    </div>
  </div>
  
  <div class="control-group">
   
      <label class="control-label" for="inputMensaje">Mensaje
          
      </label>
      
    <div class="controls">
        <textarea id ="inputMensaje" rows="3"></textarea>
        <br /><br />
         <button id="EnviarContacto" class="btn btn-success">Enviar</button>
         <span id ="errorMensaje" class ="alert alert-error"></span>
    </div>
  </div>
</form>
</div>