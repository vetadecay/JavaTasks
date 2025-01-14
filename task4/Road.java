import java.util.ArrayList;
import java.util.List;

public class Road {
    private List<Vehicle<? extends Passenger>> carsInRoad = new ArrayList<>();

    public int getCountOfHumans() {
        return carsInRoad.stream()
                .mapToInt(vehicle -> vehicle.getPassengers().size())
                .sum();
    }

    public void addCarToRoad(Vehicle<? extends Passenger> vehicle) {
        carsInRoad.add(vehicle);
    }
}
