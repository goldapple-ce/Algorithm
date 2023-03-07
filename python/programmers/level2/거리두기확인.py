from collections import deque



def solution(places):
    answer = []
    max_row,max_col = 5,5

    for place in places:
        flag = False
        place = [list(row) for row in place]
        for row in range(max_row):
            for col in range(max_col):
                if place[row][col] == 'P':
                    if bfs(place,row,col):
                        flag = True
                        break
            if flag:
                break
        if flag:
            answer.append(0)
        else:
            answer.append(1)
        
            
    return answer

def bfs(place,r,c):
    max_row,max_col = 5,5
    dir_type = {'U':(-1,0),'D':(1,0),'L':(0,-1),'R':(0,1)}
    visited = [[False for _ in range(max_col)] for _ in range(max_row)]
    
    queue = deque()
    queue.append((r,c,0))
    place[r][c] = 'S'

    while queue:
        row,col,cnt = queue.popleft()
        if cnt > 2:
            continue
        if place[row][col] == 'P':
            return True
        visited[row][col] = True
        for dir_row,dir_col in dir_type.values():
            n_row,n_col = row+dir_row,col+dir_col
            if 0<=n_row<max_row and 0<=n_col<max_col and place[n_row][n_col] != 'X' and not visited[n_row][n_col]:
                queue.append((n_row,n_col,cnt+1))
    return False