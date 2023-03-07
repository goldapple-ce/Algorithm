# from collections import defaultdict
# import string

# def solution(msg):
#     answer = []
#     dictionary = defaultdict(int)
#     for index,alpha in enumerate(list(string.ascii_uppercase)):
#         dictionary[alpha] = index+1
#     i = 0
#     j = 1
#     while i < len(msg):
#         while True:
#             if j > len(msg):
#                 answer.append(dictionary[msg[i:j]])
#                 break
#             if msg[i:j] not in dictionary:
#                 answer.append(dictionary[msg[i:j-1]])
#                 dictionary[msg[i:j]] = max(dictionary.values())+1
#                 break
#             j+=1
#         i = j-1
#     return answer

def solution(msg):
    answer = []
    dict_alpha = {alpha : index for index,alpha in enumerate('_ABCDEFGHIJKLMNOPQRSTUVWXYZ')}

    while True:
        if msg in dict_alpha:
            answer.append(dict_alpha[msg])
            break
        for i in range(1,len(msg)+1):
            if msg[:i] not in dict_alpha:
                answer.append(dict_alpha[msg[:i-1]])
                dict_alpha[msg[:i]] = len(dict_alpha)
                msg = msg[i-1:]
                break
    return answer