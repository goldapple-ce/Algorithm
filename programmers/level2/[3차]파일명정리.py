from collections import defaultdict
from string import ascii_uppercase
def solution(files):
    answer = []
    file_dict = defaultdict(list)
    for file in files:
        HEAD_idx,NUMBER_idx = 0,0
        len_file = len(file)
        for idx in range(len_file):
            if file[idx] in '1234567890':
                HEAD_idx = idx
                break
        for idx in range(HEAD_idx,len_file):
            if file[idx] not in '1234567890':
                NUMBER_idx = idx
                break
        if NUMBER_idx == 0:
            NUMBER_idx = len_file
            
        file_dict[file[:HEAD_idx].lower()].append((int(file[HEAD_idx:NUMBER_idx]),file))
    for HEAD,file in sorted(file_dict.items()):
        answer += [name for num,name in sorted(file,key = lambda x : x[0])]
    return answer