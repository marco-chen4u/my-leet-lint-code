/***
* LintCode 530. Geohash II
This is a followup question for Geohash.
Convert a Geohash string to latitude and longitude.
Try http://geohash.co/.
Check how to generate geohash on wiki: Geohash or just google it for more details.

Example1
    Input: "wx4g0s"`
    Output: lat = 39.92706299 and lng = 116.39465332

Example2
    Input: "w"
    Output: lat = 22.50000000 and lng = 112.50000000
***/
public class GeoHash {
    // constants
    private static final int DEFAULT_VALUE = -1;
    private static final char[] BASE_32_KEY_SET = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
                                                              'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 
                                                              'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
                                                              'y', 'z'};
    private static final int[] mask = new int[] {16, 8, 4, 2, 1};

    // field
    private Map<Character, Integer> map;

    // constructor
    public GeoHash() {
        map = new HashMap<Character, Integer>();
        int index = 0;
        for (char ch : BASE_32_KEY_SET) {
            map.put(ch, index++);
        }
    }
	
    /*
     * @param geohash: geohash a base32 string
     * @return: latitude and longitude a location coordinate pair
     */
    public double[] decode(String geohash) {
        // corner case
	if (isEmpty(geohash)) {
            return new double[] {DEFAULT_VALUE, DEFAULT_VALUE};
        }
	
        double[] longitude = new double[] {-180, 180};
        double[] latitude = new double[] {-90, 90}; 		
        boolean swtichFlag = true;

        for (char ch : geohash.toCharArray()) {
            int value = map.get(ch);
            for (int i = 0; i < mask.length; i++) {
                if (swtichFlag) {
                    adjust(longitude, value & mask[i]);
                }
                else {
                    adjust(latitude, value & mask[i]);
                }

                swtichFlag ^= true;// flip the value
            }
        }

        return new double[] {(latitude[0] + latitude[1]) / 2.0, 
                                (longitude[0] + longitude[1]) / 2.0};
    }
	
    // helper methods
    private void adjust(double[] degrees, int digit) {
        if (digit > 0) {
            degrees[0] = (degrees[0] + degrees[1]) / 2.0;
        }
        else {
            degrees[1] = (degrees[0] + degrees[1]) / 2.0;
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
