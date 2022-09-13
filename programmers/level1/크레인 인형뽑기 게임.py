def solution(board, moves):
    answer = 0
    bagage = []

    for choice in moves:
        for i in range(len(board)):
            if board[i][choice-1] == 0:
                continue
            else:
                bagage.append(board[i][choice-1])
                board[i][choice-1] = 0
                break

        if len(bagage) > 1 and bagage[-1] == bagage[-2]:
            bagage.pop()
            bagage.pop()
            answer += 1

    return answer


print(solution([[0, 0, 0, 0, 0], [0, 0, 1, 0, 3], [0, 2, 5, 0, 1], [
      4, 2, 4, 4, 2], [3, 5, 1, 3, 1]], [1, 5, 3, 5, 1, 2, 1, 4]))
