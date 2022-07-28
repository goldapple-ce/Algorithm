import time
d = [0]*201


# 피보나치 함수(O(2^n))
def fibo(x):
    if x == 1 or x == 2:
        return 1
    return fibo(x-1)+fibo(x-2)


# 피보나치 탑다운 표현(O(n))
def fibo_topDown(x):
    if x == 1 or x == 2:
        return 1

    if d[x] != 0:
        return d[x]
    d[x] = fibo_topDown(x-1) + fibo_topDown(x-2)
    return d[x]


# 피보나치 바텀업 표현(O(n))
def fibo_bottomUp(x):
    global d
    d = [0]*201
    d[1] = 1
    d[2] = 1

    for i in range(3, x+1):
        d[i] = d[i-1]+d[i-2]

    return d[x]


start = time.time()
print(fibo(35))
print("피보나치 함수 코드 실행시간", time.time()-start)

start = time.time()
print(fibo_topDown(35))
print("피보나치 탑다운식 코드 실행 시간", time.time()-start)

start = time.time()
print(fibo_bottomUp(35))
print("피보나치 바텀업식 코드 실행 시간", time.time()-start)
