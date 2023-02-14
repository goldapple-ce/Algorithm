def solution(records):
    answer = []
    dict_name = {}
    for record in records:
        status = record.split()
        # print(status)
        if status[0] == 'Enter':
            dict_name[status[1]] = status[2]
            answer.append(status[1]+'@님이 들어왔습니다.')
        elif status[0] == 'Leave':
            answer.append(status[1]+'@님이 나갔습니다.')
        else:
            dict_name[status[1]] = status[2]
    
    for i in range(len(answer)):
        idx,status = answer[i].split('@')
        answer[i] = dict_name[idx] + status
    
    return answer