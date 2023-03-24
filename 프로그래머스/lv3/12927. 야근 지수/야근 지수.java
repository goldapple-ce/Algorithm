import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Arrays;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works){
            queue.add(work);
        }
        for(int cnt = 0; cnt < n;cnt++){
            int work = queue.poll();
            if(work == 0) return 0;
            queue.add(work-1);
        }
        while(!queue.isEmpty()){
            answer += Math.pow(queue.poll(),2);
        }
        
        return answer;
    }
}