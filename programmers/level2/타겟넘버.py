from collections import deque

def solution(numbers, target):
    answer = 0
    queue = deque()
    n = len(numbers)
    queue.append([numbers[0],0])
    queue.append([-1*numbers[0],0])
    while queue:
        number,idx = queue.popleft()        
        idx += 1
        if idx < n:
            queue.append([number+numbers[idx],idx])
            queue.append([number-numbers[idx],idx])
        else:
            if target == number:
                answer += 1
    return answer