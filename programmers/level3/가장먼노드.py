from collections import deque

def solution(n, edge):
    answer = 0
    graph = [[] for _ in range(n+1)]
    for start,end in edge:
        graph[start].append(end)
        graph[end].append(start)

    dp = [n] * (n+1)
    dp[0],dp[1] = 0,1
    queue = deque()
    queue.append((1,1))
    
    while queue:
        start,dist = queue.popleft()

        for end in graph[start]:
            if dist+1 < dp[end]:
                queue.append((end,dist+1))
                dp[end] = dist+1

    
    return dp.count(max(dp)) 