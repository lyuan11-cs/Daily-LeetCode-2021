/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.
*/

class WordDictionary {

    class TrieNode{
        public TrieNode[] children;
        public boolean isWord;
        public TrieNode(){
            children = new TrieNode[26];
        }
        
    }
    public TrieNode root = new TrieNode();
    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    public void addWord(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length();i++)
        {
            char c = word.charAt(i);
            if(cur.children[c-'a'] == null)
            {
                cur.children[c-'a'] = new TrieNode();
            }
            cur = cur.children[c-'a'];
        }
        cur.isWord = true;
    }
    public boolean search(String word) {
        TrieNode cur = root;
        return helper(0,cur,word);
    }
    
    public boolean helper(int start,TrieNode cur,String word)
    {
        if(start == word.length())
        {
            if(cur.isWord)
            {
                return true;
            }else
            {
                return false;
            }
        }
        char c = word.charAt(start);
        if(c == '.')
        {
            for(int i = 0; i < 26;i++)
            {
                if(cur.children[i] != null)
                {
                    if(helper(start+1,cur.children[i],word))
                    {
                        return true;
                    }
                }
            }
        }else
        {
            if(cur.children[c-'a'] != null)
            {
                return helper(start+1,cur.children[c-'a'],word);
            }else
            {
                return false;
            }
        }
        return false;
        
    }
    
    
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */