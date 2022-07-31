# 만들 수 없는 금액

# 동네 편의점의 주인인 동빈이는 N개의 동전을 이용하여 만들 수 없는 양의 정수 금액 중 최솟값을 구하는 프로그램을 작성하세요.

# 입력 조건
# 첫째 줄에는 동전의 개수를 나타내는 양의 정수 N이 주어집니다.
# 둘째 줄에는 각 동전의 화폐 단위를 나타내는 N개의 자연수가 주어지며, 각 자연수는 공백으로 구분합니다.

# 출력 조건
# 첫쨰 줄에 주어진 동전들로 만들 수 없는 양의 정수 금액 중 최솟값을 출력합니다.

N = int(input())
array = list(map(int, input().split()))
array.sort()

target = 1
for i in array:
    if target < i:
        break
    target += i

print(target)
