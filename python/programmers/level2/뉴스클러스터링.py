from collections import Counter
def solution(str1, str2):
    str1,str2 = str1.lower(),str2.lower()
    str1_list, str2_list = [],[]
    
    for i in range(len(str1)-1):
        if str1[i].isalpha() and str1[i+1].isalpha():
            str1_list.append(str1[i]+str1[i+1])
            
    for i in range(len(str2)-1):
        if str2[i].isalpha() and str2[i+1].isalpha():
            str2_list.append(str2[i]+str2[i+1])
            
    counter1 = Counter(str1_list)
    counter2 = Counter(str2_list)
    
    inter = list((counter1 & counter2).elements())
    union = list((counter1 | counter2).elements())
    
    return int(len(inter)/len(union) * 65536) if len(union)!= 0 else 65536