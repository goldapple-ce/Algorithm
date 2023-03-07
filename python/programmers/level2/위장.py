from collections import defaultdict
def solution(clothes):
    answer = 1
    kinds = defaultdict(int)
    for cloth,kind in clothes:
        kinds[kind] += 1
    for value in kinds.values():
        answer *= (value+1)

    return answer-1