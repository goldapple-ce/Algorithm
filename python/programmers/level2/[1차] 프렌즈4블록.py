def solution(m, n, board):
    answer = 0
    board = [[i for i in row] for row in board]
    while True:
        check = []
        
        #4블록 check
        for row in range(m-1):
            for col in range(n-1):
                if board[row][col] != 'X' and board[row][col] == board[row][col+1] == board[row+1][col] == board[row+1][col+1]:
                    check+=[(row,col),(row,col+1),(row+1,col),(row+1,col+1)]
        
        #같은 블록이 없다면 stop
        if len(check) == 0:
            break
        
        #4블록 'X'로 Change & 지워진 블럭 개수 세기
        for (row,col) in set(check):
            answer += 1
            board[row][col] = 'X'
        
        #board 정렬 
        for row in range(m-2,-1,-1):    #밑에서 위로 정렬 시작
            for col in range(n):
                if board[row][col] != 'X' and board[row+1][col] == 'X': #현재 블럭이 빈공간('X')이 아니고 아래가 빈공간이면
                    new_row = row
                    while new_row+1 < m and board[new_row+1][col] == 'X':  #빈공간이 아닐때까지 블럭 밑으로 내림
                        board[new_row][col],board[new_row+1][col] = board[new_row+1][col],board[new_row][col]
                        new_row += 1
    return answer