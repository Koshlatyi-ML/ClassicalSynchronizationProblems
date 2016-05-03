# ClassicalSynchronizationProblems

This project solves four classical synchronization problems:
 1. "Producer-consumer" problem (ProducerConsumer package). 
 
 	   There are two threads. In one of them "producer" generates messages; in the other, "consumer", receives them for further processing.
		 The threads interact with each other by means of a common storage with fixed capacity. "Producer" enters a new message into the queue if there is enough empty space.
		 Otherwise, it waits for releasing space in the queue. "Consumer" retreives the message from the queue if the last is not empty. Otherwise, it waits
		 for new data to be added into the queue. https://en.wikipedia.org/wiki/Producer%E2%80%93consumer_problem
		 
		 This problem has solved by using semaphores.
 2. "Readers-writers" problem (WritersReaders package).
 
 There exist two groups of threads which share common storage. In one of them, "readers" raed stored data, being able to neither delete nor edit it. In the other, "writers" add data records into the storage. Certain restrictions apply in this case:
	1) multiple "writers" cannot operate simultaneously;
 	2) read operations is not possible while new data is being entered into the storage;
	3) multiple "readers can operate simultaneously. https://en.wikipedia.org/wiki/Readers%E2%80%93writers_problem
 		
 		This problem has solved by using wait()/notify() methods.
 		
 3. "Dining philosophers" problem (EatingPhilosophers package).
 
 Five philosophers sit at round table with 5 plates of spaghetti, each one belongs to some philosopher. Between plates placed one fork. Philosophers either think or eat. To start eating philosopher must take two adjacent. Each fork is held only by one philosopher, so if another philosopher want to take the fork he need to wait until it will be released. https://en.wikipedia.org/wiki/Dining_philosophers_problem
 
 		This problem has solved by using locks.
 		
 4. "Sleeping barber" problem (BarberShop package).
 
 The barber shop has two rooms - waiting room which has limited number of places and working room which has single сhair, where client is serving. Firsty, visitors come into waiting room and if there any free spaces they take place in the queue otherwise they leave the barber shop. If there any visitors in waiting room barber calls ome of them to his room and haircuts him. After serving visitor leaves barber shop and barber calls next one if it exists, otherwise he waits for some visitors. https://en.wikipedia.org/wiki/Sleeping_barber_problem
 
 		This problem has solved by using monitors.
