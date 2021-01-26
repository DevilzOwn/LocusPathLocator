# LocusPathLocator
You are given two locations - A & B. You are provided with latitude & longitude of them (short form LatLngs). You need to calculate ‘real’ points on the road that connects A & B. You need to use data from ​google directions API​.
Sample Input:
Latlngs for point A and point B.
Ex:

Sample Input:
A is 12.94523, 77.61896 B is 12.95944, 77.66085
Sample Output:
LatLngs at constant distance interval of 50 m between A & B on the road.
12.94523, 77.61896 12.94516, 77.61954 12.94487, 77.62004 .
.
.
12.9591, 77.65867 12.95944, 77.66085


Steps to run the test:
1. Input the source and destination lattitude and longitudes in "/LocusPathLocator/src/main/resources/input.json" 
2. Install maven and 
3. Run:
	`mvn clean install`
	to run the test.

Output would be displayed on the terminal.


Example: 

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running service.PathPlotterServiceTest
[{"lat":12.94514,"lng":77.6192},{"lat":12.94254,"lng":77.62273},{"lat":12.93991,"lng":77.62543},{"lat":12.9384,"lng":77.62889},{"lat":12.93855,"lng":77.63271},{"lat":12.93991,"lng":77.6362},{"lat":12.94573,"lng":77.63982},{"lat":12.94944,"lng":77.63934},{"lat":12.95395,"lng":77.64106},{"lat":12.95771,"lng":77.6413},{"lat":12.96135,"lng":77.64138},{"lat":12.96027,"lng":77.64502},{"lat":12.95977,"lng":77.64939},{"lat":12.95945,"lng":77.65366},{"lat":12.95925,"lng":77.65764}]
