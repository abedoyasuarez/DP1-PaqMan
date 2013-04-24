    
$(document).ready(function(){
	cargarScript("/soft/resources/js/Home/fokus.min.js",comprueba);
});

function cargarScript(url, comprueba){

        var script = document.createElement("script")
        script.type = "text/javascript";

        if (script.readyState){  
           script.onreadystatechange = function(){
            if (script.readyState == "loaded" ||
                script.readyState == "complete"){
                  script.onreadystatechange = null;
                  comprueba();
               }
            };
        } else {  
          script.onload = function(){
            comprueba();
          };
        }

        script.src = url;
        document.getElementsByTagName("head")[0].appendChild(script);
       }
       
       function comprueba(){
           //alert("cargo");
       }
       
