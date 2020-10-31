import requests
import sys

#make sure there are at least three arguments given.
if len(sys.argv) < 3:
    print('Insufficient number of arguments, please run the program with the following format:')
    print('\tpy save_url_data_to_file.py <url> <output file>')
    exit()

#attempt to get data from URL
try:
    print('Attempting to request URL data...')
    r = requests.get(sys.argv[1], allow_redirects=True)
    print('Data successfully requested.\n')
except:
    print('Unable to request URL information.')
    print('Ensure proper URL was given, or that server is not down.')
    exit()

#create an output file for each file name specified
for filename in sys.argv[2:]:
    try:
        print('Writing data to file %s...' % filename)
        with open(filename, 'wb') as outfile:
            outfile.write(r.content)
            print('Data written. Closing file...')
        print('File closed.\n')
    except:
        print('Unable to write data to file.')
        print('Ensure that output file is not already open or being used by another application')

#tell user all data has been written and list all the outfiles that were written
print('All data written. Names of the output files are as follows:')
for filename in sys.argv[2:]:
    print('\t', filename)
