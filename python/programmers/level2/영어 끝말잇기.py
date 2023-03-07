def solution(n, words):
    p = [words[0][0]]
    for i, word in enumerate(words):
        if word not in p and p[-1][-1] == word[0]:
            p.append(word)
        else:
            return [i % n + 1, (i//n)+1]
    return [0, 0]