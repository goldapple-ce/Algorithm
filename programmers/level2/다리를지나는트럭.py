from collections import deque

def solution(bridge_length, weight, truck_weights):
    answer = 0
    queue = deque([0]*bridge_length)
    truck_weights = deque(truck_weights)
    while truck_weights:
        answer += 1
        weight += queue.popleft()
        if weight >= truck_weights[0]:
            weight -= truck_weights[0]
            queue.append(truck_weights.popleft())
        else:
            queue.append(0)

    return answer+bridge_length