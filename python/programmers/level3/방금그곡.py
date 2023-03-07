from math import ceil

def solution(m, musicinfos):
    answer = None
    m = changeFlat(m)
    
    for musicinfo in musicinfos:
        start,end,name,music = musicinfo.split(',')
        start,end = list(map(int,start.split(':'))),list(map(int,end.split(':')))
        during = (end[0]*60 + end[1]) - (start[0] * 60 + start[1])
        music = changeFlat(music)
        music = music * ceil(during/len(music))
        music = music[:during]
        # print(m,music)
        if m in music:
            if answer == None or answer[0] < during or (answer[0] == during and answer[1] > start):
                    answer = (during, start, name)
    
    return answer[-1] if answer else "(None)"

def changeFlat(m):
    for alpha in 'CDFGA':
        m = m.replace(alpha+'#',alpha.lower())
    return m