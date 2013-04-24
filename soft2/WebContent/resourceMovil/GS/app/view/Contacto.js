Ext.define('DP1.view.Contacto',{
	extend: 'Ext.form.Panel',
	xtype: 'contacto',
        id : 'contactForm',
	config: {
		title: 'Contacto',
		iconCls: 'user',
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
				xtype: 'fieldset',
				title: 'Mandanos un mensaje!',
				instructions: 'Revisa tu email',
				items: [
						{
			                xtype: 'emailfield',
			                name : 'email',
			                label: 'Email',
			                placeHolder : 'tuemail@tudominio.com'
			            },
			            {
                                        xtype: 'textareafield',
                                        label: 'Mensaje',
                                        maxRows: 4,
                                        name: 'mensaje'
                                    }
				]
			},
				{
					xtype: 'button',
					text: 'Enviar',
					ui: 'confirm',
					action: 'contactoAction'
				}
		]
                
	}
});