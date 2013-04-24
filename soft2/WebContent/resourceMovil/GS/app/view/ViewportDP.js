Ext.define('DP1.view.ViewportDP',{
	extend: 'Ext.Panel',
	xtype: 'viewportDP',
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
			 		xtype : 'loginform'
			 	},
			 	{
			 		xtype: 'homepanel'
			 	}
			]
	}
})