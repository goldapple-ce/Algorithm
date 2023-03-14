import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int p: priorities) queue.add(p);
        Arrays.sort(priorities);
        
        while(!queue.isEmpty()){
            int ingre = queue.poll();
            if(ingre == priorities[priorities.length - answer-1]){
                answer++;
                location--;
                if(location < 0) break;
            }else{
                queue.add(ingre);
                location--;
                if(location < 0) location = queue.size()-1;
            }
            
        }
        
        return answer;
    }
}