/***
* LintCode 502. Mini Cassandra
Cassandra is a NoSQL database (a.k.a key-value storage). 
One individual data entry in cassandra constructed by 3 parts:
	-row_key. (a.k.a hash_key, partition key or sharding_key.)
	-column_key.
	-value
row_key is used to hash and can not support range query. Let's simplify this to a string.
column_key is sorted and support range query. Let's simplify this to integer.
value is a string. You can serialize any data into a string and store it in value.

Implement the following methods:
	-insert(row_key, column_key, value)
	-query(row_key, column_start, column_end) return a list of entries

Example 1:
    Input:
        insert("google", 1, "haha")
        query("google", 0, 1)
    Output: [(1, "haha")]
    
Example 2:
    Input:
        insert("google", 1, "haha")
        insert("lintcode", 1, "Good")
        insert("google", 2, "hehe")
        query("google", 0, 1)
        query("google", 0, 2)
        query("go", 0, 1)
        query("lintcode", 0, 10)
    Output:
        [(1, "haha")]
        [(1, "haha"),(2, "hehe")]
        []
        [(1, "Good")]
***/
/**
 * Definition of Column:
 * public class Column {
 *     public int key;
 *     public String value;
 *     public Column(int key, String value) {
 *         this.key = key;
 *         this.value = value;
 *    }
 * }
 */
public class MiniCassandra {
    // fields
    private final String DEFAULT_VALUE = "MiniCassandra";
    
    private Map<String, NavigableMap<Integer, Column>> rowToColumnMap;
    
    // constructor
    public MiniCassandra() {
        // do intialization if necessary
        rowToColumnMap = new HashMap<String, NavigableMap<Integer, Column>>();
    }
    
    // internal(helper) method
    private boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }

    /*
     * @param raw_key: a string
     * @param column_key: An integer
     * @param column_value: a string
     * @return: nothing
     */
    public void insert(String row_key, int column_key, String value) {
        // check corner case
        if (isEmptyStr(value)) {
            return;
        }
        
        // rowToColumnMap operation
        rowToColumnMap.putIfAbsent(row_key, new TreeMap<Integer, Column>());
        NavigableMap<Integer, Column> columnMap = rowToColumnMap.get(row_key);
        
        // columnMap operation
        columnMap.putIfAbsent(column_key, new Column(column_key, DEFAULT_VALUE));
        columnMap.get(column_key).value = value;
    }

    /*
     * @param row_key: a string
     * @param column_start: An integer
     * @param column_end: An integer
     * @return: a list of Columns
     */
    public List<Column> query(String row_key, int column_start, int column_end) {
        List<Column> result = new ArrayList<Column>();
        
        if (!rowToColumnMap.containsKey(row_key)) {
            return result;
        }
        
        NavigableMap<Integer, Column> columnMap = rowToColumnMap.get(row_key);
        columnMap = columnMap.subMap(column_start, true, column_end, true);
        for (Column value : columnMap.values()) {
            result.add(value);
        }
        
        return result;
    }
}
