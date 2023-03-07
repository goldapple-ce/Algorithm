def solution(sticker):
    answer = 0
    len_sticker = len(sticker)
    if len_sticker == 1 : return sticker[0]
    dp1,dp2 = [0]*len_sticker,[0]*len_sticker
    dp1[0] = sticker[0]
    dp1[1] = sticker[0]
    dp2[1] = sticker[1]
    
    for i in range(2,len_sticker-1):
        dp1[i] = max(dp1[i-2]+sticker[i],dp1[i-1])
    for i in range(2,len_sticker):
        dp2[i] = max(dp2[i-2]+sticker[i],dp2[i-1])
    
    print(dp1)
    print(dp2)
    
    return max(max(dp1),max(dp2))