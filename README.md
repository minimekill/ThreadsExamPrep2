# ThreadsExamPrep2

Using threadpools is a way for us to manage many threads. Not using it u have to launch every thread ur self and make sure its joined again. Threadpools can measure wether a new thread is needed and able to launch them all at once with one command.

By using Future we can handle the returns from threads. Most cases we are gonna start asking for outputs from threads that might not have been completed yet, and therefore we can get its result yet. With Future we can make a "shell" that will eventually return a result, so if we use get() on the future it will be returned once the task is finished.

we use callable to support the future. Since we cannot pass a callable into a thread we use a executor service ! 

In order to see the console output of the program, the main class can be executed.
In order to see the json from the server, launch the webproject and go to: http://localhost:8080/ExamPrep2/api/web/scrape


