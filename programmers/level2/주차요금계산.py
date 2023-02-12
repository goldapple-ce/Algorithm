from collections import defaultdict
from math import ceil

def solution(fees, records):
    answer = defaultdict(int)
    dict_record = {}
    for record in records:

        time,num, status = record.split(' ')
        time = list(map(int,(time.split(':'))))
        time = time[0]*60 + time[1]
        if status == 'IN':
            dict_record[num] = time
        else:
            answer[num] += time - dict_record[num]
            del dict_record[num]
            
    for num,time in dict_record.items():
        answer[num] += 1439 - time
    
    result = []
    
    for num,time in sorted(answer.items()):
        if time <= fees[0]:
            result.append(fees[1])
            continue
        result.append(fees[1]+ceil((time-fees[0])/fees[2])*fees[3])

    return result