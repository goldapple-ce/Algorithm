class Solution {
    public String solution(String s, int n) {
        char[] s_arr = s.toCharArray();
        for(int i = 0;i<s_arr.length;i++){
            if('A'<=s_arr[i] && s_arr[i]<='Z'){
                s_arr[i] = (char)((s_arr[i]+n-'A')%26 + 'A');
            }else if('a'<=s_arr[i] && s_arr[i]<='z'){
                s_arr[i] = (char)((s_arr[i]+n-'a')%26 + 'a');
            }
        }
        return String.valueOf(s_arr);
    }
}