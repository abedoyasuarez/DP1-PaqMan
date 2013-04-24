Ext.define('GS.controller.Users',{
	extend: 'Ext.app.Controller',

	init: function(){
		this.control({
			'button': {
				tap: function(){
					alert('sape');
				}
			}
		});
	}
});