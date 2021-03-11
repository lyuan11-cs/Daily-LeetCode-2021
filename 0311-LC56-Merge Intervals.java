/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 

Constraints:

1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^4
*/
Solution 1: 
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
        {
            return new int[0][0];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for(int[] ins:intervals)
        {
            pq.offer(ins);
        }
        int[] first = pq.poll();
        int start = first[0];
        int end = first[1];
        List<int[]> res = new ArrayList<>();
        while(!pq.isEmpty())
        {
            int[] temp = pq.poll();
            if(end >= temp[0])
            {
                end = Math.max(end,temp[1]);
            }else
            {
                res.add(new int[]{start,end});
                start = temp[0];
                end = temp[1];
            }
        }
        res.add(new int[]{start,end});
        
        return res.toArray(new int[res.size()][2]);
    
    }
}

Solution 2: 

class Solution {
    public int[][] merge(int[][] intervals) {
        int length=intervals.length;
        if(length<=1)
            return intervals;
    
        int[] start = new int[length];
        int[] end = new int[length];
        for(int i=0;i<length;i++){
            start[i]=intervals[i][0];
            end[i]=intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int startIndex=0;
        int endIndex=0;
        List<int[]> result = new LinkedList<>();
        while(endIndex<length){
            //as endIndex==length-1 is evaluated first, start[endIndex+1] will never hit out of index
            if(endIndex==length-1 || start[endIndex+1]>end[endIndex]){
                result.add(new int[]{start[startIndex],end[endIndex]});
                startIndex=endIndex+1;
            }
            endIndex++;
        }
        return result.toArray(new int[result.size()][]);
    
    }
}

Solution 3: 
class Solution {
    public int[][] merge(int[][] intervals) {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int i = 0; i < intervals.length;i++)
        {
            int start = intervals[i][0];
            int end = intervals[i][1];
            map.put(start,map.getOrDefault(start,0) + 1);
            map.put(end,map.getOrDefault(end,0) - 1);
        }
        List<int[]> result = new ArrayList<>();
        int start = 0;
        int cnt = 0;
        for(Map.Entry<Integer,Integer> e:map.entrySet())
        {
            if(cnt == 0)
            {
                start = e.getKey();
            }
            if((cnt += e.getValue())== 0)
            {
                int[] arr = new int[2];
                // arr[0] = start;
                // arr[1] = e.getKey();
                // result.add(arr);
                result.add(new int[]{start,e.getKey()});
            }
        }
        // int[][] ret = new int[result.size()][2];
        // int index = 0;
        // for(int[] ar:result)
        // {
        //     ret[index++] = ar;
        // }
         return result.toArray(new int[result.size()][]);

    }
}