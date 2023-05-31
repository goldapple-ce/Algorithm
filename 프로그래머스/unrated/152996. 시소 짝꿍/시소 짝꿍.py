from collections import Counter
def solution(weights):
    answer = 0
    people_counter = Counter(weights)
    for weight, num_of_people in people_counter.items():
        answer += num_of_people * (num_of_people - 1) // 2
        for dis1, dis2 in [(2, 3), (2, 4), (3, 4)]:
            answer += people_counter[weight * dis1 / dis2] * num_of_people
    return answer