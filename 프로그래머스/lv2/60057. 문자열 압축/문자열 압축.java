import java.util.*;

class Solution {
    public int solution(String s) {
        if(s.length() == 1) return 1;
        int answer = Integer.MAX_VALUE;

        for(int i = 1; i <= s.length()/2; i++){
            StringBuilder result = new StringBuilder();
            Stack<String> stack = new Stack<>();
            for(int j = 0; j <= s.length(); j += i){
                String target = s.substring(j,Math.min(j+i,s.length()));
                if(stack.size() != 0 && !target.equals(stack.peek())) {
                    if(stack.size() > 1) result.append(stack.size());
                    result.append(stack.pop());
                    stack.clear();
                };
                stack.push(target);
            }
            result.append(stack.pop());
            answer = Math.min(answer,result.length());
        }
        return answer;
    }
}