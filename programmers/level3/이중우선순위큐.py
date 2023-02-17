import heapq

def solution(operations):
    answer = []
    for operation in operations:
        k, v = operation.split()
        v = int(v)
        if k =='I':
            heapq.heappush(answer,v)
        elif answer :
            
            if v == -1:
                del answer[0]
            else:
                del answer[answer.index(max(answer))]

    return [max(answer),answer[0]] if answer else [0,0]