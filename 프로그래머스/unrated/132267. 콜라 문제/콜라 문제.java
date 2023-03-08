class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        while(a<=n){
            int temp = n/a;
            answer += temp*b;
            n %= a;
            n += temp*b;
        }
        return answer;
    }
}