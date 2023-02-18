def solution(m, n, puddles):
    graph = [[1]*m for _ in range(n)]
    for puddle_col,puddle_row in puddles:
        if puddle_row == 1:
            for col in range(puddle_col,m):
                graph[puddle_row-1][col] =0
        if puddle_col == 1:
            for row in range(puddle_row,n):
                graph[row][puddle_col-1] = 0
        graph[puddle_row-1][puddle_col-1] = 0
    
    for row in range(1,n):
        for col in range(1,m):
            if graph[row][col] == 0: continue
            graph[row][col] = graph[row-1][col] + graph[row][col-1]

    return graph[-1][-1] % 1000000007