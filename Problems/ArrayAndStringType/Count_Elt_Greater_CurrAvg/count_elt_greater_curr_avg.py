def countResponseTimeRegressions(responseTimes):
    if len(responseTimes) <= 1: return 0
    
    count:int = 1
    total:int = responseTimes[0]
    to_ret:int = 0
    
    for i in range(1, len(responseTimes)):
        curr:int = responseTimes[i]
        total += curr
        count += 1
        curr_avg = total / count
        if curr > curr_avg:
            to_ret += 1
    
    return to_ret

if __name__ == "__main__":
    # Example test cases
    tests = [
        [100, 120, 80, 140, 130],
        [10, 20, 30, 40],
        [50, 40, 30, 20],
        [5],
        []
    ]

    for idx, arr in enumerate(tests):
        result = countResponseTimeRegressions(arr)
        print(f"Test Case {idx+1}")
        print(f"Input:  {arr}")
        print(f"Output: {result}")
        print("-" * 30)