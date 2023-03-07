def solution(triangle):
    for row in range(1,len(triangle)):
        triangle[row][0] += triangle[row-1][0]
        triangle[row][-1] += triangle[row-1][-1]
        for col in range(1,row):
            triangle[row][col] += max(triangle[row-1][col],triangle[row-1][col-1])

    return max(triangle[-1])