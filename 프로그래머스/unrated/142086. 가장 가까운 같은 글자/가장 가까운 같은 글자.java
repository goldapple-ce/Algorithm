import java.util.HashMap;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        HashMap<Character,Integer> counter = new HashMap<Character,Integer>();
        
        for(int i = 0;i<s.length();i++){
            char ch = s.charAt(i);
            int cnt = counter.getOrDefault(ch,-1);
            answer[i] = (cnt==-1)?-1 :i-cnt;
            counter.put(ch,i);
        }
        
        return answer;
    }
}