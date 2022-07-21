# 입력 조건
# 첫째 줄에 N과 K가 공백으로 구분되며 각자 자연수로 주어진다. 이때 입력으로 주어지는 N은 항상 K보다 크거나 같다.

# 출력 조건
# 첫째 줄에 N이 1이 될 때까지 1번 혹은 2번의 과정을 수행해야 하는 횟수의 최솟값을 출력한다.

N, K = map(int, input().split())

cnt = 0

while True:
    if N == 1:
        break

    if N % K == 0:
        N /= K
        cnt += 1
    else:
        N -= 1
        cnt += 1

print(cnt)
