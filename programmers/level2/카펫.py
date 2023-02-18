def solution(brown, yellow):
    answer = []
    a = int((brown-4)/2)
    for i in range(1, int((a+1)/2+1)):
        if i*(a-i) == yellow:
            answer.append(a-i+2)
            answer.append(i+2)
            break
    return answer