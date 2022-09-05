def solution(lottos, win_nums):
    correct = [i for i in lottos if i in win_nums]

    max_correct = 7-(len(correct)+lottos.count(0))
    min_correct = 7-len(correct)

    return [max_correct if max_correct < 7 else 6, min_correct if min_correct < 7 else 6]


print(solution([0, 0, 0, 0, 0, 0], [31, 10, 45, 1, 6, 19]))
