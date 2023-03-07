class Solution {
    public String solution(String s) {
        String answer = "";
        String[] s_arr = s.split("");
        int cnt = 0;
        for(String s_i : s_arr){
            cnt = s_i.contains(" ") ?0 :cnt +1;
            answer += cnt%2==1 ?s_i.toUpperCase() : s_i.toLowerCase();
        }
        
        return answer;
    }
}