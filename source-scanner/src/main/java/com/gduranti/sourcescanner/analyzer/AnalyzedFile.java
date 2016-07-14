package com.gduranti.sourcescanner.analyzer;

import java.util.ArrayList;
import java.util.List;

public class AnalyzedFile implements Comparable<AnalyzedFile> {

    private final String name;
    private final String path;
    private final List<SouceFragment> fragments;

    public AnalyzedFile(String name, String path) {
        this.name = name;
        this.path = path;
        this.fragments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public List<SouceFragment> getFragments() {
        return fragments;
    }

    public void add(SouceFragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public int compareTo(AnalyzedFile o) {
        return path.compareTo(o.path);
    }

}
