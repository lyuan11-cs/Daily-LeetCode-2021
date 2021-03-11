/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
Example 3:

Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]
Example 4:

Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]
Example 5:

Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]
 

Constraints:

0 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 10^5
intervals is sorted by intervals[i][0] in ascending order.
newInterval.length == 2
0 <= newInterval[0] <= newInterval[1] <= 10^5

*/

Template Solution : 
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int i = 0; i < intervals.length;i++)
        {
            int start = intervals[i][0];
            int end = intervals[i][1];
            map.put(start,map.getOrDefault(start,0)+1);
            map.put(end,map.getOrDefault(end,0) - 1);
        }
        int s = newInterval[0];
        int en = newInterval[1];
        map.put(s,map.getOrDefault(s,0) + 1);
        map.put(en,map.getOrDefault(en,0) - 1);
        List<int[]> list = new ArrayList<>();
        int cnt = 0;
        int str = 0;
        for(Map.Entry<Integer,Integer> e:map.entrySet())
        {
            if(cnt == 0)
            {
                str = e.getKey();
            }
            cnt += e.getValue();
            if(cnt == 0)
            {
                list.add(new int[]{str,e.getKey()});
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}

Solution 2: 

public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int[] toAdd = newInterval;
        
        for (int i = 0; i < intervals.length; i ++) {
			/*1. No overlap and toAdd appears before current interval, add toAdd to result.*/
            if (intervals[i][0] > toAdd[1]) {
                ans.add(toAdd);
                toAdd = intervals[i];
            }
            /*2. Has overlap, update the toAdd to the merged interval.*/
			else if (intervals[i][1] >= toAdd[0])  
                toAdd = new int[] {Math.min(intervals[i][0], toAdd[0]),
                                   Math.max(intervals[i][1], toAdd[1]) };
			/*3. No overlap and toAdd appears after current interval, add current interval to result.*/
            else ans.add(intervals[i]); 
        }
        ans.add(toAdd);
		return ans.toArray(new int[ans.size()][2]);
    }
