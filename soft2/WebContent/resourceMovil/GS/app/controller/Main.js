Ext.define('DP1.controller.Main',{
	extend: 'Ext.app.Controller',
	views: ['Home','Login','Info','Map','Envio','Contacto'],

	refs : [
		{
			ref : 'loginForm',
			selector : '#loginForm'
		},
                {
                        ref: 'contactForm',
                        selector : '#contactForm'
                },
                 {
                        ref: 'rastreoForm',
                        selector : '#rastreoForm'
                },{
                    ref : 'mapita',
                    selector : '#mapita'
                }
	],

	
	init : function(){
		console.log('Main iniciado');
		this.control({
			//'button[action=submitLogin]'}}
                        'button[action = rastrearEnvio]' : {
                            tap : 'rastrearEnvio'
                        },
                        'button[action = formRastreo]' : {
                            tap : 'insertarRastreo'
                        },
                        'button[action = modalinfo]' : {
                            tap : 'abrirModal'
                        },
                        'button[action = actualizar]':{
                            tap :   'actualizarMapa'
                        },
			'button[action = loginAction]':{
				tap: 'ingresaAplicativo'
			},
			'button[action = salirAplicativo]':{
				tap: 'salir'
			},
                        'button[action = contactoAction]': {
                                tap: 'contactar'
                        },
                        
                       
                        "ptabpanel #ext-tab-2": {
                            tap: 'actualizarMapa'
                         }
                       
		})
	},
        insertarRastreo : function () {
            var title = 'Rastreo';
            var helpText = 'sape';
            
            DP1.rastreoPanel = DP1.rastreoPanel || Ext.create('Ext.Panel', {
                    ui: 'dark',
                    modal: true,
                    fullscreen: false,
                    hideOnMaskTap: true,
                    centered: true,
                    width: 300,
                    height: 250,
                    styleHtmlContent: true,
                    scrollable: 'vertical',
                    zIndex: 100,
                    items: [
                        {
                            docked: 'top',
                            xtype: 'toolbar',
                            title: title
                        },
                         {
                            xtype: 'fieldset',
                            title: 'C&oacute;digo de rastreo',
                            
                            instructions: 'Rastree cualquier env&iacute;o',
                            items: [
                                {
                                    xtype: 'textfield',
                                    name : 'rastreo',
                                    id : 'rastreoForm',
                                    placeholder : 'Codigo de Rastreo'
                                    
                                }
                            ]
                        },
                        {
					xtype: 'button',
					text: 'Rastrear!',
					ui: 'confirm',
					action: 'rastrearEnvio'
			}
                        
                    ]
                });
                DP1.rastreoPanel.show('pop');
        },
        
        rastrearEnvio : function () {
            DP1.rastreoPanel.hide();
            Ext.Viewport.setMasked({xtype:'loadmask',message:'Cargando'});
            var formRastreo = this.getRastreoForm();
            console.log(formRastreo.getValue());
            var mapp = this.getMapita();
            //mapp.doLayout();
            var mapa = mapp.getMap();
            var pinImage = new google.maps.MarkerImage("http://mapicons.nicolasmollet.com/wp-content/uploads/mapicons/shape-default/color-2643ff/shapecolor-white/shadow-1/border-color/symbolstyle-color/symbolshadowstyle-no/gradient-no/yoga.png",
            new google.maps.Size(32, 37),
            new google.maps.Point(0,0),
            new google.maps.Point(10, 34));
                                                
                                                
        var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
            new google.maps.Size(40, 37),
            new google.maps.Point(0, 0),
            new google.maps.Point(12, 35));
            
            Ext.Ajax.request({
            url: 'Pedido/ConsultarRutaMovil',
            method: 'POST',
            params: {
                id: formRastreo.getValue()
            },
            failure: function (response) { },
            success: function (response, opts) { 
                
                var data = Ext.JSON.decode(response.responseText.trim());
                //alert(data.success); 
                if (data.me == ""){
                    var rutaMapa = new Array();
                    var arrRuta = new Array();
                    var arrRutaPintar = new Array();
                    var arrRuta = data.lRuta;
                    //console.log(arrRuta);
                    var cont = 0;
                    var rutaMapa = arrRuta[0];
                    //console.log(rutaMapa.length);
                    var route =  [ ];
                    if (rutaMapa)                
                    for (i = 0; i < rutaMapa.length; i++){
                        var ruta = new Array();
                        ruta = rutaMapa[i];
                        route.push(  new google.maps.LatLng( ruta.latitud , ruta.longitud ) );
                        var globito = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" +  dameColorGlobo(ruta.paso),
                            new google.maps.Size(21, 34),
                            new google.maps.Point(0,0),
                            new google.maps.Point(10, 34));
                        new google.maps.Marker({
                            position: new google.maps.LatLng(ruta.latitud,ruta.longitud),
                            map: mapa,
                            icon: globito,
                            shadow: pinShadow
                        });
                        var unaRuta = {
                            latitud : ruta.latitud,
                            longitud : ruta.longitud
                        };
                        arrRutaPintar[i] = unaRuta; 
                    }
                    console.log(arrRutaPintar);
                    var lineas = new google.maps.Polyline({
                        path: route,
                        map: mapa,
                        strokeColor: '#222000',
                        strokeWeight: 4,
                        strokeOpacity: 0.6,
                        clickable: false
                    });
                }else{
                    Ext.Viewport.setMasked(false);
                    Ext.Msg.alert('Info', 'No tiene envíos activos.');
                }
                Ext.Viewport.setMasked(false);
            }
        });
            
        },
        abrirModal : function () {
            
           
            var title = 'Info';
            var helpText = 'Puedes ver la informaci&oacute;n de tu &uacute;ltimo env&iacute;o! o puedes rastrear el que quieras';
            
            DP1.helpPanel = DP1.helpPanel || Ext.create('Ext.Panel', {
                    ui: 'dark',
                    modal: true,
                    fullscreen: false,
                    hideOnMaskTap: true,
                    centered: true,
                    width: 300,
                    height: 250,
                    styleHtmlContent: true,
                    scrollable: 'vertical',
                    zIndex: 100,
                    items: [
                        {
                            docked: 'top',
                            xtype: 'toolbar',
                            title: title
                        },
                        {
                            html: helpText,
                            hidden: !helpText
                        }
                    ]
                });
                DP1.helpPanel.show('pop');
           
        },
        
        actualizarMapa : function (){
             var mapp = this.getMapita();
            //mapp.doLayout();
            var map = mapp.getMap();
            Ext.Viewport.setMasked({xtype:'loadmask',message:'Cargando'});
        var code = localStorage.getItem("codeRastreo");
        /*Mi posicion*/
        var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
            new google.maps.Size(40, 37),
            new google.maps.Point(0, 0),
            new google.maps.Point(12, 35));
        /*Fin Mi Posicion*/
        Ext.Ajax.request({
            url: 'Pedido/ConsultarRutaMovil',
            method: 'POST',
            params: {
                id: code
            },

            failure: function (response) { },
            success: function (response, opts) { 
                var data = Ext.JSON.decode(response.responseText.trim());
                //alert(data.success); 
                if (data.me == ""){
                    var rutaMapa = new Array();
                    var arrRuta = new Array();
                    var arrRutaPintar = new Array();
                    var arrRuta = data.lRuta;
                    //console.log(arrRuta);
                    var cont = 0;
                    var rutaMapa = arrRuta[0];
                    //console.log(rutaMapa.length);
                    var route =  [ ];
                    if (rutaMapa)               
                    for (i = 0; i < rutaMapa.length; i++){
                        
                        var ruta = new Array();
                        ruta = rutaMapa[i];
                        if (i == 0){
                            map.panTo(new google.maps.LatLng(ruta.latitud,ruta.longitud));
                        }
                        route.push(  new google.maps.LatLng( ruta.latitud , ruta.longitud ) );
                        var globito = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" +  dameColorGlobo(ruta.paso),
                            new google.maps.Size(21, 34),
                            new google.maps.Point(0,0),
                            new google.maps.Point(10, 34));
                        new google.maps.Marker({
                            position: new google.maps.LatLng(ruta.latitud,ruta.longitud),
                            map: map,
                            icon: globito,
                            shadow: pinShadow
                        });
                        var unaRuta = {
                            latitud : ruta.latitud,
                            longitud : ruta.longitud
                        };
                        arrRutaPintar[i] = unaRuta; 
                    }
                    console.log(arrRutaPintar);
                    var lineas = new google.maps.Polyline({
                        path: route,
                        map: map,
                        strokeColor: '#222000',
                        strokeWeight: 4,
                        strokeOpacity: 0.6,
                        clickable: false
                    });
                    
                }else{
                    Ext.Msg.alert('Info', 'No tiene envíos activos.');
                }
                Ext.Viewport.setMasked(false);
            }
        });
        },
        
        dibujaMapa: function(comp, map) {
            alert("sape");
 
        },
        contactar : function(){
            
            var formContacto = this.getContactForm();
            var values = formContacto.getValues();

            if (values.email && values.mensaje){
                Ext.Viewport.setMasked({xtype:'loadmask',message:'Cargando'});
                 formContacto.submit({
                                                 url: 'Usuario/contactenosmovil',
						 method: 'POST',
						 success: function(form, result) {
								//alert(result.nombreUser + ' cabrito');
                                                                Ext.Viewport.setMasked(false);
								if (result.me == ""){
						  				formContacto.setValues({
                                                                                    mensaje: ''
                                                                                });	
                                                                                Ext.Msg.alert('Info', 'Gracias por contactarse con nosotros');
						  				
						  		}else {
						  			Ext.Msg.alert('Error',result.me);
						  		}
						  },
						 failure: function(form, result) {
							 Ext.Viewport.setMasked(false);	
                                                         Ext.Msg.alert('Info', 'Error del back end');
					      }
					});
            }else{
                Ext.Msg.alert('Info', 'Email y mensaje son requeridos.');
            }
        },
        
        
	salir : function(){
		DP1.container.setActiveItem(0);
	},

	ingresaAplicativo: function(){
			var form = this.getLoginForm();
			var values = form.getValues();
			if (values.email && values.password) {
                            //localStorage.setItem("email",values.email);
                            Ext.Viewport.setMasked({xtype:'loadmask',message:'Cargando'});
                            var formContacto = this.getContactForm();
			    formContacto.setValues({
                                email: values.email
                            })	
                                form.submit({
					url: 'Usuario/loginmovil',
						 method: 'POST',
						 success: function(form, result) {
								//alert(result.nombreUser + ' cabrito');
								if (result.me == ""){
                                                                                Ext.Viewport.setMasked(false);
                                                                                localStorage.setItem("codeRastreo",result.code);
						  				Ext.Msg.alert('Bienvenido', result.nombreUser);
						  				DP1.container.setActiveItem(1); // me voy al segundo card
						  		}else {
						  			Ext.Viewport.setMasked(false);
                                                                        Ext.Msg.alert('Error',result.me);
						  		}
						  },
						 failure: function(form, result) {
							Ext.Viewport.setMasked(false);		            
					      }
					});
			}else{
				Ext.Msg.alert('Error', 'Email y clave son requeridos.');	
			}
			


			/*
			Ext.Ajax.request({
			    url: 'backend/login.php',
			    method: 'POST',
			    headers: {
			        'Content-Type': 'application/json;charset=utf-8'
			    }, 
				    
				    params: {
			        user: values.email,
			        pass: values.password
			    },
			    
			    failure: function (response) { },
			    success: function (response, opts) { 
			    		//alert("success");
			    }
			});

		*/
		/*

		//console.log("enviando");
		var form = this.getLoginForm();
		//console.log(form);
		var values = form.getValues();
		if (values.email && values.password) {
				//alert(values.email + " " + values.password);
				Ext.getCmp('navigatingPanels').setActiveItem(0);
				//console.log(aa);
		}
		else {
			//Ext.Msg.alert('Error', 'Email y clave son requeridos.');					
			alert("sape");
		}
		*/
	}
});