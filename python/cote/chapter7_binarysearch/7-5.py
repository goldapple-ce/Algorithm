# 동빈이네 전자 매장에는 부품이 N개 있다.
# 각 부품은 정수 형태의 고유한 번호가 있다.
# 어느날 손님이 M개 종류의 부품을 대량으로 구매하겠다며 당일 날 견적서를 요청했다.
# 이때 가게 안에 부품이 모두 있는지 확인하는 프로그램을 작성해보자.

# 입력조건
# 첫째 줄에 정수 N이 주어진다.
# 둘째 줄에는 공백으로 구분하여 N개의 정수가 주어진다.
# 셋째 줄에는 정수 M이 주어진다.
# 넷째 줄에는 공백으로 구분하여 M개의 정수가 주어진다.

# 출력 조건
# 첫째 줄에 공백으로 구분하여 각 부품이 존재하면 yes를, 없으면 no를 출력한다.

def binary_search(array, target, start, end):
    if start > end:
        return None
    mid = (start+end) // 2
    if array[mid] == target:
        return mid
    elif array[mid] > target:
        return binary_search(array, target, start, mid-1)
    else:
        return binary_search(array, target, mid+1, end)


def count_search(array, target):
    cnt = [0]*(max(array)+1)

    for i in array:
        cnt[i] += 1

    if cnt[target] == 0:
        return None
    else:
        return target


N = int(input())
store = list(map(int, input().split()))
store.sort()

M = int(input())
Est = list(map(int, input().split()))

for i in Est:
    # res = binary_search(store, i, 0, N-1)
    res = count_search(store, i)
    if res is None:
        print("No", end=' ')
    else:
        print("Yes", end=' ')
