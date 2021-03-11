/*
You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

 

Example 1:
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

Example 2:

Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 

Constraints:

The number of nodes in the tree is in the range [2, 1000].
-2^31 <= Node.val <= 2^31 - 1
*/

Solution 1:
class Solution 
{
    TreeNode prev = null, first = null, second = null;
    public void recoverTree(TreeNode root) 
    {
        inorder(root);
        
        //swapping val part of nodes that were swapped by mistake to get the correct sequence
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
        
    void inorder(TreeNode root)
    {
        if(root == null)
            return;
        
        inorder(root.left);
        
        if(prev != null && root.val < prev.val)
        {
            if(first == null)
                first = prev;
            
            second = root;
        }
        prev = root;
        
        inorder(root.right);
    }
}




Solution 2: Morris 
class Solution {
  public void swap(TreeNode a, TreeNode b) {
    int tmp = a.val;
    a.val = b.val;
    b.val = tmp;
  }

  public void recoverTree(TreeNode root) {
    // predecessor is a Morris predecessor. 
    // In the 'loop' cases it could be equal to the node itself predecessor == root.
    // pred is a 'true' predecessor, 
    // the previous node in the inorder traversal.
    TreeNode x = null, y = null, pred = null, predecessor = null;

    while (root != null) {
      // If there is a left child
      // then compute the predecessor.
      // If there is no link predecessor.right = root --> set it.
      // If there is a link predecessor.right = root --> break it.
      if (root.left != null) {
        // Predecessor node is one step left 
        // and then right till you can.
        predecessor = root.left;
        while (predecessor.right != null && predecessor.right != root)
          predecessor = predecessor.right;

        // set link predecessor.right = root
        // and go to explore left subtree
        if (predecessor.right == null) {
          predecessor.right = root;
          root = root.left;
        }
        // break link predecessor.right = root
        // link is broken : time to change subtree and go right
        else {
          // check for the swapped nodes
          if (pred != null && root.val < pred.val) {
            y = root;
            if (x == null) x = pred;
          }
          pred = root;

          predecessor.right = null;
          root = root.right;
        }
      }
      // If there is no left child
      // then just go right.
      else {
        // check for the swapped nodes
        if (pred != null && root.val < pred.val) {
          y = root;
          if (x == null) x = pred;
        }
        pred = root;

        root = root.right;
      }
    }
    swap(x, y);
  }
}