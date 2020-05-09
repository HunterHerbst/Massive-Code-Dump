import numpy as np
import matplotlib.pyplot as plt

class Neural_Network(object):
    
    ##This creates the Neural Network structure
    def __init__(self):
        #Define HyperParameters 
        #? (Essentially Parameters that 
        #? define the structuring of the 
        #? network)
        #! Network is unable to create new layers automatically
        self.inputLayerSize = 2
        self.outputLayerSize = 1
        self.hiddenLayerSize = 3

        #Weights (Parameters)
        self.w1 = np.random.randn(self.inputLayerSize, self.hiddenLayerSize)
        self.w2 = np.random.randn(self.hiddenLayerSize, self.outputLayerSize)

    ##This is what is used to predict information
    def forward(self, x):
        #Propagate inputs through network
        self.z2 = np.dot(x, self.w1)
        self.a2 = self.sigmoid(self.z2)
        self.z3 = np.dot(self.a2, self.w2)
        yHat = self.sigmoid(self.z3)
        return yHat

    ##This determines whether or not a neuron activates
    def sigmoid(self, z):
        #Apply sigmoid activation function
        return 1 / ( 1 + np.exp( -z ) )
    
    ##Cost function of the Network
    def costFunction(self, x, y):
        self.yHat = self.forward(x)
        J = 0.5 * sum( (y - self.yHat) ** 2)
        return J

    ##Cost function derivative used for gradient descent
    def costFunctionPrime(self, x, y):
        #compute derivative with respect to W1 and W2
        self.yHat = self.forward(x)

        delta3 = np.multiply( -( y - self.yHat ), self.sigmoidPrime( self.z3 ) )
        dJdW2 = np.dot( self.a2.T, delta3 )

        delta2 = np.dot( delta3, self.w2.T) * self.sigmoidPrime( self.z2 )
        dJdW1 = np.dot( x.T, delta2 )

        return dJdW1, dJdW2

    ##The derivative of the sigmoid activation function
    ##Used for gradient descent
    def sigmoidPrime(self, z):
        return np.exp( -z ) / ( ( 1 + np.exp( -z ) ) ** 2 )

    ##This is used to compute the gradients of the cost function
    def computeGradients(self, x, y):
        dJdW1, dJdW2 = self.costFunctionPrime(x, y)
        
        return np.concatenate( ( dJdW1, dJdW2 ) )

def computeNumericalGradient(N, x, y):
    paramsInitial = N.getParams()
    numgrad = np.zeros( paramsInitial.shape )
    perturb = np.zeros( paramsInitial.shape )
    e = 1e-4

    for p in range( len(paramsInitial) ):
        #Set perturbation vector
        perturb[p] = e
        N.setParams( paramsInitial + perturb )
        loss2 = N.costFunction( x, y )

        N.setParams( paramsInitial - perturb )
        loss1 = N.costFunction( x, y )

        #Compute Numerical Gradient:
        numgrad[p] = ( loss2 - loss1 ) / ( 2 * e )

        #Return the value we changed back to zero:
        perturb[p] = 0
    
    #Return Params to original value:
    N.setParams( paramsInitial )

    return numgrad



#*Start main functions

#*End main functions