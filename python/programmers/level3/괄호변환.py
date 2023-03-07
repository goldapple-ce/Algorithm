def solution(p):
    if not p:
        return ''
    
    u,v = divide(p)
    
    if check(u):
        return u + solution(v)
    
    answer = '('
    answer += solution(v)
    answer += ')'
    
    for p in u[1:len(u) - 1]:
        if p == '(':
            answer += ')'
        else:
            answer += '('

    return answer

def divide(p):
    stack = 0
    for i in range(len(p)):
        if p[i] == '(':stack += 1
        else : stack -= 1
        if stack == 0 : return p[:i+1],p[i+1:]

def check(u):
    stack = []
    for i in range(len(u)):
        if u[i] == '(':
            stack.append('(')
        else:
            if not stack:
                return False
            stack.pop()
    return True