# N가지 종류의 화폐가 있다.
# 이 화폐들의 개수를 최소한으로 이용해서 그 가치의 합이 M원이 되도록 하려고 한다.
# 이이때 각 화폐는 몇 개라도 사용할 수 있으며, 사용한 화폐의 구성은 같지만 순서만 다른 것은 같은 경우로 구분한다.

# 입력 조건
# 첫째 줄에 N,M 이 주어진다.
# 이후 N개의 줄에는 각 화폐의 가치가 주어진다.

# 출력 조건
# 첫째 줄에 M원을 만들기 위한 최소한의 화폐 개수를 출력한다.
# 불가능할 떄는 -1을 출력한다.

N, M = map(int, input().split())
money_type = [int(input()) for _ in range(N)]

d = [10001]*(M+1)
d[0] = 0
for i in range(N):
    for j in range(money_type[i], M+1):
        d[j] = min(d[j], d[j-money_type[i]]+1)

print(d[M] if d[M] != 10001 else -1)
