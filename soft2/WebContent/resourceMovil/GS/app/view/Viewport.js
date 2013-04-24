Ext.define('DP1.view.Viewport',{
	extend: 'Ext.TabPanel',
	xtype: 'viewport',
	config: {
			 fullscreen: true,
			 tabBarPosition: 'bottom',
			 iconCls:'user',
       		 title: 'Navigating Panels',
       		 layout: 'card',
		        animation: {
		            type: 'slide',
		            direction: 'left',
		            duration: 300
		        },
			 items: [
			 
			 	{
			 		xtype: 'homepanel'
			 	},
			 	
			 	{
			 		xtype : 'loginform'
			 	}
			 ]
	}
})