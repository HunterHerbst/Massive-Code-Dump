
def next_prime( prime_arr = [1] ):
    product = 1
    for p in prime_arr:
        product*=p

    new_arr = prime_arr + [ product + 1 ]
    return new_arr

primes_list = next_prime()

for i in range(10):
    print( primes_list[-1] )
    primes_list = next_prime( primes_list )

print( "Done. " )