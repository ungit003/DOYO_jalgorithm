from collections import deque
import sys

input = sys.stdin.readline

T = int(input())

for _ in range(T):
    p = input().rstrip()
    n = int(input())
    arr = input().rstrip()[1:-1].split(',')
    
    if n == 0:
        d = deque()
    else:
        d = deque(arr)
    
    rev = False
    error = False
    
    for func in p:
        if func == 'R':
            rev = not rev
        elif func == 'D':
            if len(d) == 0:
                error = True
                break
            if rev:
                d.pop()
            else:
                d.popleft()
    
    if error:
        print("error")
    else:
        if rev:
            d.reverse()
        print("[" + ",".join(d) + "]")
