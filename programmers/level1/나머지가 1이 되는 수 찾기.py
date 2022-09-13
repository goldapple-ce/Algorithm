def solution(n):
    half = n//2+1
    answer = None

    for i in range(half, 0, -1):
        print(i)
        if n % i == 1:
            answer = i
            break

    if answer is None:
        answer = n-1

    return answer


print(solution(101))
