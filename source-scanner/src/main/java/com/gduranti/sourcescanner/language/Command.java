package com.gduranti.sourcescanner.language;

public class Command {

    private final String name;
    private final String[] closeMarkers;

    public Command(String name, String... closeMarkers) {
        this.name = name;
        this.closeMarkers = closeMarkers;
    }

    public String getName() {
        return name;
    }

    public String[] getCloseMarkers() {
        return closeMarkers;
    }

}
