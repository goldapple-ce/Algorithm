# 문제
# 알파벳 소문자로 이루어진 단어를 가지고 아래와 같은 과정을 해 보려고 한다.

# 먼저 단어에서 임의의 두 부분을 골라서 단어를 쪼갠다. 즉, 주어진 단어를 세 개의 더 작은 단어로 나누는 것이다. 각각은 적어도 길이가 1 이상인 단어여야 한다. 이제 이렇게 나눈 세 개의 작은 단어들을 앞뒤를 뒤집고, 이를 다시 원래의 순서대로 합친다.

# 예를 들어,

# 단어 : arrested
# 세 단어로 나누기 : ar / rest / ed
# 각각 뒤집기 : ra / tser / de
# 합치기 : ratserde
# 단어가 주어지면, 이렇게 만들 수 있는 단어 중에서 사전순으로 가장 앞서는 단어를 출력하는 프로그램을 작성하시오.

# 입력
# 첫째 줄에 영어 소문자로 된 단어가 주어진다. 길이는 3 이상 50 이하이다.

# 출력
# 첫째 줄에 구하고자 하는 단어를 출력하면 된다.
import sys
S = list(sys.stdin.readline().rstrip())
res = []

for i in range(1, len(S)-1):
    for j in range(i+1, len(S)):
        first = S[:i]
        second = S[i:j]
        third = S[j:]

        first.reverse()
        second.reverse()
        third.reverse()

        res.append("".join(first+second+third))

print(min(res))
