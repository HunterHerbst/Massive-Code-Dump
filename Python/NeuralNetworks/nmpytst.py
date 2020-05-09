import numpy as np

mtx = np.array([[1, 2, 3], [4, 5, 6]])
mtx2 = np.array([[2, 1], [3, 4], [6, 5]])

mtx3 = np.dot(mtx, mtx2)

print(mtx)
print(mtx2)
print(mtx3)