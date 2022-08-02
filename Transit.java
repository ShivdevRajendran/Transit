package transit;

import java.util.ArrayList;

import javax.swing.ScrollPaneConstants;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	private TNode trainZero; // a reference to the zero node in the train layer

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */ 
	public Transit() { trainZero = null; }

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */
	public Transit(TNode tz) { trainZero = tz; }
	
	/*
	 * Getter method for trainZero
	 *
	 * DO NOT remove from this file.
	 */
	public TNode getTrainZero () {
		return trainZero;
	}



	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0. Store the zero node in the train layer in
	 * the instance variable trainZero.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 */
	public void makeList(int[] trainStations, int[] busStops, int[] locations) {

	    // UPDATE THIS METHOD

		trainZero = new TNode(0);
		TNode train = new TNode(trainStations[0]);
		trainZero.setNext(train);
		TNode busZero = new TNode(0);
		TNode bus = new TNode(busStops[0]);
		busZero.setNext(bus);
		TNode walkZero = new TNode(0);
		TNode walk = new TNode(locations[0]);
		walkZero.setNext(walk);
		trainZero.setDown(busZero);
		busZero.setDown(walkZero);
		int j = 0;
		int k = 0;
		for (int i = 0; i < trainStations.length; i++) {
			train.setLocation((trainStations[i]));
			//StdOut.println("train: " + train.getLocation() + " ");
			while ( ( j < busStops.length ) || (bus.getLocation() != train.getLocation() ) ) {
					bus.setLocation(busStops[j]);
					//StdOut.println("bus: " + bus.getLocation() + " ");
				while ( (k < locations.length ) || (walk.getLocation() != bus.getLocation() ) ) {
					walk.setLocation((locations[k]));
					//StdOut.println("walk: " + walk.getLocation() + " ");	
					if (walk.getLocation() == bus.getLocation() ) {
						bus.setDown(walk);
						//StdOut.println("YES");

						if (k != locations.length -1) {
						TNode walkNext = new TNode(0);
						walk.setNext(walkNext);
						walk = walkNext;
						}
						k++;
						break;
					}
					else if (k != locations.length - 1){
					TNode walkNext = new TNode(0);
					walk.setNext(walkNext);
					walk = walkNext;
					}

					k++;
				}

				if (bus.getLocation() == train.getLocation() ) {
					train.setDown(bus);
					//StdOut.println("YES");

					if (j != busStops.length - 1) {
						TNode busNext = new TNode(0);
						bus.setNext(busNext);
						bus = busNext;
					}
					j++;
					break;
				}
				else 
					if (j != busStops.length-1){
						TNode busNext = new TNode(0);
						bus.setNext(busNext);
						bus = busNext;
					}
				j++;
				//bus = bus.getNext();

			}
			//train = train.getNext();

			if (i != trainStations.length -1) {
			TNode trainNext = new TNode(0);
			train.setNext(trainNext);
			train = trainNext;
			}
		}



//rest

	while ( ( j < busStops.length ) || (bus.getLocation() != train.getLocation() ) ) {
		if (j < busStops.length) {
		bus.setLocation(busStops[j]);
		//StdOut.println("bus: " + bus.getLocation() + " ");
		while ( (k < locations.length ) || (walk.getLocation() != bus.getLocation() ) ) {		
				walk.setLocation((locations[k]));
				//StdOut.println("walk: " + walk.getLocation() + " ");
				if (walk.getLocation() == bus.getLocation() ) {
					bus.setDown(walk);
					//StdOut.println("YES");

					if (k != locations.length -1) {
						TNode walkNext = new TNode(0);
						walk.setNext(walkNext);
						walk = walkNext;
					}
				k++;
				break;
				}
			else if (k != locations.length - 1){
			TNode walkNext = new TNode(0);
			walk.setNext(walkNext);
			walk = walkNext;
			}

			k++;
		}

		if (bus.getLocation() == train.getLocation() ) {
			train.setDown(bus);
			//StdOut.println("YES");
			
			if (j != busStops.length - 1) {
				TNode busNext = new TNode(0);
				bus.setNext(busNext);
				bus = busNext;
			}
			j++;
			break;
		}
		else 
			if (j != busStops.length-1) {
				TNode busNext = new TNode(0);
				bus.setNext(busNext);
				bus = busNext;
			}
		j++;
		}

		else {
			break;
		}
	}
		//bus = bus.getNext();

		//rest



		while ( (k < locations.length ) || (walk.getLocation() != bus.getLocation() ) ) {
			if (k < locations.length) {
				walk.setLocation((locations[k]));
				//StdOut.println("walk: " + walk.getLocation() + " ");			
			
				if (walk.getLocation() == bus.getLocation() ) {
					bus.setDown(walk);
					//StdOut.println("YES");
			
				if (k != locations.length -1) {
					TNode walkNext = new TNode(0);
					walk.setNext(walkNext);
					walk = walkNext;
				}
				k++;
				break;
				}
				else if (k != locations.length - 1) {
				TNode walkNext = new TNode(0);
				walk.setNext(walkNext);
				walk = walkNext;
				}
			k++;
			}

			else {
				break;
			}
		}		
	}

	
	/**
	 * Modifies the layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param station The location of the train station to remove
	 */
	public void removeTrainStation(int station) {
	    // UPDATE THIS METHOD

		TNode ptr = new TNode();
		ptr.setNext(trainZero);
		ptr = ptr.getNext();
		TNode prev = new TNode();
		prev.setNext(ptr);
		while (ptr != null) {

			if (ptr.getLocation() == station) {
					//prev = prev.getNext(); 
					prev.setNext( ptr.getNext() );  // = ptr.getNext();

					//if (ptr.getNext().getNext() != null) {
					//ptr = ptr.getNext().getNext();
					//}
					break;
					//prev.getNext() = ptr.getNext();
			}

			if (ptr.getNext() == null) {
				break;
			}
			ptr = ptr.getNext();
			prev = prev.getNext();
			//StdOut.println("POOP");
		}


		//StdOut.println(ptr.getNext().getNext().getLocation());
	}

	/**
	 * Modifies the layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param busStop The location of the bus stop to add
	 */
	public void addBusStop(int busStop) {
	    // UPDATE THIS METHOD
		TNode busPtr = trainZero.getDown();
		TNode walkPtr = busPtr.getDown();

		//while (busPtr.getNext() != null) {
		while (busPtr.getLocation() <= busStop ) {
			//StdOut.println(busPtr.getLocation());

			while (walkPtr.getLocation() < busStop) {
				walkPtr = walkPtr.getNext();
				//StdOut.println(walkPtr.getLocation());
			}

		if (busPtr.getNext() != null ) {
			if ( busPtr.getLocation() < busStop && busPtr.getNext().getLocation() > busStop) {
				busPtr.setNext(new TNode(  busStop , busPtr.getNext(), walkPtr ) );
			}
		}
		
		if (busPtr.getNext() == null) {
			busPtr.setNext(new TNode(  busStop , null, walkPtr ) );
			break;
		}

		if (busPtr.getNext() != null) 
			busPtr = busPtr.getNext();

		}

	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param destination An int representing the destination
	 * @return
	 */
	public ArrayList<TNode> bestPath(int destination) {

	    // UPDATE THIS METHOD

		TNode ptr = new TNode();
		ptr.setNext(trainZero);
		ptr = ptr.getNext();
		ArrayList<TNode> bestpath = new ArrayList<TNode>();
		bestpath.add(ptr); 
		while (ptr.getNext() != null) {
			if (ptr.getNext().getLocation() < destination) {
				ptr = ptr.getNext();
				bestpath.add(ptr); 
				}
		if (ptr.getNext() != null) {
			if (ptr.getNext().getLocation() == destination) {
				ptr = ptr.getNext();
				bestpath.add(ptr);
				while (ptr.getDown() != null) {
					ptr = ptr.getDown();
					bestpath.add(ptr);
				}
				while(ptr.getNext() != null && ptr.getLocation() < destination) {
					ptr = ptr.getNext();
					bestpath.add(ptr);
				}
				return bestpath;
			}
		}
			if ( ptr.getNext() != null && ptr.getNext().getLocation() > destination) {
				ptr = ptr.getDown();
				bestpath.add(ptr);
			//ptr = ptr.getNext();
			}
			if (ptr.getNext() == null) {
				if (ptr.getDown() != null) {
					ptr = ptr.getDown();
					bestpath.add(ptr);
				}
			
			}
		}
		return bestpath;
	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @return A reference to the train zero node of a deep copy
	 */
	public TNode duplicate() {

	    // UPDATE THIS METHOD
	TNode Tptr = new TNode();
	Tptr.setNext(trainZero);
	TNode Bptr = new TNode();
	Bptr.setNext(trainZero.getDown());
	TNode Wptr = new TNode();
	Wptr.setNext(trainZero.getDown().getDown());

	Tptr = Tptr.getNext();
	Wptr = Wptr.getNext();
	Bptr = Bptr.getNext();
	//Tptr.setDown(Bptr);
	//Tptr.getDown().setDown(Wptr);
	//ptr to duplicate here
	TNode duplicate = new TNode();
	TNode Tdup = new TNode();
	TNode Tduplicate = new TNode(Tptr.getLocation());
	Tdup.setNext(Tduplicate);
	duplicate.setNext(Tdup);

	TNode Bdup = new TNode();
	TNode Bduplicate = new TNode(Bptr.getLocation());
	Bdup.setNext(Bduplicate);
	
	TNode Wdup = new TNode();
	TNode Wduplicate = new TNode(Wptr.getLocation());
	Wdup.setNext(Wduplicate);
	//dup.setNext(duplicate);
	//duplicate.setDown(new TNode(0));// ( Bptr.getLocation() ) );
	//duplicate.getDown().setDown(new TNode ( Wptr.getLocation() ) );

	while (Tptr.getNext() != null) {
		Tptr = Tptr.getNext();
		//StdOut.println(Tptr.getLocation());
		Tduplicate.setNext(new TNode (Tptr.getLocation()) );
		Tduplicate = Tduplicate.getNext();
	}

	while (Bptr.getNext() != null) {
		Bptr = Bptr.getNext();
		Bduplicate.setNext(new TNode (Bptr.getLocation()) );
		Bduplicate = Bduplicate.getNext();
	}

	while (Wptr.getNext() != null) {
		Wptr = Wptr.getNext();
		Wduplicate.setNext(new TNode (Wptr.getLocation()) );
		Wduplicate = Wduplicate.getNext();
	}
//connect
	Tdup = Tdup.getNext();
	//StdOut.println(Tdup.getLocation());
	Bdup = Bdup.getNext();
	Wdup = Wdup.getNext();
	while (Wdup != null) {

		if (Wdup.getLocation() == Bdup.getLocation()) {
			Bdup.setDown(Wdup);
			if (Bdup.getLocation() == Tdup.getLocation()) {
				Tdup.setDown(Bdup);
				if(Tdup.getNext() != null) Tdup = Tdup.getNext();
			}
			if (Bdup.getNext() != null) Bdup = Bdup.getNext();
		}
		Wdup = Wdup.getNext();

	}

	duplicate = duplicate.getNext().getNext();
	return duplicate;

	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public void addScooter(int[] scooterStops) {

	    // UPDATE THIS METHOD
	/*	while (Tptr.getNext() != null) {
			Tptr = Tptr.getNext();
			//StdOut.println(Tptr.getLocation());
			Tduplicate.setNext(new TNode (Tptr.getLocation()) );
			Tduplicate = Tduplicate.getNext();
		}
		*/
		TNode Sptr = new TNode();
		TNode scootPtr = new TNode(0);
		Sptr.setNext(scootPtr);

		TNode Wptr = new TNode();
		Wptr.setNext(trainZero.getDown().getDown());
		TNode Bptr = new TNode();
		Bptr.setNext( trainZero.getDown() );

		Wptr = Wptr.getNext();
		Bptr = Bptr.getNext();
		

		for (int i = 0; i < scooterStops.length; i++) {
			scootPtr.setNext(new TNode (scooterStops[i]) );
			scootPtr = scootPtr.getNext();

		}
		Sptr = Sptr.getNext();

		while (Wptr != null) {

			if (Wptr.getLocation() == Sptr.getLocation()) {
				Sptr.setDown(Wptr);
				if (Sptr.getLocation() == Bptr.getLocation()) {
					Bptr.setDown(Sptr);
					if(Bptr.getNext() != null) Bptr = Bptr.getNext();
				}
				if (Sptr.getNext() != null) Sptr = Sptr.getNext();
			}
			Wptr = Wptr.getNext();
	
		}

	}

	/**
	 * Used by the driver to display the layered linked list. 
	 * DO NOT edit.
	 */
	public void printList() {
		// Traverse the starts of the layers, then the layers within
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// Output the location, then prepare for the arrow to the next
				StdOut.print(horizPtr.getLocation());
				if (horizPtr.getNext() == null) break;
				
				// Spacing is determined by the numbers in the walking layer
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print("--");
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print("-");
				}
				StdOut.print("->");
			}

			// Prepare for vertical lines
			if (vertPtr.getDown() == null) break;
			StdOut.println();
			
			TNode downPtr = vertPtr.getDown();
			// Reset horizPtr, and output a | under each number
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				while (downPtr.getLocation() < horizPtr.getLocation()) downPtr = downPtr.getNext();
				if (downPtr.getLocation() == horizPtr.getLocation() && horizPtr.getDown() == downPtr) StdOut.print("|");
				else StdOut.print(" ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
	
	/**
	 * Used by the driver to display best path. 
	 * DO NOT edit.
	 */
	public void printBestPath(int destination) {
		ArrayList<TNode> path = bestPath(destination);
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the number if this node is in the path, otherwise spaces
				if (path.contains(horizPtr)) StdOut.print(horizPtr.getLocation());
				else {
					int numLen = String.valueOf(horizPtr.getLocation()).length();
					for (int i = 0; i < numLen; i++) StdOut.print(" ");
				}
				if (horizPtr.getNext() == null) break;
				
				// ONLY print the edge if both ends are in the path, otherwise spaces
				String separator = (path.contains(horizPtr) && path.contains(horizPtr.getNext())) ? ">" : " ";
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print(separator + separator);
					
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print(separator);
				}

				StdOut.print(separator + separator);
			}
			
			if (vertPtr.getDown() == null) break;
			StdOut.println();

			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the vertical edge if both ends are in the path, otherwise space
				StdOut.print((path.contains(horizPtr) && path.contains(horizPtr.getDown())) ? "V" : " ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
}
