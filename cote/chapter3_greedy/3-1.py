N = int(input())
result = 0
for money in [500,100,50,10]:
    result += N//money
    N %= money

print(result)
