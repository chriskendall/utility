// Copyright (c) 2015 Travelex Ltd

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
    public void getEncodedLongTest() {

        Long value = 123456789L;

        assertEquals("AAAAAAdbzRU", UUIDUtility.getEncodedLong(value));

    }

}
