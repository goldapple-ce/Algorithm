class Solution {
    public int[] solution(int brown, int yellow) {
        brown = (brown-4)/2;
        for(int height =1;height<=brown/2;height++){
           if(height*(brown-height) == yellow){
               return new int[] {brown-height+2, height+2};
           }
        }
        return new int[] {};
    }
}