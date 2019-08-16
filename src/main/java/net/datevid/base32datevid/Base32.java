package net.datevid.base32datevid;

import java.security.SecureRandom;
import java.util.*;

/**
 * Propuesta de codificación Base32
 *
 * @author @datevid
 * Características
 * Facil uso para el ser humano
 * Toda la cadena será tratado con mayusculas.
 * Reduce la obscenidad accidental retirando la O,U,I del alfabeto
 * Puede ser usado para cifrar indices de la base de datos y evitar comprometer información sensible
 * Usa aleatoriedad shuffle: @see http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 * Base32Datevid usa el alfabeto ABCDEFGHJKLMNPQRSTVWXYZ123456789

 *
 * Letras omitidas:
 * O,o,0 omitidos por similitud
 * 0 omitido por generar posibles palabras obsenas en español/ingles
 * U omitido por generar posibles palabras obsenas en español/ingles
 * More in: https://github.com/datevid/Base32Datevid
 */
public class Base32 {

    private static String ALPHABET_BASE32 = "ABCDEFGHJKLMNPQRSTVWXYZ123456789";
    private static String ALPHABET_B32_UPPERCASE = ALPHABET_BASE32.toUpperCase();
    private static int BASE_32 = ALPHABET_BASE32.length();

    public static String encode(Long numB10) {
        List<Integer> numB32Array = fromB10LongToB32Array(numB10, BASE_32);
        String numB32Str = fromB32ArrayToB32Str(numB32Array, ALPHABET_B32_UPPERCASE);
        return numB32Str;
    }

    public static Long decode(String numB32) {
        String numB32Uppercase=numB32.toUpperCase();
        List<Integer> numB10Array = fromB32StrToB10Array(numB32Uppercase, ALPHABET_B32_UPPERCASE);
        Long numB10 = fromB10ArrayToB10Long(numB10Array, BASE_32);
        return numB10;
    }

    public static String encode(Long numB10,String secret) {
        List<Integer> numB32Array = fromB10LongToB32Array(numB10, BASE_32);
        String numB32Str = fromB32ArrayToB32Str(numB32Array, ALPHABET_B32_UPPERCASE);
        String shuffle = shuffle(numB32Str, secret);
        return shuffle;
    }

    public static Long decode(String numB32,String secret) {
        String numB32Uppercase=numB32.toUpperCase();
        String deshuffle = deshuffle(numB32Uppercase, secret);
        List<Integer> numB10Array = fromB32StrToB10Array(deshuffle, ALPHABET_B32_UPPERCASE);
        Long numB10 = fromB10ArrayToB10Long(numB10Array, BASE_32);
        return numB10;
    }

    private static List<Integer> fromB10LongToB32Array(long numBase10, int baseB) {
        List<Integer> resArray = new ArrayList();
        if (numBase10 == 0) {
            resArray.add(0);
            return resArray;
        }
        long D = numBase10;
        int d = baseB;
        while (D > 0) {
            long c = D / d;
            int res = (int) (D % d);
            resArray.add(res);
            D = c;
        }
        Collections.reverse(resArray);
        return resArray;
    }

    private static Long fromB10ArrayToB10Long(List<Integer> numBaseBArray, int baseB) {
        Long numBase10 = 0L;
        int size = numBaseBArray.size();
        for (int i = 0; i < size; i++) {
            double potencia = Math.pow(baseB, size - i - 1);
            int numberIndex;
            numberIndex = numBaseBArray.get(i);
            numBase10 = numBase10 + numberIndex * ((long) potencia);
        }
        return numBase10;

    }

    private static String fromB32ArrayToB32Str(List<Integer> arrayBaseB, String alphabetBaseB) {
        final StringBuilder uniqueAlphabet = new StringBuilder();
        int j=arrayBaseB.size();
        for (int i = 0; i <j;  i++) {
            uniqueAlphabet.append(alphabetBaseB.charAt(arrayBaseB.get(i)));
        }
        return uniqueAlphabet.toString();
    }

    private static List<Integer> fromB32StrToB10Array(String symbol, String alphabetBaseB) {
        List<Integer> numberList = new ArrayList<>();
        int j=symbol.length();
        for (int i = 0; i < j; i++) {
            int indexOf = alphabetBaseB.indexOf(symbol.charAt(i));
            numberList.add(indexOf);
        }
        return numberList;
    }

    /**
     * * @see https://stackoverflow.com/a/32338074/7105200
     * * @see http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
     * @param size
     * @param passKey
     * @return
     */
    public static int[] getShuffleExchanges(int size, String passKey)
    {
        int[] exchanges = new int[size - 1];
        SecureRandom rand = new SecureRandom(passKey.getBytes());
        for (int i = size - 1; i > 0; i--)
        {
            int n = rand.nextInt(i + 1);
            exchanges[size - 1 - i] = n;
        }
        return exchanges;
    }

    /**
     * * @see https://stackoverflow.com/a/32338074/7105200
     * * @see http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
     * @param text
     * @param passKey
     * @return
     * @autor datevid
     */
    public static String shuffle(String text, String passKey)
    {
        char[] toShuffle = text.toCharArray();
        int size = toShuffle.length;
        int[] exchanges = getShuffleExchanges(size, passKey);
        for (int i = size - 1; i > 0; i--)
        {
            int n = exchanges[size - 1 - i];
            char tmp = toShuffle[i];
            toShuffle[i] = toShuffle[n];
            toShuffle[n] = tmp;
        }
        return (new String(toShuffle));

    }

    /**
     * * @see https://stackoverflow.com/a/32338074/7105200
     * * @see http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
     * @param text
     * @param passKey
     * @return
     * @autor datevid
     */
    public static String deshuffle(String text, String passKey)
    {
        char[] shuffled = text.toCharArray();
        int size = shuffled.length;
        int[] exchanges = getShuffleExchanges(size, passKey);
        for (int i = 1; i < size; i++)
        {
            int n = exchanges[size - i - 1];
            char tmp = shuffled[i];
            shuffled[i] = shuffled[n];
            shuffled[n] = tmp;
        }
        return (new String(shuffled));
    }
}
