def solution(n, costs):
    answer = 0
    uni = [0] * (n + 1)
    
    costs.sort(key = lambda x: x[2])
    print(costs)
    for i in range(1,n + 1):
        uni[i] = i
        
    def union(x,y):
        a = find(x)
        b = find(y)
        uni[a] = b
            
    def find(x):
        print("find",x,uni);
        if uni[x] == x: return x
        else:
            uni[x] = find(uni[x])
            return uni[x]
        
    for n1,n2,cost in costs:
        print("for",n1,n2,cost);
        if find(n1) != find(n2):
            print("union")
            union(n1,n2)
            answer += cost
    return answer