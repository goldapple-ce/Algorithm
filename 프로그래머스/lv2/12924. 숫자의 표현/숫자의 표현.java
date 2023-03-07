class Solution {
    public int solution(int n) {
        if(n < 3) return 1;
        int answer = 0;
        int left = 1,right = 1,sum = 0;
        while (right <= (int)n/2 +1){
            sum = sum+right <= n ?sum+right++ : sum-left++;
            if(sum == n)answer++;
        }
        return answer+1;
    }
}