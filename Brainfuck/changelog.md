# Changelog

## Release 1.1
- Split `compiler.c` into multiple files
    - `compilerwindows.c`
        - C program file for building the BF compiler on Windows systems

    - `compilerlinux.c`
        - C program file for building the BF compiler on Linux systems

    - `compilernodelete.c`
        - C program file for building the BF compiler universally. This version is independent of the user's OS due to the fact that it doesn't have to make a call to the system's delete command

- Added `changelog.md`

- Removed the packaged `BrainfuckCompile.exe` file

- Updated `README.md` extensively

## Release 1.0
* Project created and pushed to GitHub for the first time