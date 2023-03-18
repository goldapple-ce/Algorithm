import java.util.PriorityQueue;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Iterator;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[]{0,0};
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        PriorityQueue<Integer> queue_reverse = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        for(String operation: operations){
            StringTokenizer st = new StringTokenizer(operation," ");
            String oper = st.nextToken();
            int value = Integer.parseInt(st.nextToken());
            if(oper.equals("I")){
                queue.add(value);
                queue_reverse.add(value);
            }else if(queue.size() > 0){
                if(value == 1){
                    int max = queue_reverse.poll();
                    queue.remove(max);
                }else{
                    int min = queue.poll();
                    queue_reverse.remove(min);
                }
            }
        }
        if(queue.size() > 0){
            answer[0] = queue_reverse.poll();
            answer[1] = queue.poll();
        }
        
        return answer;
    }
}