import java.util.Stack;
import java.util.HashMap;

class Solution {
    static HashMap<Character,Character> dict = new HashMap<Character,Character>();
    public int solution(String s) {
        int answer = 0;
        dict.put('{','}');dict.put('(',')');dict.put('[',']');
        for(int x = 0;x<s.length();x++){
            if(check(s)) answer++;
            s = s.substring(1) + s.charAt(0);
        }
        
        return answer;
    }
    public boolean check(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '['){
                stack.push(dict.get(s.charAt(i)));
            }else{
                if (stack.empty() || stack.peek() != s.charAt(i)) return false;
                stack.pop();
            }
        }
        return stack.empty();
    }
}