/*

Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

 

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 10^4
0 <= starti < endi <= 10^6

*/
Solution 1 :
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }
}

Solution 2: 

public int minMeetingRooms(Interval[] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Interval itl : intervals) {
            map.put(itl.start, map.getOrDefault(itl.start, 0) + 1);
            map.put(itl.end, map.getOrDefault(itl.end, 0) - 1);
        }
        int room = 0, k = 0; 
        for (int v : map.values()) 
            k = Math.max(k, room += v); 
        
        return k; 
    }

Solution 3: 

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        
        if (intervals == null || intervals.length == 0)
            return 0;
        
        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(intervals.length, (int[] a, int[] b) -> a[1] - b[1]);
        
        pq.offer(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++)
        {
            int[] interval = pq.poll();
            if (intervals[i][0] >= interval[1])
                interval[1] = intervals[i][1];
            else
                pq.offer(intervals[i]);
            pq.offer(interval);
        }
        
        return pq.size();
    }
}

Solution 4: 

public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    static class Node {
        public int time;
        public int cost;

        public Node() {

        }
        // 时间，开始时间cost为1，结束时间cost为-1
        public Node(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }



    }
    //先按照时间升序，再按照cost升序排序
    public static Comparator<Node> cNode = new Comparator<Node>() {
        public int compare(Node o1, Node o2) {
            if(o1.time != o2.time) {
                return o1.time - o2.time;
            }
            return o1.cost - o2.cost;
        }
    };
    public int minMeetingRooms(List<Interval> intervals) {

        //扫描线数组
        List<Node>room = new ArrayList<Node>();
        for(int i = 0; i < intervals.size(); i++) {
            room.add(new Node(intervals.get(i).start, 1));
            room.add(new Node(intervals.get(i).end, -1));
        }
        //排序
        Collections.sort(room, cNode);
        int ans = 0;
        int tmp = 0;
        for(int i = 0; i < room.size(); i++) {
            tmp += room.get(i).cost;
            ans = Math.max(ans, tmp);
        }
        return ans;
    }
}
