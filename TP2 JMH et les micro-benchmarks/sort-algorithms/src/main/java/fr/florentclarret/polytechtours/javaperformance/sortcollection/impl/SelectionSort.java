package fr.florentclarret.polytechtours.javaperformance.sortcollection.impl;

import fr.florentclarret.polytechtours.javaperformance.sortcollection.Sort;

import java.util.List;

public final class SelectionSort implements Sort {

    @Override
    public <T extends Comparable<T>> List<T> sort(final List<T> list) {

        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                list.set(i, list.set(minIndex, list.get(i)));
            }
        }

        return list;
    }

}
