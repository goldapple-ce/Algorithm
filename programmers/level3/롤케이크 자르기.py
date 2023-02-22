from collections import Counter

def solution(topping):
    answer = 0
    bro_top = Counter(topping)
    my_top = set()
    
    for top_type in topping:
        bro_top[top_type] -= 1
        my_top.add(top_type)
        
        if bro_top[top_type] == 0:
            bro_top.pop(top_type)
        
        if len(my_top) == len(bro_top):
            answer += 1
        
        
    return answer