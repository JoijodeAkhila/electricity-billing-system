package model;

public class Customer {
    private String id;
    private String name;
    private int units;

    public Customer(String id, String name, int units) {
        this.id = id;
        this.name = name;
        this.units = units;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + units;
    }
}
