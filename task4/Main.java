import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Створюємо транспортні засоби
            Bus bus = new Bus(3);
            Taxi taxi = new Taxi(2);
            FireTruck fireTruck = new FireTruck(2);
            PoliceCar policeCar = new PoliceCar(1);

            // --- Тест 1: Наповнення автобусу різними типами пасажирів ---
            System.out.println("Тест 1: Наповнення автобуса");
            bus.addPassenger(new RegularPassenger("John"));
            bus.addPassenger(new Firefighter("Alice"));
            bus.addPassenger(new Policeman("Charlie"));
            System.out.println("Пасажири автобуса: " + bus.getPassengers());

            // --- Тест 2: Переповнення автобуса ---
            System.out.println("Тест 2: Переповнення автобуса");
            try {
                bus.addPassenger(new RegularPassenger("Mike"));
            } catch (IllegalStateException e) {
                System.out.println("Очікуване виключення: " + e.getMessage());
            }

            // --- Тест 3: Наповнення пожежної машини тільки пожежниками ---
            System.out.println("Тест 3: Наповнення пожежної машини");
            fireTruck.addPassenger(new Firefighter("Bob"));
            fireTruck.addPassenger(new Firefighter("Alice"));
            System.out.println("Пасажири пожежної машини: " + fireTruck.getPassengers());

            // --- Тест 4: Спроба додати не пожежника до пожежної машини ---
            System.out.println("Тест 4: Спроба додати звичайного пасажира до пожежної машини");
            try {
                fireTruck.addPassenger(new Firefighter("Mike")); // Заміна на правильний тип
            } catch (IllegalStateException e) {
                System.out.println("Очікуване виключення: " + e.getMessage());
            }

            // --- Тест 5: Наповнення поліцейської машини ---
            System.out.println("Тест 5: Наповнення поліцейської машини");
            policeCar.addPassenger(new Policeman("Charlie"));
            System.out.println("Пасажири поліцейської машини: " + policeCar.getPassengers());

            // --- Тест 6: Спроба додати не поліцейського до поліцейської машини ---
            System.out.println("Тест 6: Спроба додати звичайного пасажира до поліцейської машини");
            try {
                policeCar.addPassenger(new Policeman("Alice")); // Заміна на правильний тип
            } catch (IllegalStateException e) {
                System.out.println("Очікуване виключення: " + e.getMessage());
            }

            // --- Тест 7: Підрахунок людей на дорозі ---
            System.out.println("Тест 7: Підрахунок людей на дорозі");
            Road road = new Road();
            road.addCarToRoad(bus);
            road.addCarToRoad(taxi);
            road.addCarToRoad(fireTruck);
            road.addCarToRoad(policeCar);
            System.out.println("Загальна кількість людей на дорозі: " + road.getCountOfHumans());

            // --- Збереження та читання з файлу ---
            System.out.println("Збереження пасажирів автобуса у файл...");
            savePassengersToFile(bus.getPassengers(), "bus_passengers.dat");

            System.out.println("Читання пасажирів із файлу...");
            List<Passenger> loadedPassengers = loadPassengersFromFile("bus_passengers.dat");
            System.out.println("Завантажені пасажири: " + loadedPassengers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Збереження пасажирів у файл.
     */
    private static void savePassengersToFile(List<? extends Passenger> passengers, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(passengers);
        }
    }

    /**
     * Завантаження пасажирів із файлу.
     */
    @SuppressWarnings("unchecked")
    private static List<Passenger> loadPassengersFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Passenger>) ois.readObject();
        }
    }
}
