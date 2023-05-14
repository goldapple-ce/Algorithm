class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        for(String skillTree :skill_trees){
            int sI = 0;
            for(String st : skillTree.split("")){
                if(skill.indexOf(st) == -1){
                    continue;
                }else if(skill.indexOf(st) == sI){
                    sI++;
                }else{
                    answer--;
                    break;
                }
            }
        }
        
        
        return answer;
    }
}