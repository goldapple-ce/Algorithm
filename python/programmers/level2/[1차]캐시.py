def solution(cacheSize, cities):
    if cacheSize == 0:
        return len(cities)*5
    
    answer = 0
    cache = []
    
    for city in cities:
        city = city.lower()
        if city in cache:
            cache.pop(cache.index(city))
            cache.append(city)
            answer += 1
            continue
        
        if cacheSize <= len(cache):
            cache.pop(0)        
        cache.append(city)
        answer += 5

    return answer