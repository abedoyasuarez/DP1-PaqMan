Ext.define('GS.view.Principal', {
    extend: 'Ext.Panel',
    xtype: 'main',
    layout: 'card',
   
    config: {
        tabBarPosition: 'bottom',

        items: [
        {
            html: "First Item"
        },
        {
            html: "Second Item"
        },
        {
            html: "Third Item"
        },
        {
            html: "Fourth Item"
        }
    ]
    }
});