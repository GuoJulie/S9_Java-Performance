package fr.florentclarret.polytechtours.javaperformance.sortcollection.impl;

import fr.florentclarret.polytechtours.javaperformance.sortcollection.Sort;

import java.util.List;

public final class InsertionSort implements Sort {

    @Override
    public <T extends Comparable<T>> List<T> sort(final List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            final T item = list.get(i);
            int indexHole = i;
            while (indexHole > 0 && list.get(indexHole - 1).compareTo(item) > 0) {
                list.set(indexHole, list.get(--indexHole));
            }
            list.set(indexHole, item);
        }

        return list;
    }

}
