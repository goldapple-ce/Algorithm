def solution(book_time):
    answer = 0
    for idx,[start,end] in enumerate(book_time):
        temp_start = start.split(':')
        temp_end = end.split(':')    
        start = int(temp_start[0])*60 + int(temp_start[1])
        end = int(temp_end[0])*60 + int(temp_end[1])+10
        book_time[idx] = [start,end]
    book_time.sort(key=lambda x:x[1])
    
    for i in range(len(book_time)):
        temp_answer = 1
        for j in range(len(book_time)):
            if i == j:
                continue
            if book_time[j][0] < book_time[i][1] <= book_time[j][1]:
                temp_answer += 1
        if temp_answer > answer:
            answer = temp_answer

    return answer