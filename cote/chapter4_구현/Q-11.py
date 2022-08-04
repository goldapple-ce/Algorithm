N = int(input())
K = int(input())

table = [[0]*(N+1) for _ in range(N+1)]

loc_apple = []
for _ in range(K):
    a, b = map(int, input().split())
    table[a][b] = 2

L = int(input())

order_lst = []
for _ in range(L):
    a, b = input.split()
    order_lst.append((int(a), b))

die = False
x, y = 0, 0
table[x][y] = 1
cnt = 0

# 상,우,하,좌
dir_type = {0: (-1, 0), 1: (0, 1), 2: (1, 0), 3: (0, -1)}
dir = 1

while die is False:

    # 전진
    x, y += dir_type[dir]
    table[x][y] = 1

    # 시간흐름
    cnt += 1

    # 벽에 닿거나 자신의 몸에 부딫히면 죽음.
    if x < 1 or x > N or y < 1 or y > N or table[x][y] == 1:
        die = True

    # 방향 전환
    for x in order_lst:
        if cnt == x[0]:
            if x[1] == 'L':
                dir -= 1
                if dir == -1:
                    dir = 3
            elif x[1] == 'D':
                dir += 1
                if dir == 4:
                    dir = 0
