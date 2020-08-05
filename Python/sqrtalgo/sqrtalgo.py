S = float(input('Enter any numberic value: '))
if S == 0:
    print('The square root of 0 is simply 0')
else:
    iterations = 1
    guess = float(input('Enter an initial guess: '))
    xold = guess
    isNegative = S < 0
    if isNegative:
        S *= -1
    xnew = 0.5 * (xold + S / xold)
    error = abs(xnew * xnew / S - 1)
    while error > 1e-9:
        xold = xnew
        xnew = 0.5 * (xold + S / xold)
        error = abs(xnew * xnew / S - 1)
        iterations += 1
    print('Algorithm completed in', iterations, 'iterations')
    if isNegative:
        S*=-1
    print('The square root of', S, 'is', xnew, ('i' if isNegative else''))
