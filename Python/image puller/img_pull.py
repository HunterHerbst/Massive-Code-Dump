import sys, subprocess, time
count = 0
while True:
    p = subprocess.Popen(['powershell.exe', f'iwr -outf Timelapse\\tl{count:04d}.jpg -Uri "http://208.90.173.26/cgi-bin/CGIProxy.fcgi?cmd=snapPicture2&usr=guest&pwd=guest&"'], stdout=sys.stdout)
    count+=1
    time.sleep(0.125)