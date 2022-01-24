/***
* LintCode 501. Design Twitter
Implement a simple twitter. Support the following method:
    1.postTweet(user_id, tweet_text). Post a tweet.
    2.getTimeline(user_id). 
        Get the given user's most recently 10 tweets posted by himself, 
        order by timestamp from most recent to least recent.
    3.getNewsFeed(user_id). 
        Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). 
        Order by timestamp from most recent to least recent.
    4.follow(from_user_id, to_user_id). 
        from_user_id followed to_user_id.
    5.unfollow(from_user_id, to_user_id). 
        from_user_id unfollowed to to_user_id.
Example
    Example 1
        Input:
            postTweet(1, "LintCode is Good!!!")
            getNewsFeed(1)
            getTimeline(1)
            follow(2, 1)
            getNewsFeed(2)
            unfollow(2, 1)
            getNewsFeed(2)
        Output:
            1
            [1]
            [1]
            [1]
            []
    Example 2
        Input:
            postTweet(1, "LintCode is Good!!!")
            getNewsFeed(1)
            getTimeline(1)
            follow(2, 1)
            postTweet(1, "LintCode is best!!!")
            getNewsFeed(2)
            unfollow(2, 1)
            getNewsFeed(2)
        Output:
            1
            [1]
            [1]
            2
            [2,1]
            []
***/

/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */
 // helper class
class Node implements Comparable<Node> {
    // fields
    int order;
    Tweet tweet;

    // constructor
    public Node(int order, Tweet tweet) {
        this.order = order;
        this.tweet = tweet;
    }

    // method
    @Override
    public int compareTo(Node other) {
        return other.order - this.order;//reverse order, the most recently
    }
}

public class MiniTwitter {    
    // fields
    private int order;
    private Map<Integer, Set<Integer>> friendship;
    private Map<Integer, List<Node>> userTweets;
    
    // constructor
    public MiniTwitter() {
        // do intialization if necessary
        this.order = 0;
        this.friendship = new HashMap<Integer, Set<Integer>>();
        this.userTweets = new HashMap<Integer, List<Node>>();
    }
    
    // internal(helper) methods
    private List<Node> getLastTen(List<Node> list) {
        if (list == null || list.isEmpty()) {
            return list;
        }
        
        int last = (list.size() > 10) ? 10 : list.size();
        int size = list.size();
        
        return list.subList(size - last, size);
    }
    
    private List<Node> getFirstTen(List<Node> list) {
        if (list == null || list.isEmpty()) {
            return list;
        }
        
        int last = (list.size() > 10) ? 10 : list.size();
        int size = list.size();
        
        return list.subList(0, last);
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        userTweets.putIfAbsent(user_id, new ArrayList<Node>());
        Tweet tweet = Tweet.create(user_id, tweet_text);
        
        order++;
        Node node = new Node(order, tweet);
        userTweets.get(user_id).add(node);
        
        return tweet;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {
        List<Tweet> result = new ArrayList<Tweet>();
        List<Node> list = new ArrayList<Node>();
        
        if (userTweets.containsKey(user_id)) {
            list.addAll(getLastTen(userTweets.get(user_id)));
        }

        if (friendship.containsKey(user_id)) {
            for (Integer currentFriend : friendship.get(user_id)) {
                List<Node> values = getLastTen(userTweets.get(currentFriend));
                if (values == null || values.isEmpty()) {
                    continue;
                }
                
                list.addAll(getLastTen(userTweets.get(currentFriend)));
            }
        }

        Collections.sort(list);
        
        list = getFirstTen(list);
        for (Node node : list) {
            result.add(node.tweet);
        }

        return result;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        List<Tweet> result = new ArrayList<Tweet>();
        List<Node> list = new ArrayList<Node>();
        
        if (userTweets.containsKey(user_id)) {
            List<Node> values = getLastTen(userTweets.get(user_id));
            if (values != null && !values.isEmpty()) {
                list.addAll(getLastTen(userTweets.get(user_id)));
            }
        }
        
        Collections.sort(list);
        list = getFirstTen(list);
        for (Node node : list) {
            result.add(node.tweet);
        }
        
        return result;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        friendship.putIfAbsent(from_user_id, new HashSet<Integer>());
        
        friendship.get(from_user_id).add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        if (!friendship.containsKey(from_user_id)) {
            return;
        }
        
        friendship.get(from_user_id).remove(to_user_id);
    }
}
