from PIL import Image
import sys

# Create new black image
img = Image.new( 'RGBA', (255, 255), "black")
pixels = img.load()

for i in range(img.size[0]):
    for j in range(img.size[1]):
        pixels[i, j] = (i, 100, 100, int((j/4)*4))

img.save(sys.argv[1])