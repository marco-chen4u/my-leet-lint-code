/***
* LintCode 1070. Accounts Merge
Given a list accounts, each element accounts[i] is a list of strings, 
where the first element accounts[i][0] is a name, 
and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. 
Two accounts definitely belong to the same person if there is some email that is common to both accounts. 
Note that even if two accounts have the same name, 
they may belong to different people as people could have the same name. 
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, 
return the accounts in the following format: the first element of each account is the name, 
and the rest of the elements are emails in sorted order. 
The accounts themselves can be returned in any order.

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

//version-1: union find
public class Solution {
    /**
     * @param accounts: List[List[str]]
     * @return: return a List[List[str]]
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> results = new ArrayList<>();
        // corner case
        if (accounts == null || accounts.isEmpty()) {
            return results;
        }

        UnionFind unionFind = new UnionFind(accounts);

        for (List<String> accountInfoList : accounts) {
            String accountName = accountInfoList.get(0);
            int size = accountInfoList.size();
            for (int i = 1; i < size - 1; i++) {
                String email1 = accountInfoList.get(i);
                String email2 = accountInfoList.size() > (i + 1) ? accountInfoList.get(i + 1) : null;
                if (isEmpty(email1) || isEmpty(email2)) {
                    continue;
                }
                unionFind.union(email1, email2);
            }
        }

        Map<String, List<String>> emailComponents = unionFind.getComponents();
        for (Map.Entry<String, List<String>> entry : emailComponents.entrySet()) {
            String accountName = unionFind.getAccountName(entry.getKey());
            List<String> emails = entry.getValue();
            
            List<String> accountInfo = new ArrayList<>(emails);
            accountInfo.add(0, accountName);
            
            results.add(accountInfo);
        }

        return results;
    }

    // helper method
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // inner class
    class UnionFind {
        // fields
        private Map<String, String> fathers;
        private Map<String, String> emailToAccountMap;

        private Map<String, List<String>> components;

        // constructor
        public UnionFind(List<List<String>> accounts) {
            fathers = new TreeMap<>();
            emailToAccountMap = new HashMap<>();

            for (List<String> accountInfoList : accounts) {
                int size = accountInfoList.size();
                String accountName = accountInfoList.get(0);
                for (int i = 1; i < size; i++) {
                    String email = accountInfoList.get(i);
                    fathers.put(email, email);
                    emailToAccountMap.put(email, accountName);
                }
            }

            components = new HashMap<>();
        }

        // methods
        public String find(String x) {
            String superParent = fathers.get(x);
            while (!superParent.equals(fathers.get(superParent))) {
                superParent = fathers.get(superParent);
            }

            String next = null;
            String parent = x;
            while (!parent.equals(fathers.get(parent))) {
                next = fathers.get(parent);

                // flattern the relationship
                fathers.put(parent, superParent);
                emailToAccountMap.put(parent, emailToAccountMap.get(superParent));

                // update for the next one
                parent = next;
            }

            return superParent;
        }

        public void union(String x, String y) {
            String parentX = find(x);
            String parentY = find(y);

            if (parentX.equals(parentY)) {
                return;
            }

            fathers.put(parentX, parentY);
            String accountName = emailToAccountMap.get(parentY);
            emailToAccountMap.put(parentX, accountName);
        }

        public Map<String, List<String>> getComponents() {
            if (!components.isEmpty()) {
                return components;
            }

            for (Map.Entry<String, String> entry : fathers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                String parent = find(value);
                
                components.putIfAbsent(parent, new ArrayList<>());

                components.get(parent).add(key);
            }

            return components;
        }

        public String getAccountName(String email) {
            return emailToAccountMap.get(email);
        }

        private void updateEmailAndAccount(String email, String accountName) {
            emailToAccountMap.put(email, accountName);
        }
    }
}
