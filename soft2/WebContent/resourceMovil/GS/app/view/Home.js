 function dameColorGlobo(flag){
        switch (flag) { 
   	case 0 :
      	 return "FE7569";
      	 break 
   	case 1 :
         return "66FF00";   
      	 break 
   	case 2 : 
         return "eee";
      	 break 
   	default: break;
        } 
        return "CCC";
    }

Ext.define('DP1.view.Home',{
	extend : 'Ext.TabPanel',
	xtype: 'homepanel',
        alias: 'widget.ptabpanel',
	config: {
		fullscreen: true,
			    tabBarPosition: 'bottom',
			    defaults: {
                            styleHtmlContent: true
			    },
			    
			    items: [
			    	{   
			           xtype : 'info'
			        },/*
                                {
                                  xtype: 'envio'  
                                },*/
			        {
			        	 xtype: 'map',
                                        
                                         mapOptions: {
                                                zoom: 1
                                            },
                                        //seCurrentLocation: true,
                                        action : 'mapaClick',
                                        items: [
                                          {
                                            xtype: 'toolbar',
                                            docked: 'top',
                                            ui: 'light',
                                            title: 'Redex',
                                            defaults: {
                                                iconMask: true
                                            },

                                            items: [
                                                        
                                                        { 
                                                         xtype: 'button',
                                                         iconCls : 'refresh',
                                                         action: 'actualizar',
                                                         ui: 'plain'
                                                        },
                                                        
                                                        { 
                                                         xtype: 'button',
                                                         iconCls: 'locate',      
                                                         iconMask: true,
                                                         action: 'formRastreo',
                                                         ui: 'plain',
                                                         right: 50
                                                        },
                                                        { 
                                                         xtype: 'button',
                                                         iconCls: 'info',      
                                                         iconMask: true,
                                                         action: 'modalinfo',
                                                         ui: 'plain',
                                                         right: 5
                                                        }
                                                        
                                                     ]
                                           }  
                                        ]
			        },
			        {
			            xtype: 'contacto'
			        },
			    ]
	}
});

function mapita(comp, map){
    console.log(map);
}
function pintaMapa(comp, map){
        var code = localStorage.getItem("codeRastreo");
        Ext.Ajax.request({
            url: 'Pedido/ConsultarRutaMovil',
            method: 'POST',
            params: {
                id: code
            },

            failure: function (response) { },
            success: function (response, opts) { 
            //alert("success");
            }
        });
        new google.maps.Marker({
            position: new google.maps.LatLng(this._geo.getLatitude(), this._geo.getLongitude()),
            map: map
        });
    }

/*
fullscreen: true,
    tabBarPosition: 'bottom',

    defaults: {
        styleHtmlContent: true
    },

    items: [
        {
            title: 'Home',
            iconCls: 'home',
            html: 'Home Screen'
        },
        {
            title: 'Contact',
            iconCls: 'user',
            html: 'Contact Screen'
        }
    ]


    config: {
		title: 'Home',
		iconCls: 'home',
		cls: 'home',
		scrollable : true,
		styleHtmlContent: true,
		 html: [
                        '<img src="http://www.redex.com.co:8090/images/logo.png" />',
                        '<h1>Bienvenido a Redex</h1>',
                        "<p>Redex, hace que el envío de paquetes, sea un juego de niños ",
                        " Logueate y visualiza información de tus envíos </p>",
                        '<h2>By PacoteSoft</h2>'
                    ].join("")
	}
*/
