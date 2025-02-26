public class Compressor {

    /**
     * Repeats a string n amount of times recursively.
     *
     * @param n      the number of times for the string to be repeated.
     * @param string the string being repeated.
     * @return string repeated n times.
     * @throws IllegalArgumentException if n is less than 0
     */
    public String repeatString(String string, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n was < 0");
        } else if (n == 0) {
            return "";
        } else {
            return string + repeatString(string, n - 1);
        }
    }

    /**
     * Compresses a string using a for loop.
     *
     * @param text the string to compress.
     * @return the compressed string.
     */
    public String compress(String text) {
        StringBuilder compressed = new StringBuilder();

        for (int i = 0; i < text.length(); ) {
            String sub = text.substring(i, i + 1);
            int amount = 0;

            while (i < text.length() && sub.equals(text.substring(i, i + 1))) {
                amount++;
                i++;
            }

            compressed.append(sub).append(amount);
        }

        return compressed.toString();
    }

    /**
     * Decompresses a string recursively.
     *
     * @param text the string to decompress.
     * @return the decompressed string.
     */
    public String decompress(String text) {
        if (text.isEmpty()) {
            return "";
        } else {
            int slice = 1;

            String letter = text.substring(0, 1);

            while (slice < text.length() && Character.isDigit(text.charAt(slice))) {
                slice++;
            }

            String amount = text.substring(1, slice);

            return repeatString(letter, Integer.parseInt(amount)) + decompress(text.substring(slice));
        }
    }

}