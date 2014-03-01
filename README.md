iterator-refactor README

This repository holds example files to practice refactoring a series of loops
to using the iterator pattern.

Requirements:

Given a list of Countries.
Find those Countries that have a Top Level Domain (TLD).
Add the Country's Currency.
Display the Country Name, 3 char ISO-alpha, TLD, and Currency.


Initial psuedo Code:
    Loop through Countries and load into memory array.
    Loop through TLD and load into memory array.
    Loop through Currency and load into memory array.
    Loop through Counties:
        Find TLD by looping through TLD array.
        If TLD found
            Copy Country and TLD info to Output structure
            Look up Currency for Country add info to Output structure
            Add Output structure to array.
    Loop through Output array and print.


Refactored psuedo code:
    Loop through TLD and load into hash array
    Loop through Currency and load into haah array
    Create iterator for Counties
    Add iterator for TLD filter to Country iterator
    Add iterator for Currency filter to TLD iterator
    Loop through TLD iterator and print


