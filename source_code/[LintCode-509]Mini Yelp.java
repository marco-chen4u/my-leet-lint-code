/***
* LintCode 509. Mini Yelp
Design a simple yelp system. Support the following features:
    1.Add a restaurant with name and location.
    2.Remove a restaurant with id.
    3.Search the nearby restaurants by given location.
A location is represented by latitude and longitude, both in double. 
A Location class is given in code.

Nearby is defined by distance smaller than k Km .

Restaurant class is already provided and you can directly call Restaurant.create() to create a new object. 
Also, a Helper class is provided to calculate the distance between two location, use Helper.get_distance(location1, location2).

A GeoHash class is provided to convert a location to a string. 
Try GeoHash.encode(location) to convert location to a geohash string and GeoHash.decode(string) to convert a string to location.

Example 1
    Input:
        addRestaurant("Lint Cafe", 10.4999999, 11.599999)
        addRestaurant("Code Cafe", 10.4999999, 11.512109)
        neighbors(10.5, 11.6, 6.7)
        removeRestaurant(1)
        neighbors(10.5, 9.6, 6.7)
    Output:
        1
        2
        ["Lint Cafe"]
        []
    Explanation:
        When add 2 restaurants, we return 1 and 2.
        When searching neighbors, first time we find Lint Cafe and the second time we find nothing.

Example 2
    Input:
        addRestaurant("Lint Cafe", 10.4999999, 11.599999)
        addRestaurant("Code Cafe", 10.4999999, 11.512109)
        neighbors(10.5, 11.6, 6.7)
        removeRestaurant(1)
        neighbors(10.5, 11.6, 6.7)
    Output:
        1
        2
        ["Lint Cafe"]
        []
    Explanation
        When searching neighbors the second time, the "Lint Cafe" has been removed.

Notice
    Press here to Learn about GeoHash
***/
/**
 * Definition of Location:
 * class Location {
 *     public double latitude, longitude;
 *     public static Location create(double lati, double longi) {
 *         // This will create a new location object
 *     }
 * };
 * Definition of Restaurant
 * class Restaurant {
 *     public int id;
 *     public String name;
 *     public Location location;
 *     public static Restaurant create(String name, Location location) {
 *         // This will create a new restaurant object,
 *         // and auto fill id
 *     }
 * };
 * Definition of Helper
 * class Helper {
 *     public static get_distance(Location location1, Location location2) {
 *         // return distance between location1 and location2.
 *     }
 * };
 * class GeoHash {
 *     public static String encode(Location location) {
 *         // return convert location to a GeoHash string
 *     }
 *     public static Location decode(String hashcode) {
 *          // return convert a GeoHash string to location
 *     }
 * };
 */
 
// helper class
class Node implements Comparable<Node> {
    // fields
    double distance;
    Restaurant restaurant;
    // constructor
    public Node(double distance, Restaurant restaurant) {
        this.distance = distance;
        this.restaurant = restaurant;
    }

    // methods
    @Override
    public int compareTo(Node other) {
        if (this.distance > other.distance) {
            return 1;
        }

        if (this.distance < other.distance) {
            return -1;
        }

        return 0;
    }
}

public class MiniYelp {
    private static final String END_OF_BASE_32_CODE = "}";
    private static final String SEPERATOR = ".";
    private static final double[] GEO_HASH_PRECISIONS = new double[] {2500, 630, 78, 20, 2.4, 0.61, 0.076, 0.019};// 8 precision radius
    private NavigableMap<String, Restaurant> restaurantMap;// <geohash-code, restaurant>
    private Map<Integer, String> idToHashCodeMap;// <restaurant_id, geohash-code>
	
    public MiniYelp() {
        // initialize your data structure here.
        this.idToHashCodeMap = new HashMap<Integer, String>();
        this.restaurantMap = new TreeMap<String, Restaurant>();
    }

    // @param name a string
    // @param location a Location
    // @return an integer, restaurant's id
    public int addRestaurant(String name, Location location) {
        Restaurant restaurant = Restaurant.create(name, location);
        String geohashCode = GeoHash.encode(location) + SEPERATOR + restaurant.id;

        this.restaurantMap.put(geohashCode, restaurant);
        this.idToHashCodeMap.put(restaurant.id, geohashCode);	

        return restaurant.id;
    }

    // @param restaurant_id an integer
    public void removeRestaurant(int restaurant_id) {
        if (!idToHashCodeMap.containsKey(restaurant_id)) {
            return;
        }

        String geohashCode = idToHashCodeMap.get(restaurant_id);

        idToHashCodeMap.remove(restaurant_id);
        restaurantMap.remove(geohashCode);
    }

    // @param location a Location
    // @param k an integer, distance smaller than k miles
    // @return a list of restaurant's name and sort by 
    // distance from near to far.
    public List<String> neighbors(Location location, double k) {
        List<String> result = new ArrayList<String>();

        int geohashLength = getGeohasLength(k);		
        String geohashCode = GeoHash.encode(location);
        String hashCode = geohashCode.substring(0, geohashLength);
        String hashCodeEnd = hashCode + END_OF_BASE_32_CODE;

        List<Node> nodes = new ArrayList<Node>();
        Map<String, Restaurant> restaurantOfRadius = null;
        restaurantOfRadius = restaurantMap.subMap(hashCode,true,hashCodeEnd,true);
        for (Restaurant currentRestaurant : restaurantOfRadius.values()) {
            double currentDistance = Helper.get_distance(location, currentRestaurant.location);
            if (currentDistance <= k) {
                nodes.add(new Node(currentDistance, currentRestaurant));
            }
        }

        if (nodes.isEmpty()) {
            return result;
        }

        Collections.sort(nodes);

        for (Node node : nodes) {
            result.add(node.restaurant.name);
        }

        return result;
    }

    // helper method
    private int getGeohasLength(double precision) {
        int size = GEO_HASH_PRECISIONS.length;
        for (int i = 0; i < size; i++) {
            if (precision > GEO_HASH_PRECISIONS[i]) {
                return i;
            }
        }

        return size;
    }
}
