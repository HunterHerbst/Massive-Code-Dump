
iterations = int(input( "Enter the number of terms to use: " ))

i = 1
product = 2.0
while( i < iterations ):
    product *= ( 4 * i * i ) / ( ( 2 * i - 1 ) * ( 2 * i + 1 ) )
    i += 1

print( "Pi with ", iterations, "terms is ", product )
