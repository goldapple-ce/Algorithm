import java.util.Arrays;
class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        char[] t_arr = t.toCharArray();
        for(int i = 0; i<= t.length() - p.length();i++){
            String temp = String.valueOf(Arrays.copyOfRange(t_arr,i,i+p.length()));
            if(Long.parseLong(temp) <= Long.parseLong(p)) answer++;
        }
        
        return answer;
    }
}