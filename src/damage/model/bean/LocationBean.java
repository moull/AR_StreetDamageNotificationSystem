package damage.model.bean;

public class LocationBean {
	
	private int id;
	private String street;
	private String num;
	private String city;
	private double longitude;
	private double latitude;
	private double altitude;
	private double accuracy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

}
