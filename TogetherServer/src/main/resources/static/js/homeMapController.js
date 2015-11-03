var map;
var tommyHomeLat = 25.076237;
var tommyHomeLng = 121.480514
			
$(document).ready(function() {
	map = new GMaps({
		el : '#map',
		lat : tommyHomeLat,
		lng : tommyHomeLng
	});
	
	map.addMarker({
		lat : tommyHomeLat,
		lng : tommyHomeLng,
        title: 'Tommy Home',
        infoWindow: {
          content: '<p>Sweet home of Tommy</p>'
        }
	});
	
	GMaps.geolocate({
        success: function(position) {
        	var currentPosLat = position.coords.latitude;
        	var currentPosLng = position.coords.longitude;
			map.setCenter(currentPosLat, currentPosLng);
			
			map.addMarker({
				lat : currentPosLat,
				lng : currentPosLng,
		        title: 'You are here',
		        infoWindow: {
		          content: '<p>Hello~~</p>'
		        }
			});
        },
        error: function(error){
			alert('Geolocation failed: ' + error.message);
        },
		not_supported: function(){
          	alert("Your browser does not support geolocation");
        },
		always: function(){
			<!-- alert("Done!"); -->
		}
	});
});
