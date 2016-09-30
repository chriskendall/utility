// Copyright (c) 2016 C P Kendall

package uk.co.cpkendall.utility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.UUID;

/**
 * Test @class
 */
public class UUIDUtilityTest {

    @Test
    public void get22CharacterUUIDStringTest() {

        UUID uuid = UUID.fromString("b9312a03-bf2d-43e8-8560-82abd1e38385");

        assertEquals("uTEqA78tQ_ghWCCq9Hjg4U", UUIDUtility.get22CharacterUUIDString(uuid));

    }

    @Test
    public void getUUIDFromEncodedStringTest() {

        UUID uuid = UUID.fromString("b9312a03-bf2d-43e8-8560-82abd1e38385");

        assertEquals("uTEqA78tQ_ghWCCq9Hjg4U", UUIDUtility.get22CharacterUUIDString(uuid));

        assertEquals("b9312a03-bf2d-43e8-8560-82abd1e38385", UUIDUtility.getUUIDFromEncodedString("uTEqA78tQ_ghWCCq9Hjg4U").toString());

    }


    @Test
    public void getEncodedLongTest() {
        Long value = 23463682394857234L;

        assertEquals("AFNcGEZEnxI", UUIDUtility.getEncodedLong(value));
    }


    @Test
    public void getUUIDFrormEncodedStringTest() {

        long value = 3489896342348L;

        assertEquals("AAADLI4II0w", UUIDUtility.getEncodedLong(value));

        assertEquals(value, UUIDUtility.getLongFromEncodedString("AAADLI4II0w"));

    }

}
