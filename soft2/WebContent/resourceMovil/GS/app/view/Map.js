/*var position = new google.maps.LatLng(37.44885, -122.158592);
var mapdemo = Ext.create('Ext.Map', {
            mapOptions : {
                center : new google.maps.LatLng(37.381592, -122.135672),  //nearby San Fran
                zoom : 12,
                mapTypeId : google.maps.MapTypeId.ROADMAP,
                navigationControl: true,
                navigationControlOptions: {
                    style: google.maps.NavigationControlStyle.DEFAULT
                }
            },
         listeners: {
                maprender: function(comp, map) {
                    var marker = new google.maps.Marker({
                        position: position,
                        title : 'Sencha HQ',
                        map: map
                    });

                    google.maps.event.addListener(marker, 'click', function() {
                        infowindow.open(map, marker);
                    });

                    setTimeout(function() {
                        map.panTo(position);
                    }, 1000);
                }

            }
        });
    */    
        
Ext.define('DP1.view.Map',{
	extend: 'Ext.Map',
	xtype: 'map',
        id : 'mapita',
	config: {
		title: 'Rastreo',
		iconCls: 'maps'
	},listeners: {
            'tap' : function(){
                alert("sape");
            }
        }
});
        /*
DP1.mapa = Ext.define('DP1.view.Map',{
            extend: 'Ext.Panel',
            xtype: 'map',
            config: {
		title: 'Rastreo',
		iconCls: 'maps'
	},
            //items: [mapdemo]
            initialize: function() {
                items: [mapdemo];
            }
        });

*/
/*
var center = new google.maps.LatLng(37.44885, -122.158592);
var map = new Ext.Map({
        mapOptions : {
            center : center,
            zoom : 20,
            mapTypeId : google.maps.MapTypeId.HYBRID,
            navigationControl: true,
            navigationControlOptions: {
                    style: google.maps.NavigationControlStyle.DEFAULT
                }
        },

        listeners : {
            maprender : function(comp, map){

                var marker = new google.maps.Marker({
                     position: center,
                     //title : 'Sencha HQ',
                     map: map
                });
                setTimeout( function(){map.panTo (center);} , 1000);
            }

        },

         geo:new Ext.util.GeoLocation({
              autoUpdate:true,
              maximumAge: 0,
              timeout:2000,
              listeners:{
                locationupdate: function(geo) {
                  center = new google.maps.LatLng(geo.latitude, geo.longitude);
                  if (map.rendered)
                    map.update(center)
                  else
                    map.on('activate', map.onUpdate, map, {single: true, data: center});
                },
                 locationerror: function (   geo,
                                            bTimeout, 
                                            bPermissionDenied, 
                                            bLocationUnavailable, 
                                            message) {
                    if(bLocationUnavailable){
                        alert('Your Current Location is Unavailable on this device');
                    }
                    else{
                        alert('Error occurred.');
                    }      
                 }
              }
         })

  });

DP1.mapa = Ext.define('DP1.view.Map',{
	extend: 'Ext.Panel',
	xtype: 'map',
        id : 'map',
	config: {
		title: 'Rastreo',
		iconCls: 'maps'
	},
        items: []
});
*/