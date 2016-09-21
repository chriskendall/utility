// Copyright (c) 2016 C P Kendall
package uk.co.cpkendall.utility;

import java.util.UUID;

/**
 * Utility class providing useful UUID features
 */
public class UUIDUtility {

    private static final char[] characters =
                    {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
                                    'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                                    'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
                                    '3', '4', '5', '6', '7', '8', '9', '_', '-'};


    /**
     * Returns a 22 character String representing the 32 character UUID.
     * The character set is as followed:
     * 0-25   = A->Z
     * 26-51  = a->z
     * 52-62  = 0->9
     * 63-64  = _->-
     * @param uuid the UUID to represent
     * @return a 22 character string representation
     */
    public static String get22CharacterUUIDString(UUID uuid) {

        StringBuilder stringBuilder =
                        new StringBuilder(getEncodedLong(uuid.getMostSignificantBits()));
        stringBuilder.append(getEncodedLong(uuid.getLeastSignificantBits()));

        return stringBuilder.toString();
    }

    /**
     * Returns a String representation of a long.
     * The 64 bits of a long are divided into 6 bits and are presented as followed:
     * 0-25   = A->Z
     * 26-51  = a->z
     * 52-62  = 0->9
     * 63-64  = _->-
     * @param longValue the long
     * @return the 11 character string representation of the long value
     */
    public static String getEncodedLong(Long longValue) {
        final int BITS = 6;

        StringBuilder stringBuilder = new StringBuilder();
        long tempMostSignificantBits = longValue;
        // The number of iterations is the number of bits in the long divided by the number of
        // bits required (rounded)
        for (int i = 1; i <= Math.ceil(Long.SIZE / (double) BITS); i++) {
            // Shift all bits and fill with 0's so only n bits remaining
            long significantSixBits = tempMostSignificantBits >>> characters.length - BITS;
            // Convert the n bits to an integer which is the index of the available character set
            stringBuilder.append(characters[(int) significantSixBits]);
            // Shift the original bits n places as these have been used
            tempMostSignificantBits = tempMostSignificantBits << BITS;
        }
        return stringBuilder.toString();
    }


}
