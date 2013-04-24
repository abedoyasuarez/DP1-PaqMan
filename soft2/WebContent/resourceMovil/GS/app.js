Ext.Loader.setConfig({
	enabled: true
});

Ext.Loader.setPath({
    'Ext.plugin': 'lib/plugin'
});



Ext.application({
	name: 'DP1',
        appFolder: 'resourceMovil/GS/app',
	requires: [
        'Ext.MessageBox',
    ],
	controllers: ['Main'],
	launch: function(){
		DP1.container = Ext.create('DP1.view.ViewportDP');
	}
});