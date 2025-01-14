import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Passenger> {
    private int maxSeats;
    private List<T> passengers = new ArrayList<>();

    public Vehicle(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void addPassenger(T passenger) {
        if (passengers.size() >= maxSeats) {
            throw new IllegalStateException("No more seats available in the vehicle.");
        }
        passengers.add(passenger);
    }

    public void removePassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new IllegalStateException("Passenger not found in the vehicle.");
        }
    }

    public List<T> getPassengers() {
        return new ArrayList<>(passengers);
    }
}

class Bus extends Vehicle<Passenger> {
    public Bus(int maxSeats) {
        super(maxSeats);
    }
}

class Taxi extends Vehicle<Passenger> {
    public Taxi(int maxSeats) {
        super(maxSeats);
    }
}

class FireTruck extends Vehicle<Firefighter> {
    public FireTruck(int maxSeats) {
        super(maxSeats);
    }
}

class PoliceCar extends Vehicle<Policeman> {
    public PoliceCar(int maxSeats) {
        super(maxSeats);
    }
}
