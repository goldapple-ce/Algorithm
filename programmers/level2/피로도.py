def solution(k, dungeons):
    answer = []
    
    def dfs(temp_k,temp_dungeons, cnt):
        temp_dungeons = [[require,consume] for require,consume in temp_dungeons if require <= temp_k ]
        if not temp_dungeons :
            answer.append(cnt)
            return
        for i in range(len(temp_dungeons)-1):
            dfs(temp_k-temp_dungeons[i][1],temp_dungeons[:i]+temp_dungeons[i+1:],cnt+1)
        dfs(temp_k-temp_dungeons[-1][1],temp_dungeons[:-1],cnt+1)
    
    for idx in range(len(dungeons)-1):
        dfs(k-dungeons[idx][1],dungeons[:idx]+dungeons[idx+1:],1)
    dfs(k-dungeons[-1][1],dungeons[:-1],1)
        
    return max(answer)