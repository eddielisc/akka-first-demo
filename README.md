# sc-akka-first-demo
first try for akka



title files line count seq digarams
App->FileScanner : scan
FileScanner -> FileParser : parse
FileParser -> Aggregator : start-of-file
Aggregator -> FileParser : 
FileParser -> Aggregator : line
note over Aggregator : count line Num
Aggregator -> FileParser : 
FileParser -> Aggregator : end-of-file
note over Aggregator  : print line Num
Aggregator -> FileParser : 
FileParser -> FileScanner : 
note over FileScanner : kill actors & system

