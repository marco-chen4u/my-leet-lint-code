/***
* LintCode 566. GFS Client
Implement a simple client for GFS (Google File System, a distributed file system), it provides the following methods:
	1.read(filename). Read the file with given filename from GFS.
	2.write(filename, content). Write a file with given filename & content to GFS.
There are two private methods that already implemented in the base class:
	1.readChunk(filename, chunkIndex). Read a chunk from GFS.
	2.writeChunk(filename, chunkIndex, chunkData). Write a chunk to GFS.
To simplify this question, we can assume that the chunk size is chunkSize bytes. (In a real world system, it is 64M). The GFS Client's job is splitting a file into multiple chunks (if need) and save to the remote GFS server. chunkSize will be given in the constructor. You need to call these two private methods to implement read & write methods.

Example 
	GFSClient(5)
	read("a.txt")
	>> null
	write("a.txt", "World")
	>> You don't need to return anything, but you need to call writeChunk("a.txt", 0, "World") to write a 5 bytes chunk to GFS.
	read("a.txt")
	>> "World"
	write("b.txt", "111112222233")
	>> You need to save "11111" at chink 0, "22222" at chunk 1, "33" at chunk 2.
	write("b.txt", "aaaaabbbbb")
	read("b.txt")
	>> "aaaaabbbbb"
***/

/* Definition of BaseGFSClient
 * class BaseGFSClient {
 *     private Map<String, String> chunk_list;
 *     public BaseGFSClient() {}
 *     public String readChunk(String filename, int chunkIndex) {
 *         // Read a chunk from GFS
 *     }
 *     public void writeChunk(String filename, int chunkIndex,
 *                            String content) {
 *         // Write a chunk to GFS
 *     }
 * }
 */
public class GFSClient extends BaseGFSClient {
	//fields
	private final int CHUNK_SIZE;
	private Map<String, Integer> fileMap;//<fileName, fileChunkCount> key value pair
	
	// helper methods
	private boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
    /*
    * @param chunkSize: An integer
    */public GFSClient(int chunkSize) {
        // do intialization if necessary
		CHUNK_SIZE = chunkSize;
		this.fileMap = new HashMap<String, Integer>();//<fileName, fileChunkCount> key value pair
    }

    /*
     * @param filename: a file name
     * @return: content of the file given from GFS
     */
    public String read(String filename) {
		String result = null;
        if (!fileMap.containsKey(filename)) {
			return result;
		}
		
		int chunkCount = fileMap.get(filename);
		StringBuilder content = new StringBuilder();
		for (int index = 0; index < chunkCount; index++) {
			String currentChunkContent = readChunk(filename, index);
			if (!isEmpty(currentChunkContent)) {
				content.append(currentChunkContent);
			}
		}
		
		result = content.toString();
		
		return result;
    }

    /*
     * @param filename: a file name
     * @param content: a string
     * @return: nothing
     */
    public void write(String filename, String content) {
        if (isEmpty(filename)) {
			return;
		}
		
		int size = content.length();
		int chunkCount = (size - 1 )/ CHUNK_SIZE + 1;
		this.fileMap.put(filename, chunkCount);
		
		for (int index = 0; index < chunkCount; index++) {
			int start = index * CHUNK_SIZE;
			int end = (index < chunkCount - 1) ? (index + 1) * CHUNK_SIZE : size;
			
			String currentContent = content.substring(start, end);
			writeChunk(filename, index, currentContent);
		}
    }
}