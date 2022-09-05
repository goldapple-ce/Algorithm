def solution(new_id):
    answer = []
    # 1단계
    new_id = new_id.lower()
    # 2단계
    new_id = [i for i in new_id if i.isalpha() or i.isdigit() or i in '-_.']

    # 3단계
    for c in new_id:
        if c == '.':
            if answer[-1:] != [c]:
                answer.append(c)
        else:
            answer.append(c)

    # 4단계
    if answer[:1] == ['.']:
        answer.pop(0)
    if answer[-1:] == ['.']:
        answer.pop(-1)

    # 5단게
    if answer == []:
        answer.append('a')

    # 6단계
    if len(answer) > 15:
        answer = answer[:15]
        if answer[-1] == '.':
            answer.pop(-1)

    # 7단계
    while len(answer) < 3:
        answer.append(answer[-1])

    answer = ''.join(answer)

    return answer


print(solution("abcdefghijklmn.p"))
