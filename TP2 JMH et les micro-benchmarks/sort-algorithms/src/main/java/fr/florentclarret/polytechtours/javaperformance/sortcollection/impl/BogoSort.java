package fr.florentclarret.polytechtours.javaperformance.sortcollection.impl;

import fr.florentclarret.polytechtours.javaperformance.sortcollection.Sort;

import java.util.List;
import java.util.Random;

public final class BogoSort implements Sort {

    private static final Random RANDOM = new Random();

    @Override
    public <T extends Comparable<T>> List<T> sort(final List<T> list) {
        while (!this.isSorted(list)) {
            this.nextPermutation(list);
        }
        return list;
    }

    private <T extends Comparable<T>> boolean isSorted(final List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1).compareTo(list.get(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    private <T> void nextPermutation(final List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int randomIndex = i + RANDOM.nextInt(list.size() - i);
            list.set(randomIndex, list.set(i, list.get(randomIndex)));
        }
    }

}
