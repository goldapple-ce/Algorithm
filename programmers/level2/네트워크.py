from collections import deque
def solution(maps):
    dir_type = {'U':(-1,0),'D':(1,0),'L':(0,-1),'R':(0,1)}
    row, col = 0,0
    max_row,max_col = len(maps),len(maps[0])
    queue = deque()
    queue.append((row,col))
    
    while queue:

        row,col = queue.popleft()
        for dir_row,dir_col in dir_type.values():
            post_row,post_col = row+dir_row,col+dir_col
            if -1<post_row<max_row and -1<post_col<max_col and maps[post_row][post_col] == 1:
                maps[post_row][post_col] = maps[row][col] + 1
                queue.append((post_row,post_col))
    
    return maps[-1][-1] if maps[-1][-1] > 1 else -1
