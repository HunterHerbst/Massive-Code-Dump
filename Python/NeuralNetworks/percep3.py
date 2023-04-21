import tensorflow as tf
import numpy as np

def bin_to_arr(binStr):
    return[int(n) for n in binStr if n != ' ']

weights_path = '/weights/perceptron3/weights.h5'

input_data = np.array([bin_to_arr('1000 0000'),
                       bin_to_arr('0100 0000'),
                       bin_to_arr('0010 0000'),
                       bin_to_arr('0001 0000'),
                       bin_to_arr('0000 1000'),
                       bin_to_arr('0000 0100'),
                       bin_to_arr('0000 0010'),
                       bin_to_arr('0000 0001')])

expected_output = np.array([[1,2,8],
                            [0,6,4],
                            [0,3,2],
                            [0,1,6],
                            [0,0,8],
                            [0,0,4],
                            [0,0,2],
                            [0,0,1]])

prediction_data = np.array([bin_to_arr('1111 1111'),
                            bin_to_arr('1010 1010'),
                            bin_to_arr('0101 0101'),
                            bin_to_arr('1100 1100'),
                            bin_to_arr('0011 0011'),
                            bin_to_arr('0000 0000')])

model = tf.keras.models.Sequential([
    tf.keras.layers.Dense(16, activation='sigmoid', input_shape=(8,)),
    tf.keras.layers.Dense(8, activation='relu'),
    tf.keras.layers.Dense(3, activation='sigmoid')
])

model.compile(loss="categorical_crossentropy", optimizer='adam', metrics=['accuracy'])

model.fit(input_data, expected_output, epochs=100, batch_size=1)

inputPred = model.predict(input_data)
predictions = model.predict(prediction_data)

print(f'input data test:\n{inputPred}')
print(f'real test data:\n{predictions}')

model.summary()