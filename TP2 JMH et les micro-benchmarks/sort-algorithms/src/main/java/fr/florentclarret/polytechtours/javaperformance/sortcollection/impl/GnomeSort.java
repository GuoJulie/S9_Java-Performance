package fr.florentclarret.polytechtours.javaperformance.sortcollection.impl;

import fr.florentclarret.polytechtours.javaperformance.sortcollection.Sort;

import java.util.List;

public final class GnomeSort implements Sort {

    @Override
    public <T extends Comparable<T>> List<T> sort(final List<T> list) {
        int i = 0;
        while (i < list.size()) {
            if (i == 0 || list.get(i - 1).compareTo(list.get(i)) <= 0) {
                i++;
            } else {
                list.set(i - 1, list.set(i, list.get(i - 1)));
                i--;
            }
        }
        return list;
    }

}
