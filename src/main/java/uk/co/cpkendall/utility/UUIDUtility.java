// Copyright (c) 2016 C P Kendall
package uk.co.cpkendall.utility;

import java.util.BitSet;
import java.util.UUID;

/**
 * Utility class providing useful UUID features.
 */
public class UUIDUtility {

    private static final int BITS = 6;
    private static final int LONG_ENCODED_LENGTH = 11;
    private static final char[] characters =
                    {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
                                    'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                                    'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
                                    '3', '4', '5', '6', '7', '8', '9', '_', '-'};

    private UUIDUtility() {
        // Singleton
    }

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
    public static String get22CharacterUUIDString(final UUID uuid) {

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
    public static String getEncodedLong(final Long longValue) {

        StringBuilder stringBuilder = new StringBuilder();
        long tempMostSignificantBits = longValue;

        // The number of iterations is the number of bits in the long divided by the number of
        // bits required (rounded up)
        for (int i = 1; i <= Math.ceil(Long.SIZE / (double) BITS); i++) {

            // Shift all bits and fill with 0's so only n bits remaining
            long significantSixBits = tempMostSignificantBits >>> Long.SIZE - BITS;
            // Convert the n bits to an integer which is the index of the available character set

            stringBuilder.append(characters[(int) significantSixBits]);
            // Shift the original bits n places as these have been used
            tempMostSignificantBits = tempMostSignificantBits << BITS;
        }
        return stringBuilder.toString();
    }


    /**
     * Returns a UUID from the previously encoded UUID.
     * @param encoded UUID encoded using <code>get22CharacterUUIDString</code> method
     * @return the decoded UUID
     */
    public static UUID getUUIDFromEncodedString(final String encoded) {

        if (encoded.length() != LONG_ENCODED_LENGTH*2) {
            throw new IllegalArgumentException(
                            "Encoded string is not equal to " + LONG_ENCODED_LENGTH*2 + " "
                                            + "characters");
        }

        Long mostSignificantBits = getLongFromEncodedString(encoded.substring(0, LONG_ENCODED_LENGTH));
        Long leastSignificantBits = getLongFromEncodedString(encoded.substring(LONG_ENCODED_LENGTH));

        return new UUID(mostSignificantBits, leastSignificantBits);
    }



    /**
     * Returns a long from the previously encoded string.
     * @param encoded string encoded using <code>getEncodedLong</code> method
     * @return the decoded long
     */
    public static long getLongFromEncodedString(final String encoded) {

        if (encoded.length() != LONG_ENCODED_LENGTH) {
            throw new IllegalArgumentException(
                            "Encoded string [" + encoded + "] is not equal to " + LONG_ENCODED_LENGTH + " "
                                            + "characters");
        }

        BitSet bitSet = new BitSet(Long.SIZE);
        String characterString = String.valueOf(characters);

        for (int i = 0; i < encoded.length(); i++) {

            // Get the character and find the index
            Character character = encoded.charAt(i);

            // Use the index to set 6 bits
            Integer index = characterString.indexOf(character);

            // Piece the 6 bits together
            byte bits = index.byteValue();

            String binaryString = String.format("%6s", Integer.toBinaryString(bits)).replace(' ', '0');

            for (int bit = BITS; bit > 0; bit--) {
                int bitPosition = (Long.SIZE - (i * BITS) - (BITS - bit)) - 1;

                if (bitPosition >= 0) {
                    bitSet.set(bitPosition, binaryString.substring(BITS - bit, BITS - bit + 1).equals("1"));
                } else {
                    break;
                }
            }
        }

        return bitSet.toLongArray()[0];
    }


}
