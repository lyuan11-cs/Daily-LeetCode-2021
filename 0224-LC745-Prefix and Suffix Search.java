/*
Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 

Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 

Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.
*/

class WordFilter {
    Trie trie;
    public WordFilter(String[] words) {
        trie = new Trie();
        for(int i = 0; i < words.length;i++)
        {
            String str = words[i];
            String suffix = "";
            
            for(int j = str.length() - 1;j >= 0;j--)
            {
                trie.insert(suffix+"{" + str,i);
                suffix = str.charAt(j) + suffix;
            }
            trie.insert(suffix + "{" + str,i);
        }
        
    }
    
    public int f(String prefix, String suffix) {
        return trie.search(suffix + "{" + prefix);
    }
}

class TrieNode{
    public TrieNode[] children;
    public int weight;
    public TrieNode()
    {
        children = new TrieNode[27];
        weight = -1;
    }
    
}

class Trie{
    TrieNode root;
    public Trie(){
        root = new TrieNode();
    }
    public void insert(String word,int weight)
    {
        TrieNode cur = root;
        for(int i = 0; i < word.length();i++)
        {
            char c = word.charAt(i);
            if(cur.children[c-'a'] == null)
            {
                cur.children[c-'a'] = new TrieNode();
            }
            cur.children[c-'a'].weight = Math.max(weight, cur.children[c-'a'].weight);
            cur = cur.children[c-'a'];
        }
    }
    public int search(String suffix)
    {
        TrieNode cur = root;
        for(int i = 0;i < suffix.length();i++)
        {
            char c = suffix.charAt(i);
            if(cur.children[c-'a'] == null)
            {
                return -1;
            }
            cur = cur.children[c-'a'];
        }
        return cur.weight;
    }
    
    
    
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */