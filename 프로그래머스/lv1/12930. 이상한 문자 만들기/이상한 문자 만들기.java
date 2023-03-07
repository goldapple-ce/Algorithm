import java.util.Arrays;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] s_arr = s.split("");
        boolean flag = true;
        int idx = 0;
        for(int i = 0;i<s_arr.length;i++){
            if(s_arr[i].equals(" ")){
                flag = true;
                answer += s_arr[i];
                continue;
            }
            if(flag){
                idx = 0;
                flag = false;
            }
            if (idx % 2==0){
                answer += s_arr[i].toUpperCase();
            }else{
                answer += s_arr[i].toLowerCase();
            }
            idx++;
        }
        return answer;
    }
}