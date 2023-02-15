def solution(dirs):
    answer = 0
    dir_type = {'U':(-1,0),'D':(1,0),'L':(0,-1),'R':(0,1)}
    row, col = 5,5
    channel = []
    
    for direct in dirs:
        pre_row,pre_col = dir_type[direct][0]+row, dir_type[direct][1]+col
        if -1<pre_row<11 and -1<pre_col<11:
            if [(row,col),(pre_row,pre_col)] not in channel:
                answer += 1
            channel.append([(row,col),(pre_row,pre_col)])
            channel.append([(pre_row,pre_col),(row,col)])
            row,col = pre_row,pre_col

    return answer