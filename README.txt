* White Space Obliterator

This is a program that clears (obliterates) white space in a specified input file and outputs a non-whitespaced output file
in the same directory. Optionally, this program can also obliterate comments (anything starting with \\) if the
"no-comments" arg is entered.

Input files must be named with a .in extension. Output files will have the same filename, but append a .out extension.

** system requirements

This program requires Java 8+ be installed on system that it is running on.

** How to Run this code

1. unzip the project

2. from the 'src' directory, run 'javac Main.java'

3. run 'java Main <any path to file>' or 'java Main <any path to file> no-comments'. ARGUMENTS MUST BE ENTERED IN THIS
ORDER OR THE PROGRAM WILL EXIT (because it will think your input file is 'no-comments' and does not have an .in
extension). Paths to files can be absolute or relative.

4. view output, which will be in the same directory as the specified input file.

** Troubleshooting

Trying to run this program on a file without a .in extension will result in an error and termination of the program.