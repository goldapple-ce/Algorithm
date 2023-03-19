class Solution {
    static int answer;
    static int nums_len;
    
    public int solution(int[] numbers, int target) {
        // int answer = 0;
        answer = 0;
        nums_len = numbers.length - 1;
        dfs(numbers,target,0,numbers[0]);
        dfs(numbers,target,0,-numbers[0]);
        
        return answer;
    }
    
    public void dfs(int[] numbers,int target,int idx,int sum){
        if(idx == nums_len){
            if(sum == target){
                answer++;
            }
            return;
        }
        idx++;
        dfs(numbers,target,idx,sum+numbers[idx]);
        dfs(numbers,target,idx,sum-numbers[idx]);
    } 
}