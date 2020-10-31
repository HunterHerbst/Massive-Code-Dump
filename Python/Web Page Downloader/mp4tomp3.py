from moviepy.editor import *
from sys import argv

mp4_file = VideoFileClip(argv[1])
mp3_file = mp4_file.audio
mp3_file.write_audiofile(argv[2])
mp3_file.close()
mp4_file.close()