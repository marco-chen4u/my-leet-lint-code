/***
* LintCode 1070. Accounts Merge
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example
Example 1:
	Input:
	[
		["John", "johnsmith@mail.com", "john00@mail.com"],
		["John", "johnnybravo@mail.com"],
		["John", "johnsmith@mail.com", "john_newyork@mail.com"],
		["Mary", "mary@mail.com"]
	]
	
	Output: 
	[
		["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
		["John", "johnnybravo@mail.com"],
		["Mary", "mary@mail.com"]
	]

	Explanation: 
	The first and third John's are the same person as they have the common email "johnsmith@mail.com".
	The second John and Mary are different people as none of their email addresses are used by other accounts.

	You could return these lists in any order, for example the answer
	
	[
		['Mary', 'mary@mail.com'],
		['John', 'johnnybravo@mail.com'],
		['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']
	]
	is also acceptable.

Notice
	The length of accounts will be in the range [1, 1000].
	The length of accounts[i] will be in the range [1, 10].
	The length of accounts[i][j] will be in the range [1, 30].
***/

public class Solution {
    /**
     * @param accounts: List[List[str]]
     * @return: return a List[List[str]]
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<List<String>>();
        // check corner case
        if (accounts == null || accounts.isEmpty()) {
            return result;
        }
        
        //build an UnionFind to accept the account infomation
        UnionFind uf = new UnionFind(accounts);
        // connect the different node(email) relationship
        for (List<String> accountInfo : accounts) {
            int size = accountInfo.size();
            for (int i = 1; i < size - 1; i++) {
                uf.union(accountInfo.get(i), accountInfo.get(i + 1));
            }// for i
        }// for accounts
        
        Map<String, List<String>> emailComponents = uf.getEmailComponents();
        for (Map.Entry<String, List<String>> emailEntry : emailComponents.entrySet()) {
            List<String> accountInfo = new ArrayList<String>();
            List<String> emailList = emailEntry.getValue();
            String accountName = uf.getAccountName(emailEntry.getKey());
            //Collections.sort(emailList);
            accountInfo.addAll(emailList);//loading all email list in desired order
            accountInfo.add(0, accountName);// make sure account is in the first
            
            result.add(accountInfo);
        }
        
        return result;
    }
}

// helper class
class UnionFind {
    // fields
    Map<String, String> fathers;
    Map<String, String> accountMap;
    
    Map<String, List<String>> components;    
    Map<String, List<String>> accountEmailListMap;    
	
    // constructor
    public UnionFind(List<List<String>> accounts) {
        fathers = new TreeMap<String, String>();
        accountMap = new HashMap<String, String>();  
		
        for (List<String> accountInfo : accounts) {
            String accountName = accountInfo.get(0);
            int size = accountInfo.size();
            for (int i = 1; i < size; i++) {
                String email = accountInfo.get(i);
                fathers.put(email, email);
                accountMap.put(email, accountName);
            }// for i
        }// for accounts
    }
    
    // methods
    public String find(String x) {
        String superParent = fathers.get(x);        
        while (!superParent.equals(fathers.get(superParent))) {
            superParent = fathers.get(superParent);
        }
        
        String tmp = null;
        String parent = x;
        while (!parent.equals(fathers.get(parent))) {
            tmp = fathers.get(parent);
            fathers.put(parent, superParent);
            accountMap.put(parent, accountMap.get(superParent));
            parent = tmp;
        }
        
        return superParent;
    }
    
    public void union(String x, String y) {
        String parentX = find(x);
        String parentY = find(y);        
        if (!parentX.equals(parentY)) {
            fathers.put(parentX, parentY);
            accountMap.put(parentX, accountMap.get(parentY));
        }
    }
    
    public Map<String, List<String>> getEmailComponents() {
        if (components != null && !components.isEmpty()) {
            return components;
        }
        
        if (components == null) {
            components = new HashMap<String, List<String>>();
        }        
        if (!components.isEmpty()) {
            components.clear();
        }
        
        for (String email : fathers.keySet()) {
            String parent = find(email);
            components.putIfAbsent(parent, new ArrayList<String>());
            
            List<String> emailList = components.get(parent);
            emailList.add(email);
        }
        
        return components;
    }
    
    public String getAccountName(String email) {
        return accountMap.get(email);
    }
}