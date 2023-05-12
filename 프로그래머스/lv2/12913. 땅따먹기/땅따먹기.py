def solution(land):
    for i in range(1,len(land)):
        for j in range(4):
            max_row = 0
            for pre_j in range(4):
                if j == pre_j:
                    continue
                if max_row < land[i-1][pre_j]:
                    max_row = land[i-1][pre_j]
            land[i][j] += max_row
    return max(land[-1])
    # for i in range(1,len(land)):
    #     for j in range(4):
    #         land[i][j] += max(land[i-1][:j]+land[i-1][j+1:])
    # return max(land[-1])