import sys
from pytube import YouTube

# if len(sys.argv) < 3:
#     print('Insufficient number of arguments.')
#     print('Remember to provide the video URL as first command-line argument.')
#     print('Remember to provide the name to save the video under as second command-line argument.')
#     exit()

#.streams.filter(progressive=True, file_extension='mp4').order_by('resolution').desc().last().download()