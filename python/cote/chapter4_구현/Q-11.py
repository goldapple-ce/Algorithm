from collections import deque

N = int(input())
K = int(input())

table = [[0]*(N+1) for _ in range(N+1)]

loc_apple = []
for _ in range(K):
    a, b = map(int, input().split())
    table[a][b] = 1

L = int(input())

order_lst = deque()
for _ in range(L):
    a, b = input().split()
    order_lst.append((int(a), b))

die = False
x, y = 1, 1
body = deque()
table[x][y] = 1
cnt = 0

# 상,우,하,좌
dir_type = {0: (-1, 0), 1: (0, 1), 2: (1, 0), 3: (0, -1)}
dir = 1

while die is False:

    # 전진
    x += dir_type[dir][0]
    y += dir_type[dir][1]

    # 시간흐름
    cnt += 1

    # 벽에 닿거나 자신의 몸에 부딫히면 죽음.
    if x < 1 or x > N or y < 1 or y > N:
        die = True
        break
    if (x, y) in body:
        die = True
        break

    body.append((x, y))

    if table[x][y] != 2:
        body.popleft()
    print(body)

    table[x][y] = 2

    # 방향 전환
    if cnt == order_lst[0][0]:
        order = order_lst.popleft()

        if order[1] == 'L':
            dir = (dir-1) % 4
        elif order[1] == 'D':
            dir = (dir-1) % 4

print(cnt)
