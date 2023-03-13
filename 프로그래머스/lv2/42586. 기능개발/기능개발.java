import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        int[] days = new int[progresses.length];
        for (int i = 0; i < progresses.length ; i++){
            days[i] = (int)Math.ceil((double)(100-progresses[i])/speeds[i]);
        }
        int fin_day = days[0];
        int fin_cnt = (fin_day == 100)?1 :0;
        for(int day : days){
            if(fin_day < day){
                answer.add(fin_cnt);
                fin_day = day;
                fin_cnt = 0;
            }
            fin_cnt++;
        }
        if(fin_cnt != 0) answer.add(fin_cnt);
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}