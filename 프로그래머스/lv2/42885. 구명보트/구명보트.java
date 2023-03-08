import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int left=0,right=people.length-1;
        Arrays.sort(people);
        for(;left<=right;right--){
            if (people[left]+people[right] <= limit) left++;
            answer++;
        }
        
        return answer;
    }
}