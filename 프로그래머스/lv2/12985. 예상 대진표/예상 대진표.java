import java.lang.Math;
class Solution{
    public int solution(int n, int a, int b){
        int answer = 1;
        
        while(Math.abs(a-b) != 1 || a/2 == b/2){
            a = (int)Math.ceil((double)a/2);
            b = (int)Math.ceil((double)b/2);
            answer++;
        }
        return answer;
    }
}