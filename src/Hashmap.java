import java.lang.Math;
public class Hashmap {
    public KeyValuePair[] array;
    public PrimeNumberGenerator prime = new PrimeNumberGenerator();

    @Override
    public String toString() {
        String res = String.valueOf(prime.currentPrime()) + "\n";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += "\n";
            }
            res += i + "\t";
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res;
    }

    public String toStringOneLine() {
        String res = String.valueOf(prime.currentPrime()) + "[";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += ",";
            }
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res + "]";
    }

    public Hashmap() {
        array = new KeyValuePair[1];
    }

    public Hashmap(String inp) {
        String data = inp.substring(inp.indexOf('[')+1, inp.length()-1);
        int primeNum = Integer.parseInt(inp.substring(0, inp.indexOf('[')));

        while (prime.currentPrime() < primeNum) {
            prime.nextPrime();
        }

        String[] elements = data.split(",");

        array = new KeyValuePair[elements.length];

        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals("-")) {
                array[i] = null;
            } else {
                String[] extractedData = elements[i].substring(1, elements[i].length()-1).split(":");
                array[i] = new KeyValuePair(Integer.parseInt(extractedData[0]), Integer.parseInt(extractedData[1]));
            }
        }
    }

    public int hash(int studentNumber) {
        if (studentNumber < 0) {
            return -1;
        }

        byte[] digits = new byte[Integer.toString(studentNumber).length()];
        for (int i = 0; i < digits.length; i++) {
            digits[(digits.length - 1) - i] = (byte) (studentNumber % 10);
            studentNumber /= 10;
        }

        int val = 0;
        for (byte digit : digits) {
            val = prime.currentPrime() * val + digit;
        }
        val = Math.abs(val);
        val %= array.length;
        return val;
    }

    public KeyValuePair search(int studentNumber) {
        int index = searchIndex(studentNumber);

        if (index != -1) {
            return array[index];
        }

        return null;
    }
    private int searchIndex(int studentNumber) {
        int index = hash(studentNumber);

        KeyValuePair element = array[index];
        if (element != null && element.studentNumber == studentNumber) {
            return index;
        }

        for (int i = 1; i < array.length; i++) {
            int probe = Math.abs(index + (i % 2 == 0 ? 1 : -1) * (int) (Math.pow((i + 1) / 2, 2)) * prime.currentPrime()) % array.length;
            element = array[probe];
            if (element != null && element.studentNumber == studentNumber) {
                return probe;
            }
        }

        return -1;
    }

    private void resize() {
        KeyValuePair[] oldVals = array;
        array = new KeyValuePair[array.length*2];
        prime.nextPrime();
        for (KeyValuePair element : oldVals) {
            if (element != null) {
                insert(element);
            }
        }
    }

    private void insert(KeyValuePair element) {
        insert(element.studentNumber, element.mark);
    }

    public void insert(int studentNumber, int mark) {
        KeyValuePair element = search(studentNumber);
        if (element != null) {
            element.mark = mark;
            return;
        }

        int index = hash(studentNumber);

        if (array[index] == null) {
            array[index] = new KeyValuePair(studentNumber, mark);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            int probe = Math.abs(index + (int) Math.pow(i, 2) * prime.currentPrime()) % array.length;
            if (array[probe] == null) {
                array[probe] = new KeyValuePair(studentNumber, mark);
                return;
            }
            probe = Math.abs(index - (int) Math.pow(i, 2) * prime.currentPrime()) % array.length;
            if (array[probe] == null) {
                array[probe] = new KeyValuePair(studentNumber, mark);
                return;
            }
//            int probe = Math.abs(index + (i % 2 == 0 ? 1 : -1) * (int) (Math.pow((i + 1) / 2, 2)) * prime.currentPrime()) % array.length;
//            if (array[probe] == null) {
//                array[probe] = new KeyValuePair(studentNumber, mark);
//                return;
//            }
        }

        resize();
        insert(studentNumber, mark);
    }

    public void remove(int studentNumber) {
        int index = searchIndex(studentNumber);
        if (index != -1) {
            array[index] = null;
        }
    }
}
