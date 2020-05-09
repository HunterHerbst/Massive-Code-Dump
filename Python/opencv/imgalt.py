import cv2 as cv

cap = cv.VideoCapture(0)
cap.set(cv.CAP_PROP_FPS, 60)
cv.namedWindow('window')

while True:
    ret, raw = cap.read()

def shiftMat(img, x, y):
    pix = cv.getRectSubPix(img, 1)