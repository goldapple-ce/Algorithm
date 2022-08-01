# 알파벳 대문자와 숫자로만 구성된 문자열이 입력으로 주어집니다.
# 이때 모든 알파벳을 오름차순으로 정렬하여 이어서 출력한 뒤에, 그 뒤에 모든 숫자를 더한 값을 이어서 출력합니다.

# 입력 조건
# 첫쨰 줄에 하나의 문자열 S가 주어집니다.

# 출력 조건
# 첫째 줄에 문제에서 요구하는 정답을 출력합니다.
S = input()

num = 0
res = []
for i in S:
    if i.isalpha():
        res.append(i)
    else:
        num += int(i)
res.sort()

if num != 0:
    res.append(str(num))

print(''.join(res))
