import numpy as np, matplotlib.pyplot as plt, random as rd, os
learning_rate = 1 #learning rate
bias = 1 #bias value
weights = [rd.random(), rd.random(), rd.random(), rd.random(), rd.random()]

class Percep(object):

    def __init__(self):
        # HyperParameters
        self.inputLayerSize = 5
        self.outputLayerSize = 1
        self.learningRate = 1

        # Weights
        #? The connection between the first two layers
        #? Right now this is just input -> output
        self.w1 = np.random.randn(self.inputLayerSize, self.outputLayerSize)

    # x is the expected output, y is the calculated output
    def calculateError(self, x, y):
        return np.subtract(x, y)

    # x is a matrix of the inputs
    def calculate(self, x):
        output = np.dot(x, self.w1)
        return output

    def train(self, inp, out):
        outputP = self.calculate( inp )
        error = self.calculateError( out, outputP )

        for i in range( self.w1.size ):
            self.w1[i] += error[0] * inp[i] * self.learningRate

    # this is just to get a better looking output for printing
    def getOutput(self, x):
        output = self.calculate(x)
        return (int)(output[0])

##Start of executed code

net = Percep()

for i in range(5):
    net.train([0, 0, 0, 0, 0], [0])
    net.train([0, 0, 0, 0, 1], [1])
    net.train([0, 0, 0, 1, 0], [2])
    net.train([0, 0, 1, 0, 0], [4])
    net.train([0, 1, 0, 0, 0], [8])
    net.train([1, 0, 0, 0, 0], [16])

print( net.getOutput([1, 1, 0, 0, 0]) )