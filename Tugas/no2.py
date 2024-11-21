#NO 2
def find_lis(arr):
    n = len(arr)
    dp = [1] * n  #LIS Length Initialization

    for i in range(1, n):
        for j in range(i):
            if arr[j] < arr[i]:
                dp[i] = max(dp[i], dp[j] + 1)

    #LIS Length maxumum searching
    max_length = max(dp)
    return max_length

#Example
input = [10, 22, 9, 33, 21, 50, 41, 60, 80]
result = find_lis(input)
print(f"Longest Increasing Subsequence (LIS) length: {result}")