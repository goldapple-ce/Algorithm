import java.util.HashMap;

class Solution {
    public int[] solution(String s) {
        char[] s_arr = s.toCharArray();
        int[] answer = new int[s_arr.length];
        HashMap<Character,Integer> counter = new HashMap<Character,Integer>();
        
        for(int i = 0;i<s_arr.length;i++){
            int cnt = counter.getOrDefault(s_arr[i],-1);
            answer[i] = (cnt==-1)?-1 :i-cnt;
            counter.put(s_arr[i],i);
        }
        
        return answer;
    }
}