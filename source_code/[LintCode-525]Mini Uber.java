/***
* LintCode 525. Mini Uber
Support two basic uber features:
	1.Drivers report their locations.
	2.Rider request a uber, return a matched driver.
When rider request a uber, match a closest available driver with him, then mark the driver not available.

When next time this matched driver report his location, return with the rider's information.

You can implement it with the following instructions:
	-report(driver_id, lat, lng)
		-return null if no matched rider.
		-return matched trip information.
	-request(rider_id, lat, lng)
		i.create a trip with rider's information.
		ii.find a closest driver, mark this driver not available.
		iii.fill driver_id into this trip.
		iv.return trip
This is the definition of Trip in Java:

public class Trip {
    public int id; // trip's id, primary key
    public int driver_id, rider_id; // foreign key
    public double lat, lng; // pick up location
}

Example
	Example 1:
		Input:
			report(1, 36.1344, 77.5672) 
			report(2, 45.1344, 76.5672) 
			request(2, 39.1344, 76.5672) 
			report(1, 38.1344, 75.5672) 
			report(2, 45.1344, 76.5672) 
		Output:
			null
			null
			Trip(rider_id: 2, driver_id: 1, lat: 39.1344, lng: 76.5672)
			Trip(rider_id: 2, driver_id: 1, lat: 39.1344, lng: 76.5672)
			null
	Example 2:
		Input:
			report(1, 36.1344, 77.5672)
			report(2, 45.1344, 76.5672)
			request(2, 39.1344, 76.5672)
			request(3, 78.1344, 134.5672)
		Output:
			null
			null
			LOG(INFO): Trip(rider_id: 2, driver_id: 1, lat: 39.1344, lng: 76.5672)
			LOG(INFO): Trip(rider_id: 3, driver_id: 2, lat: 78.1344, lng: 134.5672)
***/
/**
 * Definition of Trip:
 * public class Trip {
 *     public int id; // trip's id, primary key
 *     public int driver_id, rider_id; // foreign key
 *     public double lat, lng; // pick up location
 *     public Trip(int rider_id, double lat, double lng);
 * }
 * Definition of Helper
 * class Helper {
 *     public static double get_distance(double lat1, double lng1,
                                         double lat2, double lng2) {
 *         // return distance between (lat1, lng1) and (lat2, lng2)
 *     }
 * };
 */
public class MiniUber {
	// inner class
	class Location {
		// fields
		double latitude;
		double longitude;
		// constructor
		public Location(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}
	}
	
	// fields
	private final int UNKNOWN = - 1;
	private final int DEFAULT_VALUE = Integer.MAX_VALUE;	
	private Map<Integer, Trip> driver2Trip;   // trip table[---Dispatch Service---]
	private Map<Integer, Location> driver2Location;  // location table[---Goe Service---]

	// constructor
    public MiniUber() {
        // initialize your data structure here.
		this.driver2Trip = new HashMap<Integer, Trip>();
		this.driver2Location = new HashMap<Integer, Location>();
    }

    // @param driver_id an integer
    // @param lat, lng driver's location
    // return matched trip information if there have matched rider or null
    public Trip report(int driver_id, double lat, double lng) {
		Trip trip = null;//[---GEO service---]
        // check if ther current driver is already in business
		if (driver2Trip.containsKey(driver_id)) {
			trip = driver2Trip.get(driver_id);
			return trip;
		}
		
		// check if the driver's availability(already exists or just rookie to get joined)
		if (driver2Location.containsKey(driver_id)) {
			// update this driver's current location
			Location currentLocation = driver2Location.get(driver_id);
			currentLocation.latitude = lat;
			currentLocation.longitude = lng;//servide know's current driver's location
		}
		else {// a new rookie to join Uber
			Location location = new Location(lat, lng);
			driver2Location.put(driver_id, location);
		}
		
		return trip;
    }

    // @param rider_id an integer
    // @param lat, lng rider's location
    // return a trip
    public Trip request(int rider_id, double lat, double lng) {
        Trip trip = new Trip(rider_id, lat, lng);//[---Dispatch Service---]
		
		// assign a driver of the nearby scop of location(lat, lng) where rider make a request
		int driver_id = UNKNOWN;
		double distance = DEFAULT_VALUE;
		for (Map.Entry<Integer, Location> driverAndLocation : driver2Location.entrySet()) {
			Location current = driverAndLocation.getValue();
			double currentDistance = Helper.get_distance(current.latitude, current.longitude, 
															trip.lat, trip.lng);
			if (currentDistance < distance) {
				distance = currentDistance;
				driver_id = driverAndLocation.getKey();
			}
		}
		
		// if driver has already assigned
		if (driver_id != UNKNOWN){
			driver2Location.remove(driver_id);// marked as busy with business(not available)
			trip.driver_id = driver_id;// return driver info for the rider
			driver2Trip.put(driver_id, trip); // driver know's the customer(rider) information(system push message)
		}
		
		return trip;
    }
}