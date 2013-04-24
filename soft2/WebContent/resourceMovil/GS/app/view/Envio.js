/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('DP1.view.Envio',{
    extend: Ext.List,
    fullscreen: true,
    xtype: 'envio',
    config: {
		title: 'Envio',
		iconCls: 'maps'
	},
    store: {
        fields: ['name'],
        data: [
            {name: 'Cowper'},
            {name: 'Everett'},
            {name: 'University'},
            {name: 'Forest'}
        ]
    },

    itemTpl: '{name}',

    listeners: {
        select: function(view, record) {
            Ext.Msg.alert('Selected!', 'You selected ' + record.get('name'));
        }
    }
});



