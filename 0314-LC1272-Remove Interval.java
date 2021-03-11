/*
A set of real numbers can be represented as the union of several disjoint intervals, where each interval is in the form [a, b). A real number x is in the set if one of its intervals [a, b) contains x (i.e. a <= x < b).

You are given a sorted list of disjoint intervals intervals representing a set of real numbers as described above, where intervals[i] = [ai, bi] represents the interval [ai, bi). You are also given another interval toBeRemoved.

Return the set of real numbers with the interval toBeRemoved removed from intervals. In other words, return the set of real numbers such that every x in the set is in intervals but not in toBeRemoved. Your answer should be a sorted list of disjoint intervals as described above.

Example 1:

Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
Output: [[0,1],[6,7]]
Example 2:

Input: intervals = [[0,5]], toBeRemoved = [2,3]
Output: [[0,2],[3,5]]
Example 3:

Input: intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
Output: [[-5,-4],[-3,-2],[4,5],[8,9]]
 

Constraints:

1 <= intervals.length <= 10^4
-10^9 <= ai < bi <= 10^9
*/

class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        int left = toBeRemoved[0];
        int right = toBeRemoved[1];
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < intervals.length;i++)
        {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if(start < left)
            {
                if(end < left)
                {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(start);
                    arr.add(end);
                    result.add(arr);
                }else if(end > left && end <= right)
                {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(start);
                    arr.add(left);
                    result.add(arr);
                }else if(end > right)
                {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(start);
                    arr.add(left);
                    result.add(arr);
                    List<Integer> arr2 = new ArrayList<>();
                    arr2.add(right);
                    arr2.add(end);
                    result.add(arr2);
                }
            }else if(start >= left && start < right)
            {
                if(end <= right)
                {
                    continue;
                }else if(end > right)
                {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(right);
                    arr.add(end);
                    result.add(arr);
                }
            }else{
                List<Integer> arr = new ArrayList<>();
                arr.add(start);
                arr.add(end);
                result.add(arr);
            }    
        }
        return result;   
    }
}

Short Version: 

public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int[] in : intervals) {
            if(in[0] < toBeRemoved[0]) {
                ans.add(List.of(in[0], Math.min(toBeRemoved[0], in[1])));
            }
            if(toBeRemoved[1] < in[1]) {
                ans.add(List.of(Math.max(toBeRemoved[1], in[0]), in[1]));
            }
        }
        return ans;
    }

class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<intervals.length;i++)
        {
            int s = intervals[i][0];
            int e = intervals[i][1];
            if(s < toBeRemoved[0]) res.add(Arrays.asList(s, Math.min(toBeRemoved[0], e)));
            if(e > toBeRemoved[1]) res.add(Arrays.asList(Math.max(toBeRemoved[1], s), e));
        }
        return res;
    }
}