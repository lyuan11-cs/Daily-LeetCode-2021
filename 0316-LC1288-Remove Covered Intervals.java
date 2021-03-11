/*
Given a list of intervals, remove all intervals that are covered by another interval in the list.

Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

After doing so, return the number of remaining intervals.

 

Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
Example 2:

Input: intervals = [[1,4],[2,3]]
Output: 1
Example 3:

Input: intervals = [[0,10],[5,12]]
Output: 2
Example 4:

Input: intervals = [[3,10],[4,10],[5,11]]
Output: 2
Example 5:

Input: intervals = [[1,2],[1,4],[3,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= intervals[i][0] < intervals[i][1] <= 10^5
All the intervals are unique.
*/

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        if(intervals.length == 0)
        {
            return 0;
        }
        Arrays.sort(intervals, (int[] a,int[] b) -> a[0] == b[0]? b[1] - a[1]:a[0]-b[0]);
        int end = intervals[0][1];
        int count = 0;
        for(int i = 1; i < intervals.length;i++)
        {
            if(intervals[i][1] <= end)
            {
                count++;
            }else
            {
                end = intervals[i][1];
            }
        }
        return intervals.length - count;
    }
}

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals,(int[] a, int[] b) -> a[0] == b[0] ? b[1]-a[1]:a[0] - b[0]);
        
        int result = n;
        int start = intervals[0][1];
        for(int i = 1; i < n; i++)
        {
            if(intervals[i][1] <= start)
            {
                result--;
            }else
            {
                start = intervals[i][1];
            }    
        }
        return result;    
    }
}


Time complexity : (NlogN) since the sorting dominates the complexity of the algorithm.

Space complexity : O(N) or O(logN)

The space complexity of the sorting algorithm depends on the implementation of each program language.

For instance, the sorted() function in Python is implemented with the Timsort algorithm whose space complexity is O(N).

In Java, the Arrays.sort() is implemented as a variant of quicksort algorithm whose space complexity is O(logN).

