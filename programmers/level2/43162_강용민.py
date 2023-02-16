def solution(n, computers):
    def dfs(row,col):
        if computers[row][col] == 0:
            return False
        if -1<row<n and -1<col<n:
            computers[row][col] = 0
            for i in range(n):
                dfs(i,col)
            dfs(col,row)
            return True
        return False
    
    answer = 0
    for row in range(n):
        for col in range(n):
            if dfs(row,col):
                answer +=1
    return answer

