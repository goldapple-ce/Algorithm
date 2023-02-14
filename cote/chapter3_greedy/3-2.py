N,M,K = map(int,input().split())
arr = list(map(int,input().split()))
arr.sort()
res,cnt = 0,0
# for _ in range(M):
#     if cnt < K:
#         res += arr[-1]
#         cnt += 1
#     else:
#         res += arr[-2]
#         cnt = 0
# print(res)

res = M//(K+1)*(arr[-1]*3+arr[-2])
res += M%(K+1)*(arr[-1])
print(res)