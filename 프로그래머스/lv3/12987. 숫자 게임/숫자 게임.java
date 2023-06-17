import java.util.PriorityQueue;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        PriorityQueue<Integer> queueA = new PriorityQueue<>((o1,o2) ->{
            return o2-o1;
        });
        PriorityQueue<Integer> queueB = new PriorityQueue<>((o1,o2) ->{
            return o2-o1;
        });
        for(int i = 0 ; i < A.length; i++){
            queueA.offer(A[i]);
            queueB.offer(B[i]);
        }
        
        while(!queueA.isEmpty() && !queueB.isEmpty()){
            int ma = queueA.poll();
            int mb = queueB.poll();
            
            if(ma < mb) answer++;
            else{
                queueB.offer(mb);
            }
        }
        return answer;
    }
}