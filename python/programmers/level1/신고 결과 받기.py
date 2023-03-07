from collections import defaultdict


def solution(id_list, report, k):
    answer = []
    report = list(set(report))
    lst = defaultdict(set)
    cnt = defaultdict(int)

    for content in report:
        reporter, target = content.split(' ')
        lst[reporter].add(target)
        cnt[target] += 1

    for id in id_list:
        res = 0
        for u in lst[id]:
            if cnt[u] >= k:
                res += 1
        answer.append(res)

    return answer

# lst = defaultdict(list)
# lst['l'].append('o')
# lst['l'].append('v')
# print(lst['l'], len(lst['l']))

# answer = []
# answer[1] = 2
# print(answer[1])
