package com.gduranti.sourcescanner.language;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommandRegistry {

    private static CommandRegistry INSTANCE = null;

    private Map<Language, List<Command>> commandsMap = new HashMap<>();

    public static CommandRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommandRegistry();
        }
        return INSTANCE;
    }

    public List<Command> getCommands(Language language) {
        if (!commandsMap.containsKey(language)) {
            loadCommands(language);
        }
        return commandsMap.get(language);
    }

    private void loadCommands(Language language) {
        Scanner scanner = null;

        try {
            List<Command> commands = new ArrayList<>();
            File commandsFile = new File(this.getClass().getResource("/" + language.name().toLowerCase() + ".commands").toURI());
            scanner = new Scanner(commandsFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (!line.startsWith("#")) {
                    String[] split = line.split("|");
                    String[] closeMarkers = split.length > 1 ? Arrays.copyOfRange(split, 1, split.length) : null;
                    commands.add(new Command(split[0], closeMarkers));
                }
            }

            commandsMap.put(language, commands);

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error loading commands.", e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
