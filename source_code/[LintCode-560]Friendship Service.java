/***
* LintCode 560. Friendship Service
Support follow & unfollow, getFollowers, getFollowings.
Example
	follow(1, 3)
	getFollowers(1) // return [3]
	getFollowings(3) // return [1]
	follow(2, 3)
	getFollowings(3) // return [1,2]
	unfollow(1, 3)
	getFollowings(3) // return [2]
***/
public class FriendshipService {
	// fields
	private Map<Integer, NavigableSet<Integer>> followers;
	private Map<Integer, NavigableSet<Integer>> followings;
	
	// constructor
    public FriendshipService() {
        this.followers = new HashMap<Integer, NavigableSet<Integer>>();
		this.followings = new HashMap<Integer, NavigableSet<Integer>>();
    }

    /*
     * @param user_id: An integer
     * @return: all followers and sort by user_id
     */
    public List<Integer> getFollowers(int user_id) {
        List<Integer> result = new ArrayList<Integer>();
		
		if (followers.containsKey(user_id)) {
			result.addAll(followers.get(user_id));
		}
		
		return result;
    }

    /*
     * @param user_id: An integer
     * @return: all followings and sort by user_id
     */
    public List<Integer> getFollowings(int user_id) {
        List<Integer> result = new ArrayList<Integer>();
		
		if (followings.containsKey(user_id)) {
			result.addAll(followings.get(user_id));
		}
		
		return result;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int to_user_id, int from_user_id) {
		// followers operation
        followers.putIfAbsent(to_user_id, new TreeSet<Integer>());
		followers.get(to_user_id).add(from_user_id);
		
		// followings operation
		followings.putIfAbsent(from_user_id, new TreeSet<Integer>());
		followings.get(from_user_id).add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int to_user_id, int from_user_id) {
        // followers operation
		if (followers.containsKey(to_user_id)) {
			followers.get(to_user_id).remove(from_user_id);
		}
		
		// followings operation
		if (followings.containsKey(from_user_id)) {
			followings.get(from_user_id).remove(to_user_id);
		}
    }
}