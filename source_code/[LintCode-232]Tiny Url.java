/***
* LintCode 232. Tiny Url
Given a long url, make it shorter.
You should implement two methods:
    -longToShort(url) Convert a long url to a short url which starts with http://tiny.url/.
    -shortToLong(url) Convert a short url to a long url.
You can design any shorten algorithm, the judge only cares about two things:
    1.The short key's length should equal to 6 (without domain and slash). And the acceptable characters are [a-zA-Z0-9]. For example: abcD9E
    2.No two long urls mapping to the same short url and no two short urls mapping to the same long url.
Example
    Example 1:
        Input: shortToLong(longToShort("http://www.lintcode.com/faq/?id=10"))
        Output: "http://www.lintcode.com/faq/?id=10"
        Explanation: 
            When longToShort() called, you can return any short url.
            For example, "http://tiny.url/abcdef".
            And "http://tiny.url/ABCDEF" is ok as well.
    Example 2:
        Input:
            shortToLong(longToShort("http://www.lintcode.com/faq/?id=10"))
            shortToLong(longToShort("http://www.lintcode.com/faq/?id=10"))
        Output:
            "http://www.lintcode.com/faq/?id=10"
            "http://www.lintcode.com/faq/?id=10"
***/

//version-1: Random Hash
public class TinyUrl {
    // fields
    private final SIZE = 6;
    private final int SCOPE = 62;
    private char[] keyChars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final String prefix = "http://tiny.url/";

    private Random random;	

    private Map<String, String> long2Short;
    private Map<String, String> short2Long;

    // constructor
    public TinyUrl() {
        this.long2Short = new HashMap<String, String>();
        this.short2Long = new HashMap<String, String>();
        this.random = new Random();
    }

    // helper methods
    private String generateShortUrl() {
        StringBuilder sb = new StringBuilder(prefix);
        int index = 0;

        for (int i = 0; i < SIZE; i++) {
            index = random.nextInt(SCOPE);
            sb.append(keyChars[index]);
        }

        return sb.toString();
    }

    /*
     * @param url: a long url
     * @return: a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        // check if this long url exists
        if (long2Short.containsKey(url)) {
            return long2Short.get(url);
        }

        String result = null;

        while (true) {
            result = generateShortUrl();

            if (long2Short.containsKey(result)) {
                continue;
            }

            long2Short.put(url, result);
            short2Long.put(result, url);

            break;		
        }

        return result;
    }

    /*
     * @param url: a short url starts with http://tiny.url/
     * @return: a long url
     */
    public String shortToLong(String url) {
        return short2Long.get(url);
    }
}

//version-2 base62
public class TinyUrl {
    //fields
    public static int GLOBAL_ID = 0;
    private final String prefix = "http://tiny.url/";

    private char[] keyChars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private Map<Character, Integer> dict;

    private final int KEY_SIZE = 62;

    private Map<Integer, String> id2url;
    private Map<String, Integer> url2id;;

    // helper methods
    private Map<Character, Integer> getInitializedDict() {
        Map<Character, Integer> result = new HashMap<Character, Integer>();
        int index = 0;
        for (char keyChar : keyChars) {
            result.put(keyChar, index++);
        }

        return result;
    }

    private boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }

    private String idToShortKey(int id) {
        String shortUrl = "";

        while (id > 0) {
            shortUrl = keyChars[id % KEY_SIZE] + shortUrl;

            id = id / KEY_SIZE;
        }

        while (shortUrl.length() < 6) {
            shortUrl = "0" + shortUrl;
        }

        return shortUrl;
    }

    private int shortKeyToId(String shortKey) {
        int id = 0;

        for (char ch : shortKey.toCharArray()) {
            id = id * KEY_SIZE + toBase62(ch);
        }

        return id;
    }

    private String getShortKey(String url) {
        return url.substring(prefix.length());
    }

    private int toBase62(char ch) {
        return dict.get(ch);
    }

    // constructor
    public TinyUrl() {
        this.id2url = new HashMap<Integer, String>();
        this.url2id = new HashMap<String, Integer>();
        this.dict = getInitializedDict();
    }

    /*
     * @param url: a long url
     * @return: a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        if (isEmptyStr(url)) {
            return null;
        }

        // check if already exists
        if (url2id.containsKey(url)) {
            return prefix + idToShortKey(url2id.get(url));
        }

        GLOBAL_ID++;

        url2id.put(url, GLOBAL_ID);
        id2url.put(GLOBAL_ID, url);

        return prefix + idToShortKey(GLOBAL_ID);
    }

    /*
     * @param url: a short url starts with http://tiny.url/
     * @return: a long url
     */
    public String shortToLong(String url) {
        if (isEmptyStr(url)) {
            return null;
        }

        String shortKey = getShortKey(url);
        int id = shortKeyToId(shortKey);

        return id2url.get(id);
    }
}
