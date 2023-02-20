from math import ceil

answer = 0
prime_list = [0,1]

def solution(numbers):
    
    for i in range(len(numbers)-1):
        dfs(''.join(numbers[i]),numbers[:i]+numbers[i+1:])
    dfs(''.join(numbers[-1]),numbers[:-1])
    
    return answer

def dfs(num, numbers):
    global answer
    global prime_list
    
    if prime(int(num)):
        prime_list.append(int(num))
        answer += 1
        
    if not numbers:
        return
    
    for i in range(len(numbers)-1):
        dfs(num + str(numbers[i]),numbers[:i] + numbers[i+1:])
    dfs(num+str(numbers[-1]),numbers[:-1])

def prime(num):
    global prime_list
    if num in prime_list:
        return False
    
    for i in range(2,ceil(num/2)+1):
        if num % i == 0:
            return False
    return True