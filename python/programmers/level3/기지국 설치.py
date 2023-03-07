from math import ceil
def solution(n, stations, w):
    answer = 0
    req_list = []
    stations.insert(0,-1*w)
    stations.append(n+w+1)
    w *= 2
    
    for i in range(1,len(stations)):
        req = stations[i]-stations[i-1]-1-w
        if req <= 0:
            continue
        req_list.append(req)
    
    w += 1
    
    for req in req_list:
        answer += ceil(req/w)
    
    return answer