Ext.define('DP1.view.Info', {
    extend: 'Ext.Carousel',
    fullscreen: true,
    xtype: "info",
    config: {
                title: 'Home',
		 iconCls: 'home',
		 cls: 'home',
        items: [{
                    xtype: 'toolbar',
                    docked: 'top',
                    ui: 'light',
                    title: 'Redex',
                   
                    items: [
            			{
            				xtype: 'button',
            				ui: 'back', 
            				text: 'Salir',
            				action: 'salirAplicativo'
            				
            			}
        				]
                   },{
            xtype: "panel",
            scrollable : true,
            layout: {
                type: 'vbox'
            },
            items: [{
                html : [
                        '<img src="http://www.redex.com.co:8090/images/logo.png" />',
                        '<h1>Bienvenido a Redex</h1>',
                        "<p>Redex, hace que el env&iacute;o de paquetes, sea un juego de ni&ntilde;os ",
                        '<h2>By Paque-tesis</h2>'
                    ].join("")
            }]
        },
        {
            xtype: "panel",
            scrollable : true,
            layout: {
                type: 'vbox'
            },
            items: [{
                html: [
                        '<h1 class="tarifas">Nuestras Tarifas</h1>',
                        '<table class="table table-hover">',
                          '<tbody><tr class="success">',
                            '<td>Continente</td>',
                            '<td>$5</td>',
                           '</tr>',
                          '<tr class="warning">',
                          '<td>Inter - Continente</td>',
                            '<td>$10</td>',
                          '</tr>',
                    '</tbody></table>'
                    ].join("")
            }
            ]
        }]
    }
});
/*
Ext.define('DP1.view.Info',{
    extend: 'Ext.Carousel',
    xtype : 'info',
    fullscreen: true,
    direction: 'vertical',
    config: {
		 title: 'Home',
		 iconCls: 'home',
		 cls: 'home'
		 //scrollable : true,
		 //styleHtmlContent: true
	},
    defaults: {
        styleHtmlContent: true
    },

    items: [
        {
            html : 'Item 1',
            style: 'background-color: #759E60'
        },
        {
            html : 'Item 2',
            style: 'background-color: #5E99CC'
        }
    ]


});

*/
/*
Ext.define('DP1.view.Info',{
	extend: 'Ext.Carousel',
	xtype : 'info',
	
        config: {
		 title: 'Home',
		 iconCls: 'home',
		 cls: 'home'
		 //scrollable : true,
		 //styleHtmlContent: true
	},
        items: [
            {
            html : 'Item 1',
            style: 'background-color: #5E99CC'
        },
        {
            html : 'Item 2',
            style: 'background-color: #759E60'
        },
        {
            html : 'Item 3'
        }
        ]
        
});

*/
/*
 html: [
                        '<img src="http://www.redex.com.co:8090/images/logo.png" />',
                        '<h1>Bienvenido a Redex</h1>',
                        "<p>Redex, hace que el envío de paquetes, sea un juego de niños ",
                        " Logueate y visualiza información de tus envíos </p>",
                        '<h2>By PacoteSoft</h2>'
                    ].join("")
 **/