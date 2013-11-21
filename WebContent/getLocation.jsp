<html>
<head>
<script src="http://maps.google.com/maps/api/js?sensor=false">
</script>
<script type="text/javascript">
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;
            document.coordinates.latitude.value = latitude;
            document.coordinates.longitude.value = longitude;
            var coords = new google.maps.LatLng(latitude, longitude);
            var mapOptions = {
                zoom: 15,
                center: coords,
                mapTypeControl: true,
                navigationControlOptions: {
                    style: google.maps.NavigationControlStyle.SMALL
                },
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(
                document.getElementById("mapContainer"), mapOptions
                );
            var marker = new google.maps.Marker({
                position: coords,
                map: map,
                title: "Your current location!"
            });
 
        });
    } else {
        alert("Geolocation API is not supported in your browser.");
    }
</script>
<style type="text/css">
#mapContainer {
height: 500px;
width: 800px;
border:10px solid #eaeaea;
}
</style>
</head>
<body>
Is that your location?<br>
<form name="coordinates" action="/DamageFinal/send" method="post">
<input type="hidden" id="latitude" name="latitude" value=""/>
<input type="hidden" id="longitude" name="longitude" value=""/>
<input type="submit" value="Yes" />
</form>
<a href="startpage.html">No</a>
<div id="latitude"></div>
<div id="longitude"></div>
<div id="mapContainer"></div>
</body>
</html>