import numpy as np
import cv2

lowerThresh = 0
upperThresh = 1
dialate = 0
blurAmt = 1

def nothing(x):
    pass

cap = cv2.VideoCapture(0)
cap.set(cv2.CAP_PROP_FPS, 60)
cv2.namedWindow('window')

cv2.createTrackbar('Lower Threshold', 'window', 0, 59, nothing)
cv2.createTrackbar('Upper Threshold', 'window', 1, 60, nothing)
cv2.createTrackbar('Dialation', 'window', 1, 10, nothing)
cv2.createTrackbar('blur', 'window', 1, 10, nothing)

while True :
    #Capture frame-by-frame
    ret, frameraw = cap.read()
    frameflip = cv2.flip(frameraw, 1)
    if blurAmt < 1 :
        blurAmt = 1
    
    if blurAmt % 2 == 0 :
        blurAmt+=1

    if dialate < 1 :
        dialate = 1
    frame = cv2.GaussianBlur(frameflip, (blurAmt, blurAmt), 0)
    #Our operations on the frame come here
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)


    lowerThresh = cv2.getTrackbarPos('Lower Threshold', 'window')
    upperThresh = cv2.getTrackbarPos('Upper Threshold', 'window')
    dialate = cv2.getTrackbarPos('Dialation', 'window')
    blurAmt = cv2.getTrackbarPos('blur', 'window')

    eKernel = np.ones((1, 1), np.uint8)
    dKernel = np.ones((dialate, dialate), np.uint8)
    clredg = cv2.Canny(gray, lowerThresh, upperThresh)

    dialated = cv2.dilate(clredg, dKernel, iterations = 1)
    #Display the resulting frame
    
    cv2.imshow('window', dialated)
    cv2.imshow('raw', frame)
    if cv2.waitKey(1) & 0xff == ord('q'):
        break

#When everything done, release the capture
cap.release()
cv2.destroyAllWindows()