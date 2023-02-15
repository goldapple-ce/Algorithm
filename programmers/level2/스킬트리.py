def solution(skill, skill_trees):
    answer = len(skill_trees)
    for skill_tree in skill_trees:
        temp = skill
        for alpha in skill_tree:
            if alpha in skill:
                if alpha != temp[0]:
                    answer -= 1
                    break
                temp = temp[1:]
            
    return answer