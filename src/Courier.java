//-----------------------------------------------------
// Title: Courier Class 
// Author: Ahmet Can Öztürk
// Assignment: 3
// Description: This class provides to create couriers in customer class.
// It makes easier calculate number of courier and checks their availability.
//-----------------------------------------------------
public class Courier {
    private int id;
    private boolean availability;

    public Courier(int id) {
        this.id = id;
        this.availability = false;
    }
    public Courier() {
        this.id = id;
        this.availability = false;
    }

    public int getId() {
        return id;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

	public boolean isAvailable() {
		return false;
	}
}