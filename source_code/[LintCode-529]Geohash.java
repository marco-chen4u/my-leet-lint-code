/***
* LintCode 529. Geohash
Geohash is a hash function that convert a location coordinate pair into a base32 string.
Check how to generate geohash on wiki: Geohash or just google it for more details.
Try http://geohash.co/.
You task is converting a (latitude, longitude) pair into a geohash string.

Example1
    Input: 
        lat = 39.92816697 
        lng = 116.38954991
        precision = 12 
    Output: "wx4g0s8q3jf9"
Example2
    Input: 
        lat = -90
        lng = 180
        precision = 12 
    Output: "pbpbpbpbpbpb"

Notice
    1 <= precision <=12
***/
public class GeoHash {
    //constants
    private static final int SIZE = 60;
    private static final int STEP = 5;
    private static final char[] BASE_32_KEY_SET = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
						       'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 
						       'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
						       'y', 'z'};

    private static final int LATITUDE_START = -90;
    private static final int LATITUDE_END = 90;

    private static final int LONGITUDE_START = -180;
    private static final int LONGITUDE_END = 180;

    /*
     * @param latitude: one of a location coordinate pair 
     * @param longitude: one of a location coordinate pair 
     * @param precision: an integer between 1 to 12
     * @return: a base32 string
     */
    public String encode(double latitude, double longitude, int precision) { 
        String result = null;

        String longitudeBinStr = getBinaryStr(longitude, LONGITUDE_START, LONGITUDE_END);
        String latitudeBinStr = getBinaryStr(latitude, LATITUDE_START, LATITUDE_END);

        StringBuilder locationCode = new StringBuilder();
        for (int i = 0; i < SIZE / 2; i++) {
            locationCode.append(longitudeBinStr.charAt(i));
            locationCode.append(latitudeBinStr.charAt(i));
        }

        StringBuilder hashCode = new StringBuilder();
        for (int i = 0; i < SIZE; i += STEP) {
            String codeSegment = locationCode.substring(i, i + STEP);
            int index = Integer.parseInt(codeSegment, 2);
            hashCode.append(BASE_32_KEY_SET[index]);
        }

        precision = Math.min(hashCode.length(), precision);
        result = hashCode.substring(0, precision);

        return result;
    }

    // helper methods
    private String getBinaryStr(double value, double start, double end) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < SIZE / 2; i++) {
            double mid = start + (end - start) / 2;

            char code = 'x';
            if (value > mid) {
                code = '1';
                start = mid;
            }
            else {
                code = '0';
                end = mid;
            }

            result.append(code);
        }

        return result.toString();
    }
}
