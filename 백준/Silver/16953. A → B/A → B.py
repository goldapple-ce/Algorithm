import sys
from collections import deque

input = sys.stdin.readline

a, b = map(int,input().split())

dq = deque()
dq.append((a,1))

answer = -1
while dq:
    now, cnt = dq.popleft()

    if now == b:
        answer = cnt
        break

    if now*2 <=b:
        dq.append((now*2,cnt+1))
    if now*10+1 <=b:
        dq.append((now*10+1,cnt+1))

print(answer)