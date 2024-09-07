//-----------------------------------------------------
// Title: Binary Search Tree (BST) Class 
// Author: Ahmet Can Öztürk
// Assignment: 3
// Description: This class extends Comparable class and their type is Customer. Also provides priority queue functions.
// We use delMax() function to delete and return largest element 
//-----------------------------------------------------
import java.util.ArrayList;

public class MaxPQ<Customer extends Comparable<Customer>> {

	private Customer[] pq; // heap-ordered complete binary tree
	private int N = 0; // in pq[1..N] with pq[0] unused
	

	public MaxPQ(int maxN) {
		pq = (Customer []) new Comparable[maxN + 1]; // createa priority queue of initial capacity max
	}

	public Customer[] getPq() {
		return pq;
	}

	public void setPq(Customer[] pq) {
		this.pq = pq;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	// is the priority queue empty or not
	public boolean isEmpty() {
		return N == 0;
	}

	//number of keys in the priority queue
	public int size() {
		return N;
	}

	//insert a key into the priority queue
	public void insert(Customer v) {
		pq[++N] = v;
		swim(N);
	}

	// This function provide to delete the priority element from queue and return the largest
	public Customer delMax() {
		Customer max = pq[1];
		exch(1, N--);
		pq[N + 1] = null;
		sink(1);
		return max;
	}

// Retrieve max key from top.
// Exchange with last item.
// Avoid loitering.
// Restore heap property.
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	
	private void exch(int i, int j) {
		Customer t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}

	}

	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

}
