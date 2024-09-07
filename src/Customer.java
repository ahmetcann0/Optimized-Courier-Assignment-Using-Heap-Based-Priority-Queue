//-----------------------------------------------------
// Title: Customer Class
// Author: Ahmet Can Öztürk
// Assignment: 3
// Description: This class implemets to Comparable interface. We overrided the compareTo() method.
// Also our main function which name is findAvg() calculate the average waiting time for customers according to number of couriers. Then prints informations.
//-----------------------------------------------------
import java.util.ArrayList;
import java.util.List;

public class Customer implements Comparable<Customer> {

	// Customer attributes
	int id;
	int year;
	int arrTime;
	int serTime;
	int size;

	// Constructors
	public Customer(int id, int year, int arrTime, int serTime) {
		this.id = id;
		this.year = year;
		this.arrTime = arrTime;
		this.serTime = serTime;
		this.size = 1;
	}

	public Customer() {

	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Overrided compereTo method
	@Override
	public int compareTo(Customer that) {

		if (this.year < that.year) {
			return -1;
		}
		if (this.year > that.year) {
			return 1;
		} else {
			if (this.id < that.id) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public static void findAvg(ArrayList<Customer> arr, MaxPQ<Customer> pq, int avg) {

		List<Courier> couriers = new ArrayList<>();

		int numCouriers = 1;

		while (true) {

			// Reset the simulation state
			// queue.clear();
			couriers.clear();
			for (int i = 0; i < numCouriers; i++) {
				couriers.add(new Courier(i));
			}
			int totalWaitTime = 0;

			// Run the simulation loop
			for (int time = 0; !pq.isEmpty() || !arr.isEmpty(); time++) {
				// Find the next available Courier
				Courier courier = new Courier();
				for (Courier c : couriers) {
					if (c.isAvailable()) {
						courier = c;
						break;
					}
				}
				if (courier == null) {
					// No Couriers are available, wait until one becomes available
					continue;
				}

				// Check for new Customers arriving at this time
				for (int i = 0; i < arr.size(); i++) {
					Customer c = arr.get(i);
					if (c.arrTime == time) {
						pq.insert(c);
						arr.remove(i);
						i--;
					}
				}

				// Assign the highest priority Customer to the Courier
				Customer c = pq.delMax();
				courier.setAvailability(true);
				c.serTime = (c.serTime - 1);
				if (c.serTime == 0) {
					// Customer's service has completed, mark the Courier as available
					courier.setAvailability(false);
					totalWaitTime += time - c.arrTime;
				}
			}

			// Calculate the average waiting time considering user input
			int avgWaitTime = totalWaitTime / arr.size();
			if (avgWaitTime <= avg) {
				// When average is calculated then exit the function due to break
				System.out.println("Minimum number of couriers: " + numCouriers);
				System.out.println("Average waiting time: " + avgWaitTime + " minutes");
				break;
			}

			// Number of couriers increasing one by one
			numCouriers++;
		}
	}
}
