# ClassicalSynchronizationProblems

This project solves four classical synchronization problems:
 1. "Producer-consumer" problem (ProducerConsumer package). 
 
 	   There are existing two threads. One of them "producer" generates messages, and the other, "consumer", receives them and for further processing.
		 Threads interacts with each other by common storage with fixed capacity. "Producer" puts message into queue if it has empty space,
		 otherwise he is waiting for releasing space in the queue. "Consumer" retreives message from queue if it isn't empty, otherwise he is waiting
		 for adding some data into the queue. https://en.wikipedia.org/wiki/Producer%E2%80%93consumer_problem
		 
		 This problem has solved by using semaphores.
 2. "Readers-writers" problem (WritersReaders package).
 
 There are existing two groups of threads which shares common storage. One of them "readers" performs only reading data from storage without deleting it. "Writers" performs recording new data in a storage. There are some restrictions in this case: threads-writers can't work together, and no process of reading can be performed while some thread puts new data in the storage, also threads-readres can be executing at the same time with each other. https://en.wikipedia.org/wiki/Readers%E2%80%93writers_problem
 		
 		This problem has solved by using wait()/notify() methods.
 		
 3. "Dining philosophers" problem (EatingPhilosophers package).
 
 Five philosophers sit at round table with 5 plates of spaghetti, each one belongs to some philosopher. Between plates placed one fork. Philosophers either think or eat. To start eating philosopher must take two adjacent. Each fork is held only by one philosopher, so if another philosopher want to take the fork he need to wait until it will be released. https://en.wikipedia.org/wiki/Dining_philosophers_problem
 
 		This problem has solved by using locks.
 		
 4. "Sleeping barber" problem (BarberShop package).
 
 The barber shop has two rooms - waiting room which has limited number of places and working room which has single сhair, where client is serving. Firsty, visitors come into waiting room and if there any free spaces they take place in the queue otherwise they leave the barber shop. If there any visitors in waiting room barber calls ome of them to his room and haircuts him. After serving visitor leaves barber shop and barber calls next one if it exists, otherwise he waits for some visitors. https://en.wikipedia.org/wiki/Sleeping_barber_problem
 
 		This problem has solved by using monitors.
