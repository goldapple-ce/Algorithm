def solution(number, k):
    answer = []
    for num in number:
        while answer and answer[-1] < num and k > 0 :
            k -= 1
            answer.pop()
        answer.append(num)
    if k != 0:
        answer = answer[:-k]
    return ''.join(answer)