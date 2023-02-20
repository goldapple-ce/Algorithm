def solution(word):
    global answer 
    answer = 0
    alphabets = 'AEIOU'
    
    def dfs(string):
        global answer
        answer += 1
        if word == string:
            return True
        
        if len(string) == 5:
            return False
        
        for alpha in alphabets:
            if dfs(string+alpha) :
                return True
    
    for alpha in alphabets:
        if dfs(alpha):
            break
    return answer