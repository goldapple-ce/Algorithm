from collections import defaultdict
from itertools import combinations

def solution(orders, courses):
    answer = []
    
    for course in courses:
        menu_cnt = defaultdict(int)
        for order in orders:
            for course_menu in list(combinations(order,course)):
                menu_cnt[''.join(sorted(course_menu))] += 1
        answer+=maxCourse(menu_cnt)        
    
    return sorted(answer)

def maxCourse(menu_cnt):
    if not menu_cnt:
        print("no exist")
        return []
    max_cnt = max(menu_cnt.values())
    course = []
    for menu,cnt in menu_cnt.items():
        if cnt == 1:
            continue
        if cnt == max_cnt:
            course.append(menu)
    return course