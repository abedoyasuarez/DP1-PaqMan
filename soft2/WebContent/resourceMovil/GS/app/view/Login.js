Ext.define('DP1.view.Login',{
	extend: 'Ext.form.Panel',
	xtype: 'loginform',
    id : 'loginForm',
	config: {
		title: 'Login',
		iconCls: 'user',
		items: [
			{
                    xtype: 'toolbar',
                    docked: 'top',
                    ui: 'light',
                    title: 'Redex',
             },       
			{
				xtype: 'fieldset',
				title: 'Ingresa y disfruta!',
				instructions: 'Ingresa a tu cuenta',
				items: [
						{
			                xtype: 'emailfield',
			                name : 'email',
			                label: 'Email',
			                placeHolder : 'tuemail@tudominio.com'
			            },
			            {
			                xtype: 'passwordfield',
			                name : 'password',
			                label: 'Password'
			            }
				]
			},
				{
					xtype: 'button',
					text: 'Ingresar',
					ui: 'confirm',
					action: 'loginAction'
					/*
					handler: function(){
							var values = this.up('loginform').getValues();
							if (values.email && values.password) {
							    this.up('loginform').submit({
							        url: 'login.php',
							        method: 'POST',
							        success: function(form, result) {
							            if (result.me){
							            	 Ext.Msg.alert('', 'Bienvenido: ' + result.nombre);
							            }else{
							            	 Ext.Msg.alert('', 'Login Failed!');
							            }
							        },
							        failure: function(form, result) {
							            Ext.Msg.alert('', 'Login Failed!');
							        }
							    });
							} else {
							    Ext.Msg.alert('Error', 'Email y clave son requeridos.');
							}
					}
					*/
				}
		]
	}
});