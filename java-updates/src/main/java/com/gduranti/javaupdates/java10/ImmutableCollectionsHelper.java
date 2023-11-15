package com.gduranti.javaupdates.java10;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImmutableCollectionsHelper {

	public static void main(String... args) {
		ImmutableCollectionsHelper main = new ImmutableCollectionsHelper();
        main.immutableCollections();

	}

    private void immutableCollections() {

        System.out.println("\nImmutable Collections");
        System.out.println("-----------------------");

        List<String> dogList = List.of("Luna", "Malu", "Mel", "Sirius", "Sirius", "Marvin");
        dogList.forEach(System.out::println);
        System.out.println("--");

        // Set.copyOf: If the given Collection contains duplicate elements,
        // an arbitrary element of the duplicates is preserved
        var dogSetCopyOfList = Set.copyOf(dogList);
        dogSetCopyOfList.forEach(System.out::println);
        System.out.println("--");

        // Set.of: throws IllegalArgumentException if there are any duplicate elements
        var dogSet = Set.of("Luna", "Sirius", "Marvin");
        dogSet.forEach(System.out::println);
        System.out.println("--");

        Map<String, Integer> dogMap = Map.of(
                "Luna", 1,
                "Sirius", 2,
                "Marvin", 3);
        dogMap.forEach((k, v) -> System.out.println("Key=" + k + " " + "Value=" + v));
    }

}
