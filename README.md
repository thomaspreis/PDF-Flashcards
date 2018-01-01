# PDF-Flashcards

This project generates a PDF containing Language Flashcards, to help you to learn a new language.

I know there are a lot of softwares and apps which does the same thing, however, I prefer print the cards and keep it with me. Ok, I'm a "old fashioned" technology guy. Pernsonally I don't care about it, I just want to learn it as fast as I can.

Hope you can contribuite with my simple project :)

---

## How it works?  

You simply inform a .txt file to the main method and it will generate a PDF file! Ooops, hang on! Of course it is necessary to have some special control characters in the txt file to generate it in the correct format.

# Input file

Each line in the input file contains two phrases, which one will be printed in the front part of the PDF file and the second phrase in the verse. You have to create sentences in the language that you are learning in the first part and in the verse the translation.

Example:

*Ich möchte nach Brasilien reisen.;I would like to travel to Brazil.*

The program will split the ';' and write the first part in the front page and the second part in the verse.
If you want to make a word in bold you have to write it between **.

# Output file

The output file will be a PDF generated containing front and verse page, it is well formatted to help you to cut the papers to be easier to manipulate.

