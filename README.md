AR_StreetDamageNotificationSystem
=================================

This is the repository for the prototype of the AR seminar at the University of Helsinki.

The prototype contains of three different functions, the 'Report Damage' function, the 'Download Damage' function
and the 'Show data on the map' function. These will be presented in this subsection.

With the 'Report Damage' function the user of the system can report damage he found on the street.
The type and the degree of the damage can be specified and a description can be inserted. The location is
automatically provided by the system. It uses the Geolocation of the computer and shows the user's position on a map.
It was implemented in HTML5 with embedded JavaScript. The JavaScript contains functions which get the current position
of the user, set a marker on a map and forward them to a function that inserts it into the database if the user
confirms his location. If the system proposes a wrong position the user can insert the location or its coordinates
by manually. The coordinates of the user's position are stored in a table called 'location'. Before doing that it is
checked if the location already exists and only if it does not the new location is stored. Otherwise the ID of the
already existing location is used for the next steps. The ID of the location, the type, the degree and the description
of the damage are stored in the database in a table called 'damage'. If something goes wrong because the user did not
insert the data correctly, he will be informed.

The 'Download Damage' function downloads the existing damage in the database of the server into the private database
of the user. The user has to press a "Start" button. Then the data where the status is "broken" is loaded and this
data is inserted into the user's private database. Before inserting it the existing content is deleted so that there
would not be redundant data. Here three different functions are called. The first one deletes everything from the
offline database. The second one that loads the data and sends it to another function. This is storing it in the
user's database. If everything succeeded, the user is informed that everything was downloaded successfully. 

The last function is the 'Show Data on the Map' function which shows the data of the damage on the map next to
the current position (see Figure \ref{Fig8}). Before showing the data on the map the user should always update
his private database so that all repaired damage is removed from it and the new damage is downloaded. On the map
the user can see the current position (marked in blue) and the damage data (marked in red). The function behind it
is a simple query to the database where all the coordinates of the damage data is provided. This data is sent to a
function with HTML5 embedded JavaScript, that builds a map based on the Google Maps API and the
Geolocation API {http://www.w3.org/TR/geolocation-API/}. The Google Maps API is used for building the map, building
markers and showing the markers on the map and the Geolocation API is used for getting the current position of the
user. It is the same function used in the 'Report Damage' function described at the beginning of this section.
