/***
* LintCode 522. Tiny Url II
As a follow up for Tiny URL, we are going to support custom tiny url, 
so that user can create their own tiny url. 
That is to say, you need to implement one more createCustom than 232. Tiny Url.
You should implement three methods:
    -longToShort(url) Convert a long url to a short url which starts with http://tiny.url/.
    -shortToLong(url) Convert a short url to a long url.
    -createCustom(url, key) Set the short url of a long url to http://tiny.url/ + key
You can design any shorten algorithm, the judge only cares about:
    1.The length of short key' generated by longToShort should equal to 6 (without domain and slash). 
      And the acceptable characters are [a-zA-Z0-9]. For example: abcD9E
    2.No two long urls mapping to the same short url and no two short urls mapping to the same long url.

Example 1:
    Input:
        createCustom("http://www.lintcode.com/", "lccode")
        shortToLong("http://tiny.url/lccode")
        createCustom("http://www.lintcode.com/", "ltcode")
    Output:
        "http://tiny.url/lccode"
        "http://www.lintcode.com/"
        "error"

Example 2:
    Input:
        longToShort("http://www.lintcode.com/")
        createCustom("http://www.lintcode.com/", "ltcode")
    Output:
        "http://tiny.url/abcdef"    => This answer is not unique.
        "error"
    Explanation:
        Although it is almost impossible:
        if your longToShort() just returns "http://tiny.url/ltcode",  
        your createCustom() should return "http://tiny.url/ltcode".
***/
public class TinyUrl2 {
    // fields
    private static int GLOBAL_ID = 0;

    private Map<String, String> long2Short;
    private Map<String, String> short2Long;

    private char[] keyChars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
					  'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
					  'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private final int KEY_SIZE = 62;

    private final int SIZE = 6;

    private final String prefix = "http://tiny.url/";

    private final String ERROR = "error";

    // helper methods
    private String getGenerateShortUrl() {
        StringBuilder sb = new StringBuilder(prefix);

        int id = GLOBAL_ID;
        for (int i = 0; i < SIZE; i++) {
            sb.append(keyChars[id % KEY_SIZE]);
            id = id / KEY_SIZE;
        }

        GLOBAL_ID++;

        return sb.toString();
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    // constructor
    public TinyUrl2() {
        this.long2Short = new HashMap<String, String>();
        this.short2Long = new HashMap<String, String>();
    }

    /*
     * @param long_url: a long url
     * @param key: a short key
     * @return: a short url starts with http://tiny.url/
     */
    public String createCustom(String long_url, String key) {
        String shortKey = prefix + key;
        // check if already exists
        if (long2Short.containsKey(long_url)) {
            if (shortKey.equals(long2Short.get(long_url))) {
                return shortKey;
            }
            else {
                return ERROR;
            }
        }

        if (!short2Long.containsKey(shortKey)) {
            long2Short.put(long_url, shortKey);
            short2Long.put(shortKey, long_url);

            return shortKey;
        }

        return ERROR;
    }

    /*
     * @param long_url: a long url
     * @return: a short url starts with http://tiny.url/
     */
    public String longToShort(String long_url) {
        if (isEmpty(long_url)) {
            return ERROR;
        }

        // check if already exists
        if (long2Short.containsKey(long_url)) {
            return long2Short.get(long_url);
        }

        String shortUrl = getGenerateShortUrl();
        long2Short.put(long_url, shortUrl);
        short2Long.put(shortUrl, long_url);

        return shortUrl;
    }

    /*
     * @param short_url: a short url starts with http://tiny.url/
     * @return: a long url
     */
    public String shortToLong(String short_url) {
        if (isEmpty(short_url)) {
            return ERROR;
        }

        if (short2Long.containsKey(short_url)) {
            return short2Long.get(short_url);
        }

        return ERROR;
    }
}
