# Benchmarking Project for Structure of Computer Systems 
This is a project made for the SCS laboratory work.

@Tibi
The progress bar is in FXMLContoller. There I make an instance of MyProgressBar for each "Benchmark" and i add it as an observer to said benchmark. When the benchmark runs a test/iteration of a test, it updates the updateArgs (list of 2 doubles) with current_test(or iteration)_nr/total_test(or iteration)_nr and notifies the observers with it and in MyProgressBar each update gets the args and sets the coresponding bar value.