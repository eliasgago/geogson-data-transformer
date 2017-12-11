When starting to design a batch job, the business logic should be decomposed into a series of steps which can be implemented using the following standard building blocks:

- Output/Format Applications: Applications reading an input file, restructures data from this record according to a standard format, and produces an output file for printing or transmission to another program or system.

In addition to the main building blocks, each application may use one or more of standard utility steps, such as:

- Merge: A program that reads records from multiple input files and produces one output file with combined data from the input files. Merges can be tailored or performed by parameter-driven standard system utilities.

Batch applications can additionally be categorized by their input source:

- File-driven applications are driven by records or values retrieved from a file.