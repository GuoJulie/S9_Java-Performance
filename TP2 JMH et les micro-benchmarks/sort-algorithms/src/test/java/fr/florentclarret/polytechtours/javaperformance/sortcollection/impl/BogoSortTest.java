package fr.florentclarret.polytechtours.javaperformance.sortcollection.impl;

import fr.florentclarret.polytechtours.javaperformance.sortcollection.AbstractSortTest;
import org.junit.jupiter.api.Disabled;

@Disabled
public class BogoSortTest extends AbstractSortTest<BogoSort> {

    public BogoSortTest() {
        super(BogoSort::new);
    }
}
