import numpy, random as rd, os
learning_rate = 1 #learning rate
bias = 1 #bias value
weights = [rd.random(), rd.random(), rd.random(), rd.random(), rd.random()]

def Perceptron(inp1, inp2, inp3, inp4, output):
    outputP = inp1 * weights[0] + inp2 * weights[1] + inp3 * weights[2] + inp4 * weights[3] + bias * weights[4]
    #outputP = 1/(1 + numpy.exp(-outputP))
    error = output - outputP
    weights[0] += error * inp1 * learning_rate
    weights[1] += error * inp2 * learning_rate
    weights[2] += error * inp3 * learning_rate
    weights[3] += error * inp4 * learning_rate
    weights[4] += error * bias * learning_rate

def calculate(a, b, c, d):
    output = a * weights[0] + b * weights[1] + c * weights[2] + d * weights[3] + bias * weights[4]
    return (int)(output)

print("Training network...")
for i in range(5):

    #Training data: The decimal value
    # of each single place in binary
    Perceptron(0, 0, 0, 0, 0)
    Perceptron(0, 0, 0, 1, 1)
    Perceptron(0, 0, 1, 0, 2)
    Perceptron(0, 1, 0, 0, 4)
    Perceptron(1, 0, 0, 0, 8)
    

print("Network trained")

for i in range(2):
    for j in range(2):
        for k in range(2):
            for l in range(2):
                print(str(i) + str(j) + str(k) + str(l), "=", calculate(i, j, k, l))
