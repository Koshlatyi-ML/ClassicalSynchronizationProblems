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
 
 Five philosophers are sitting at a round table with 5 plates of spaghetti on it. Philosophers got one plate each. One fork is laying between each pair of plates in the circle. Philosophers can either think or eat. To start to eat, a philosopher must take two adjacent to his/her plate. A fork can be held only by one philosopher at a time, so that another philosopher, willing to take it, has to wait until the fork is released. https://en.wikipedia.org/wiki/Dining_philosophers_problem
 
 		This problem has solved by using locks.
 		
 4. "Sleeping barber" problem (BarberShop package).
 
 The barber shop has two rooms - a waiting with a limited number of places and a treatment room with a single сhair. First, drop-in customres get into the waiting room. If there is any free waiting place, a customer takes the place in the queue. Otherwise, the customer leaves the barber shop. If there any customers in the waiting room, a barber asks the first one in the queue to proceed into the treatment room. Then the barber makes the customer a haircut. Afte the treatment is completed, the customer leaves the barber shop and the barber calls the next customer (if there is any waiting). Otherwise, the barber waits for new drop-in customers. https://en.wikipedia.org/wiki/Sleeping_barber_problem
 
 		This problem has solved by using monitors.
