$(document).ready(function() {
	var map;
	var tommyHomeLat = 25.076237;
	var tommyHomeLng = 121.480514
	
	// New 出一個 map, 預設位置在  Tommy 家
	map = new GMaps({
		el : '#map',
		lat : tommyHomeLat,
		lng : tommyHomeLng
	});
	
	// 在 Tommy 家新增一個 Marker
	map.addMarker({
		lat : tommyHomeLat,
		lng : tommyHomeLng,
        title: 'Tommy Home',
        infoWindow: {
          content: '<p>Sweet home of Tommy</p>'
        }
	});
	
	// 抓取目前的位置
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
