/*
Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.

The distance between two nodes is the number of edges on the path from one to the other.

 

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
Output: 3
Explanation: There are 3 edges between 5 and 0: 5-3-1-0.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
Output: 2
Explanation: There are 2 edges between 5 and 7: 5-2-7.
Example 3:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
Output: 0
Explanation: The distance between a node and itself is 0.
 

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
0 <= Node.val <= 10^9
All Node.val are unique.
p and q are values in the tree.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int findDistance(TreeNode root, int p, int q) {
        TreeNode LCA = LCA(root,p,q);
        return distance(LCA,p,0) + distance(LCA,q,0);
    }
    public int distance(TreeNode node,int val,int travel)
    {
        if(node == null)
        {
            return -1;
        }
        if(node.val == val)
        {
            return travel;
        }
        
        int left = distance(node.left,val,travel+1);
        int right = distance(node.right,val,travel+1);
        if(left == -1)
        {
            return right;
        }
        return left; 
    }
    
    public TreeNode LCA(TreeNode root,int p,int q)
    {
        if(root == null)
        {
            return null;
        }
        TreeNode left = LCA(root.left,p,q);
        TreeNode right = LCA(root.right,p,q);
        if(root.val == p || root.val == q)
        {
            return root;
        }
        if(left != null && right != null)
        {
            return root;
        }
        return left == null? right:left;
    }
    
}