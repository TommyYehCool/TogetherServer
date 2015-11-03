var map;

$(document).ready(function() {
	var tommyHomeLat = 25.076237;
	var tommyHomeLng = 121.480514
	
	// New 出一個 map, 預設位置在  Tommy 家
	map = new GMaps({
		el : '#map',
		lat : tommyHomeLat,
		lng : tommyHomeLng
	});
	
	// 在 Tommy 家新增一個 Marker
	addMarker(tommyHomeLat, tommyHomeLng, 'Tommy Home', 'Sweet home of Tommy')
	
	// 抓取目前的位置
	GMaps.geolocate({
        success: function(position) {
        	var currentPosLat = position.coords.latitude;
        	var currentPosLng = position.coords.longitude;
			map.setCenter(currentPosLat, currentPosLng);
			addMarker(currentPosLat, currentPosLng, 'You are here', 'Hello~~');
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

function addMarker(latOfMarker, lngOfMarker, titleOfMarker, showText) {
	map.addMarker({
		lat : latOfMarker,
		lng : lngOfMarker,
        title: titleOfMarker,
        infoWindow: {
        	content: '<p>' + showText + '</p>'
        }
	});
}
