/*
You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

A closed interval [a, b] (with a < b) denotes the set of real numbers x with a <= x <= b.

The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].


Example 1:
Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Example 2:

Input: firstList = [[1,3],[5,9]], secondList = []
Output: []
Example 3:

Input: firstList = [], secondList = [[4,8],[10,12]]
Output: []
Example 4:

Input: firstList = [[1,7]], secondList = [[3,10]]
Output: [[3,7]]
 

Constraints:

0 <= firstList.length, secondList.length <= 1000
firstList.length + secondList.length >= 1
0 <= starti < endi <= 10^9
endi < starti+1
0 <= startj < endj <= 10^9
endj < startj+1

*/
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int sizeA = A.length;
        int sizeB = B.length;
        
        int indexA = 0;
        int indexB = 0;
        List<int[]> result = new ArrayList<>();

        while(indexA < sizeA && indexB < sizeB)
        {
            int[] tempA = A[indexA];
            int[] tempB = B[indexB];
            int[] tempR = new int[2];
            
            if(tempA[0] > tempB[1])
            {
                indexB++;
            }else if(tempA[1] < tempB[0])
            {
                indexA++;
            }else{
                tempR[0] = Math.max(tempA[0],tempB[0]);
                tempR[1] = Math.min(tempA[1],tempB[1]);
                result.add(tempR);
                if(tempA[1] > tempB[1])
                {
                    indexB++;
                }else{
                    indexA++;
                }        
            }
        }
        int[][] ret = new int[result.size()][2];
        for(int i = 0; i < ret.length;i++)
        {
            ret[i] = result.get(i);
        }
        return ret;
    }
}

Solution 2: 
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<>();
        int i = 0, j = 0;
        while(i < A.length && j< B.length){
            int start = Math.max(A[i][0], B[j][0]);
            int end = Math.min(A[i][1], B[j][1]);
            if(start <= end) ans.add(new int[]{start, end});
            if(A[i][1]>B[j][1]) j++;
            else i++;
        }
        
        int[][] res = new int[ans.size()][2];
        i = 0;
        for(int[] pair: ans){
            res[i++] = pair;
        }
        
        return res;
    }
}