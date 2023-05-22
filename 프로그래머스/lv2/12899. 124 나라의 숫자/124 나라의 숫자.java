class Solution {
    public String solution(int n) {
        String type = "412";
        StringBuilder sb = new StringBuilder();
        
        while(n>0){
            sb.insert(0,type.charAt(n%3));
            n = (n-1) /3;
        }
        
        return sb.toString();
    }
}