<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.LinkedList"%>
<%@page import="damage.model.bean.OfflineBean" %>
<!DOCTYPE html>
<html >
	<head>
		<title>Show damage on the Map</title>
		<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>
		<script type="text/javascript" src= "https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
		<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=places&sensor=false&language=en"></script>
		<script type="text/javascript" >   google.maps.visualRefresh = true;

		
		function getInputCoordinates() {
				var value=$('#input-coordinates').val();
				if (value) value=value.replace(/\r\n/g,'\n').replace(/\n\r/g,'\n').replace(/\r/g,'\n');
				
				return $.trim(value);
			}
				
			var renderMarker,renderMarkers,clearMarkers,posFromLatlng,addSearchMarker=false,renderAutofitmap=true;
			var geocoder = new google.maps.Geocoder();
			var helsinki = new google.maps.LatLng(60.173324,24.941024);
			var ownpos;
			
			if (navigator.geolocation) {
				  navigator.geolocation.getCurrentPosition(success, error);
				} else {
				  alert("Not Supported!");
				}
			
			function success(position) {
				  var latitude = position.coords.latitude;
				  var longitude = position.coords.longitude;
				  ownpos = new google.maps.LatLng(latitude, longitude);
				}
			
			function error(msg) {
				  console.log(typeof msg == 'string' ? msg : "error");
				}
			
			var pinColor = "0101DF";
			var col2 = "DF0101";
			var pin2 = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + col2,
			        new google.maps.Size(21, 34),
			        new google.maps.Point(0,0),
			        new google.maps.Point(10, 34));
		    var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
		        new google.maps.Size(40, 37),
		        new google.maps.Point(0, 0),
		        new google.maps.Point(12, 35));
	
			$(document).ready(function() {
				var bounds=new google.maps.LatLngBounds();
				var markers=[],mapMain = new google.maps.Map(document.getElementById('map-canvas'), {
					center: ownpos,
					zoom: 15,
					disableDoubleClickZoom: true,
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					mapTypeControl: true,
					noClear:true,
					panControl:true,
					zoomControl:true,
					scaleControl:true,
					scaleControlOptions:{position:google.maps.ControlPosition.BOTTOM_RIGHT},
					streetViewControl: false,
					minZoom:4
				});
				
				navigator.geolocation.getCurrentPosition(function (position) {
		            var latitude = position.coords.latitude;
		            var longitude = position.coords.longitude;
		            var coords = new google.maps.LatLng(latitude, longitude);
		            
		           var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
				        new google.maps.Size(21, 34),
				        new google.maps.Point(0,0),
				        new google.maps.Point(10, 34));
				    var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
				        new google.maps.Size(40, 37),
				        new google.maps.Point(0, 0),
				        new google.maps.Point(12, 35));
		            
		            var currentpositionmarker = new google.maps.Marker({
		                position: coords,
		                map: mapMain,
						icon: pinImage,
		                title: "Your current location!"
		            });
		 
		        });
				
				$('#input-coordinates').focus(function(){
					$(this).addClass('selected');
				});
				
				$('#input-coordinates').blur(function(){
					$(this).removeClass('selected');
				});
				
				function finalizeRenderMarkers () {
					if (renderAutofitmap)
					{
						var count=markers.length;
						if (count>1)
							mapMain.fitBounds(bounds);
						else if (count==1)
							mapMain.setCenter(markers[0].getPosition());
					}
				};
				
				clearMarkers = function () {
					var l=markers.length;
					for(var i=0;i<l;i++)
						markers[i].setMap(null);
					
					bounds=new google.maps.LatLngBounds();
				};
				
				posFromLatlng = function (latlng) {
					var temp=latlng.split(',');
					if (temp.length!=2 || isNaN(temp[0]) || isNaN(temp[1])) {temp=latlng.split('\t');}
					if (temp.length!=2 || isNaN(temp[0]) || isNaN(temp[1])) {temp=latlng.split(' ');}
					if (temp.length!=2 || isNaN(temp[0]) || isNaN(temp[1])) return false;
					
					var lat=parseFloat(temp[0]);
					var lng=parseFloat(temp[1]);
					return new google.maps.LatLng(lat,lng);
				};
				
				renderMarker = function (latlng,callback) {
					var position = posFromLatlng(latlng);
					if (!position) {if (callback) callback();return;}
					bounds.extend(position);
					
					var marker = new google.maps.Marker({
						position: position,
						map:mapMain,
						icon: pin2,
		                shadow: pinShadow,
						title: "Damage!"
					});
					
					markers.push(marker);
					if (callback) callback(marker);
				};
				
				
				renderMarkers = function () {
					clearMarkers();
					var coords=getInputCoordinates();
					if (coords=='') return;
					
					coords=coords.split('\n');
					var i=0,l=coords.length,rendered=0;
					
					for(i=0;i<l;i++)
						eval("setTimeout(function(){renderMarker('"+coords[i]+"',function(marker){rendered++;if (rendered==l) finalizeRenderMarkers();});},0);");
				};
				
				var searchinput=document.getElementById('searchinput');
				
				$(searchinput).keypress(function (evt) {
					var charCode = evt.charCode || evt.keyCode;
					if (charCode  == 13) { //Enter key's keycode
						return false;
					}
				});
				
				var searchAuto = new google.maps.places.Autocomplete(searchinput);
				
				google.maps.event.addListener(searchAuto, 'place_changed', function() {
					var place = searchAuto.getPlace();
					var pos=place.geometry.location;
					$(searchinput).attr('data-location',pos.lat()+','+pos.lng());
					$(searchinput).attr('data-value',$(searchinput).val());
				});
				searchAuto.bindTo('bounds', mapMain);
				
				$(searchinput).blur(function(){
					var value=$(this).val();
					if ($(this).attr('data-value')!==value)
					{
						$(this).attr('data-value',value);
						$(searchinput).removeAttr('data-location');
					}
				});
				
				$('#searchbutton').click(function(){
					var latlng=$(searchinput).attr('data-location');
					if (latlng && latlng!='')
					{
						if (!addSearchMarker)
						{
							var postion=posFromLatlng(latlng);
							if (postion) mapMain.setCenter(postion);
						}
						else
						{
							renderMarker(latlng,function(marker){
								var coords=getInputCoordinates();
								coords=coords.split('\n');
								coords.push(latlng);
								
								$('#input-coordinates').val(coords.join('\n'));
								
								mapMain.setCenter(marker.getPosition());
							});
						}
					}
					else 
					{
						var value=$(searchinput).val();
						if (value!='')
						{
							value=$.trim(value);
							if (value!='')
							{
								geocoder.geocode( { 'address': value}, function(results, status) {
									if (status != google.maps.GeocoderStatus.OK) return;
									var postion=results[0].geometry.location;
									if (!addSearchMarker)
										mapMain.setCenter(postion);
									else
									{
										latlng=postion.lat()+','+postion.lng();
										renderMarker(latlng,function(marker){
											var coords=getInputCoordinates();
											coords=coords.split('\n');
											coords.push(latlng);
											
											$('#input-coordinates').val(coords.join('\n'));
											
											mapMain.setCenter(marker.getPosition());
										});
									}
								});
							}
						}
					}
				});
				
				$('#input-coordinates').blur();
				
				renderMarkers ();
			});
		</script>
		<style type="text/css" >
			html {
				margin:0;
				padding:0;
				border:0;
				width:100%;
				height:100%;
			}
			
			body {
				margin:0;
				padding:0;
				border:0;
				width:100%;
				height:100%;
			}
			
			fieldset {
				margin: 0;
				padding: 0;
				border: 0;
			}
			.main-content {
				width:100%;
				height:100%;
			}
			
			#map-container {
				width:100%;
				height:100%;
			}
			
			#map-canvas {
				width:100%;
				height:100%;
			}
			
			.gbqfb-hvr {
				border-color: #2f5bb7;
			}
			
		</style>
	</head>
	<body>
	
	<a href="startpage.html">Back to mainpage</a>
	<%if(request.getAttribute("error") != null) {%>
		<h2>Something went wrong, please try again later.</h2><br><br>
	<% } else { %>
		<%LinkedList<OfflineBean> data = (LinkedList<OfflineBean>) request.getAttribute("data");
       	 	%>
	
		<div class="main-content">
			<div id="map-container">
				<div id="map-canvas"></div>
			</div>
			<div id="searchbar" class="searchbar">
				
				<div style="clear:both;"></div>
			</div>

			<div id="input-coordinates-container">
				<textarea id="input-coordinates" type="hidden" class="selected" style="display:none;"><%for(int i = 0; i<data.size(); i++){%>
        		<%=data.get(i).getLatitude()%>,<%=data.get(i).getLongitude()%><%}%></textarea>
				<input type="hidden" value="Regenerate" id="regenerate" onclick="renderMarkers (); return false;">
			</div>
		</div>
	<% } %>
	</body>
</html>