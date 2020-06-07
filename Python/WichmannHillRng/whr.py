# Seed the state
s1, s2, s3 = 100, 100, 100

#Wichmann-Hill Generator
def rng():
    global s1, s2, s3
    s1 = ( 171 * s1 ) % 30269
    s2 = ( 172 * s2 ) % 30307
    s3 = ( 170 * s3 ) % 30323
    return ( s1 / 30269.0 + s2 / 30307.0 + s3 / 30323.0 ) % 1.0

for i in range( 10 ):
    print( rng() )
