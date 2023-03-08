class Solution {
    public String solution(int[] food) {
        String a_start = "",a_end = "";
        for(int i = 1;i<food.length;i++){
            for(int j = 0;j<food[i]/2;j++){
                a_start += String.valueOf(i);
                a_end = String.valueOf(i) +a_end;
            }
        }
        
        return a_start+"0"+a_end;
    }
}