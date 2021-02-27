/*
Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.

Follow up: Could you do this in O(n) runtime?

 

Example 1:

Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.
Example 2:

Input: nums = [0]
Output: 0
Example 3:

Input: nums = [2,4]
Output: 6
Example 4:

Input: nums = [8,10,2]
Output: 10
Example 5:

Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
Output: 127
 

Constraints:

1 <= nums.length <= 2 * 10^4
0 <= nums[i] <= 2^31 - 1
*/

class Solution {
    class TrieNode{
        public Map<Character,TrieNode> children;
        public TrieNode(){
            children = new HashMap<>();
        }
    }
    
    public int findMaximumXOR(int[] nums) {
        int max = nums[0];
        for(int i = 0; i < nums.length;i++)
        {
            if(nums[i] > max)
            {
                max = nums[i];
            }
        }
        int L = (Integer.toBinaryString(max)).length();
        int bitMask = 1 << L;
        String[] strNums = new String[nums.length];
        for(int i = 0; i < nums.length;i++)
        {
            strNums[i] =  (Integer.toBinaryString(nums[i] | bitMask)).substring(1);
        }
        int maxXor = 0;
        int currXor;
        TrieNode trie = new TrieNode();
        for(int i = 0; i < strNums.length;i++)
        {
            TrieNode node = trie;
            TrieNode xorNode = trie;
            currXor = 0;
            for(Character bit:strNums[i].toCharArray())
            {
                if(node.children.get(bit) == null)
                {
                    node.children.put(bit,new TrieNode());
                }
                node = node.children.get(bit);
                char toggleBit = bit == '1'?'0':'1';
                if(xorNode.children.get(toggleBit) == null)
                {
                    currXor = currXor << 1;
                    xorNode = xorNode.children.get(bit);
                }else
                {
                    currXor = (currXor << 1) | 1;
                    xorNode = xorNode.children.get(toggleBit);
                }
            }
           
            
            maxXor = Math.max(maxXor,currXor);
            
        }
        return maxXor;
    }
}

Solution 2: 

class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = nums[0];
        for(int i = 0; i < nums.length;i++)
        {
            if(nums[i] > max)
            {
                max = nums[i];
            }
        }
        
        int L = (Integer.toBinaryString(max)).length();
        Set<Integer> prefixes = new HashSet<>();
        int maxXor = 0;
        int currXor;
        for(int i = L - 1; i >= 0;i--)
        {
            maxXor <<= 1;
            currXor = maxXor | 1;
            prefixes.clear();
            
            for(int num:nums)
            {
                prefixes.add(num >> i);
            }
            for(int p:prefixes)
            {
                if(prefixes.contains(p ^ currXor))
                {
                    maxXor = currXor;
                    break;
                }
            }
            
            
        }
        
        return maxXor;
        
        
        
    }
}