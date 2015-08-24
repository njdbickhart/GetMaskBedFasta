# GetMaskBedFasta

This is a simple program designed to quantify and identify stretches of masked (or "gap") sequence in reference assemblies. The design goals for the program were:

* To run quickly
* To identify longer stretches of "N's" or "X's"
* To quantify how much of a chromosome was covered by "N's" or "X's"
* To give other basic stats regarding the chromosome

## Usage

In order to run the program, you can invoke it on the command line or run it as an executable in Windows.

## Output

GetMaskBedFasta produces two output files:

* A "Stats" file
* A "Bed" file

#### The Bed file

This file contains the start and end coordinates of every run of "N's" in the reference genome that was detected. These are "1-based" coordinates, and can be used directly in programs such as [BedTools](http://bedtools.readthedocs.org/en/latest/).

#### The Stats file

This is a tab delimited file that can be read in Excel or in a text editor. It contains basic statistics for each chromosome, including the longest stretch of masked sequence and the percentage of the chromosome covered by masked sequence.

## Example use cases

#### Case 1: Reference scaffold quality

If you've just run a scaffolding program, you can quickly ascertain the number of gaps in your assembly if it is in fasta form.

#### Case 2: Remasking a reference genome

If you would like to duplicate the masking of a reference genome, or if you wanted to include more masked regions manually, you can run this program and modify the resultant bed file as needed.