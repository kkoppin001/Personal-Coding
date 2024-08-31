**This script is a way for the user to enter in a file name and then if they end up 
misspelling or decide they want to change the name of the file, they have the option to change it.**

## CREATING A FILE
- The first thing you will want to do is type in `touch fileName` into the command line and then press enter
To make sure the file has been created, you can use the `ls` command to make sure it worked

``` 
example{

command: `touch yuh.jgp`

output: file created

command: `ls`

output: yuh.jgp

}
```

## RENAMING A FILE
If you see that you accidentally typed in filename.jgp instead of filename.jpg for example, 
you can call `bash namechange -f filename.jgp -r filename.jpg`   and then press enter. This
will then rename the file without having to delete the file, and then redo this process.


example{

command: `bash namechange -f filename.jgp -r filename.jpg`

output: 

File renamed from filename.jgp to filename.jpg

 }

## HELP
If you need help at all for this process, you can type in `bash namechange.sh -h`
and it will printout what needs to happen

example{

command: `bash namechange.sh -h"

output: 

   Usage: namechange -f find -r replace string to modify
   -f The text to find in the filename
   -r The replacement text for the new filename


 }

 ## NOT USING BOTH FLAGS
If you do not use both arguments, the script will not execute and then will have you restart the process for it to be correct

example{

command: `bash namechange.sh -f filename.jgp` OR `bash namechange.sh -r dilename.jpg`

output:

   Both -f and -r options are required
   Usage: namechange -f find -r replace string to modify
   -f The text to find in the filename
   -r The replacement text for the new filename

}

## TYPING IN WRONG FLAGS
If you type anything in that is not `-r`, `-f`, or `-h` you will be prompted the help command

example{

command: `bash namechange.sh -w filename.jgp`

output:

User must provide valid filename
Usage: namechange -f find -r replace "string to modify"
 -f The text to find in the filename
 -r The replacement text for the new filename
Both -f and -r options are required.


 }

 
## WRONG FILE
 If you forget to type in the wrong file, it will tell you to enter a valid file name
 
 example{

 command: `bash namechange -f yuh.jgp -r filename.jpg`

 output: 
    User must provide valid filename
    Usage: namechange -f find -r replace string to modify
   -f The text to find in the filename
   -r The replacement text for the new filename
}








 
