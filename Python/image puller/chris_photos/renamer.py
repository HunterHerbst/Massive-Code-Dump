import os
for i in range(1036):
    os.rename(f'chris{i}.jpg', f'chris{i:04d}.jpg')