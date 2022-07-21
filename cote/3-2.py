# 입력조건
# 첫째 줄에 N(2<= N <= 1,000), M(1<= M <= 10,000), K(1<=K<=10,000)의 자연수가 주어지며, 각 자연수는 공백으로 구분한다.
# 둘째 줄에 N개의 자연수가 주어진다. 각 자연수는 공백으로 구분한다. 단, 각각의 자연수는 1이상 10,000 이하의 수로 주어진다.
# 입력으로 주어지는 K는 항상 M보다 작거나 같다.

# 출력 조건
# 첫째 줄에 동빈이의 큰 수의 법치에 따라 더해진 답을 출력한다.

N, M, K = map(int, input().split())
lst = list(map(int, input().split()))
res, cnt = 0, 0
lst.sort()

# #시간복잡도 O(n)
# for i in range(M):
#     if cnt != K:
#         res += lst[-1]
#         cnt += 1
#     else:
#         res += lst[-2]
#         cnt = 0

# # 시간복잡도 O(1)
# cnt = M//(K+1) * K
# cnt += M % (K+1)

# res += cnt*lst[-1]
# res += (M-cnt)*lst[-2]

print(res)
