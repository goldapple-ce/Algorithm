def solution(routes):
    answer = 0
    routes.sort(key=lambda route: route[1])
    len_routes = len(routes)
    idx = 0
    while idx < len_routes:
        temp = routes[idx][1]
        idx += 1
        while idx < len_routes and routes[idx][0]<=temp<=routes[idx][1]:
            idx += 1
        answer += 1
        
    
    return answer