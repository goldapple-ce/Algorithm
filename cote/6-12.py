# 동빈이는 두 개의 배열 A와 B를 가지고 있다.
# 두 배열은 N개의 원소로 구성되어 있으며, 배열의 원소는 모두 자연수이다.
# 동빈이는 최대 K번의 바꿔치기를 할 수 있으며, 최종 목표는 배열 A의 모든 원소의 합이 최대가 되도록 하는 것이다.

# 입력 조건:
# 첫 번째 줄에 N,K가 공백으로 구분되어 입력된다.
# 두 번째 줄에 배열 A의 원소들이 공백으로 구분되어 입력된다.
# 세 번째 줄에 배열 B의 원소들이 공백으로 구분되어 입력된다.

# 출력 조건:
# 최대 K번의 바꿔치기 연산을 수행하여 만들 수 있는 배열 A의 모든 원소의 합의 최대값을 출력한다.

N, K = map(int, input().split())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

A.sort()
B.sort(reverse=True)

for i in range(K):
    if A[i] < B[i]:
        A[i], B[i] = B[i], A[i]
    else:
        break

print(sum(A))
