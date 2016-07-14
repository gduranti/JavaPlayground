package com.gduranti.sourcescanner.language;

import java.util.List;

public enum Language {

    COBOL("\\d\\s*\\*");

    private final String lineCommentPrefix;

    private Language(String lineCommentPrefix) {
        this.lineCommentPrefix = lineCommentPrefix;
    }

    public String getLineCommentPrefix() {
        return lineCommentPrefix;
    }

    public List<Command> getCommands() {
        return CommandRegistry.getInstance().getCommands(this);
    }

}
