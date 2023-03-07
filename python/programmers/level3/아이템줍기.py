from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = 0
    maxX = 0
    maxY = 0
    itemX *= 2
    itemY *= 2

    for x,y,x2,y2 in rectangle:
        maxX = max(x2 * 2,maxX)
        maxY = max(y2 * 2,maxY)

    graph = [[0] * (maxX + 2) for _ in range(maxY + 2)]

    for x,y,x2,y2 in rectangle:
        for i in range((x * 2),(x2 * 2) + 1):
            for j in range((y * 2),(y2 * 2) + 1):
                graph[j][i] = 1
    
    #전체 돌면서 주위 8개중에 하나가 0이면서 자기 자신이 1이면 2로 바꿈 테두리 경로
    for y in range(1,maxY + 1):
        for x in range(1,maxX + 1):
            for i in range(3):
                for j in range(3):
                    if graph[y + i - 1][x + j - 1] == 0 and graph[y][x] == 1:
                        graph[y][x] = 2
                        break
    
    dir_type = {'U':(0,1),'D':(0,-1),'L':(-1,0),'R':(1,0)}
    queue = deque()
    queue.append((characterX*2,characterY*2,0))
    while queue:
        characterX,characterY,answer = queue.popleft()
        
        if characterX == itemX and characterY == itemY:
            return answer//2
        
        graph[characterY][characterX] = 1
        for direct in 'UDLR':
            next_X = characterX + dir_type[direct][0]
            next_Y = characterY + dir_type[direct][1]
            if graph[next_Y][next_X] == 2:
                queue.append((next_X,next_Y,answer+1))

    return answer