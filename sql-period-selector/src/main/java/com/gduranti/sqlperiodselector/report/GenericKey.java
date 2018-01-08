package com.gduranti.sqlperiodselector.report;

import java.util.ArrayList;
import java.util.List;

public class GenericKey implements Comparable<GenericKey> {

    private List<String> keys;

    public GenericKey() {
        keys = new ArrayList<>();
    }

    public void addKey(String key) {
        keys.add(key);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GenericKey other = (GenericKey) obj;
        return keys.equals(other.keys);
    }

    @Override
    public int hashCode() {
        return keys.hashCode();
    }

    @Override
    public String toString() {
        return "GenericKey: " + keys.toString();
    }

    @Override
    public int compareTo(GenericKey o) {
        for (int i = 0; i < keys.size(); i++) {
            int compareTo = keys.get(i).compareTo(o.keys.get(i));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

}
