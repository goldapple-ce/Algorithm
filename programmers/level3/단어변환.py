from collections import deque

def solution(begin, target, words):
    len_words,len_word = len(words),len(words[0])
    visited = [False] * len_words
    queue = deque()
    queue.append((begin,0))

    while queue:
        word,length = queue.popleft()
        
        if word == target:
            return length
        
        for i in range(len_words):
            cnt = 0
            for j in range(len_word):
                if words[i][j] != word[j]:
                    cnt += 1
            if cnt == 1 and not visited[i]:
                visited[i] = True
                queue.append((words[i],length+1))
                
    return 0