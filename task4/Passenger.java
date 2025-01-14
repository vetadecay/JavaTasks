import java.io.Serializable;

public abstract class Passenger implements Serializable {
    private String name;

    public Passenger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + this.getClass().getSimpleName() + ")";
    }
}

class Firefighter extends Passenger {
    public Firefighter(String name) {
        super(name);
    }
}

class Policeman extends Passenger {
    public Policeman(String name) {
        super(name);
    }
}

class RegularPassenger extends Passenger {
    public RegularPassenger(String name) {
        super(name);
    }
}
