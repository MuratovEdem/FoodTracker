package org.example.foodtracker.enums;

public enum TargetEnum {
    LOSS("Loss Weight"), MAINTENANCE("Maintenance Weight"), GAIN("Gain Weight");

    private final String name;

    TargetEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
