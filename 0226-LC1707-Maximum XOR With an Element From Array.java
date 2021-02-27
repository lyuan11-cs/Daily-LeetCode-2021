/*
You are given an array nums consisting of non-negative integers. You are also given a queries array, where queries[i] = [xi, mi].

The answer to the ith query is the maximum bitwise XOR value of xi and any element of nums that does not exceed mi. In other words, the answer is max(nums[j] XOR xi) for all j such that nums[j] <= mi. If all elements in nums are larger than mi, then the answer is -1.

Return an integer array answer where answer.length == queries.length and answer[i] is the answer to the ith query.

 

Example 1:

Input: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
Output: [3,3,7]
Explanation:
1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1 XOR 3 = 2. The larger of the two is 3.
2) 1 XOR 2 = 3.
3) 5 XOR 2 = 7.
Example 2:

Input: nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
Output: [15,-1,5]
 

Constraints:

1 <= nums.length, queries.length <= 105
queries[i].length == 2
0 <= nums[j], xi, mi <= 109

*/

class Solution {
    class TrieNode{
        public TrieNode[] children;
        public int prefixValue;
        public TrieNode(){
            children = new TrieNode[2];
        }
        
    }
    
    
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] result = new int[n];
        int[][] A = new int[n][3];
        for(int i = 0; i < n;i++)
        {
            A[i][0] = queries[i][0];
            A[i][1] = queries[i][1];
            A[i][2] = i;
        }
        
        Arrays.sort(nums);
        Arrays.sort(A,(int[] a,int[] b) -> (a[1] - b[1]));
        int index = 0;
        TrieNode root = new TrieNode();
        for(int i = 0; i < A.length;i++)
        {
            while(index < nums.length && nums[index] <= A[i][1])
            {
                insert(root,nums[index]);
                index++;
            }
            int tempAns = -1;
            if(index != 0)
            {
                tempAns = search(root,A[i][0]);
            }
            result[A[i][2]] = tempAns;
        }
        return result;
    }
    
    public void insert(TrieNode root,int n)
    {
        TrieNode cur = root;
        for(int i = 31;i >= 0; i--)
        {
            int bit = (n >> i) & 1;
            if(cur.children[bit] == null)
            {
                cur.children[bit] = new TrieNode();
            }
            cur = cur.children[bit];
        }
        cur.prefixValue = n;
    }
    
    public int search(TrieNode root,int n)
    {
        TrieNode cur = root;
        for(int i = 31;i >= 0;i--)
        {
            int bit = (n >> i) & 1;
            int toggleBit = bit == 1?0:1;
            if(cur.children[toggleBit] != null)
            {
                cur = cur.children[toggleBit];
            }else
            {
                cur = cur.children[bit];
            }
        }
        return cur.prefixValue ^ n;
    }
}