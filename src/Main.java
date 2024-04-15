public class Main {
    public static int Suites_Run = 0;
    public static int Suites_Passed = 0;

    public static int Tests_Run = 0;
    public static int Tests_Passed = 0;

    static StringBuilder suites = new StringBuilder();

    public static void end_tests() {
        if (Suites_Passed == Suites_Run) {
            System.out.println("All Suites were passed");
        } else {
            System.out.println("Some Suites Failed : " + Suites_Passed + "/" + Suites_Run + '\n' + suites.toString());
        }
    }

    public static void start_suite(String name) {
        Suites_Run++;
        System.out.print(name + "\n==========================================\n");
        suites.append(name).append(": ");
    }

    public static void end_suite() {
        if (Tests_Run == Tests_Passed) {
            Suites_Passed++;
            System.out.print("All tests have passed\n");
            suites.append("Passed\n");
        } else {
            System.out.print("Some Tests Failed : " + Tests_Passed + "/" + Tests_Run + "\n\n");
            suites.append("Failed\n");
        }
        System.out.println("==========================================\n");
        Tests_Run = 0;
        Tests_Passed = 0;
    }

    public static boolean assertEquals(String test, String got, String expected) {
        Tests_Run++;
        if (got.equals(expected)) {
            Tests_Passed++;
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Passed\n");
            return true;
        } else {
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Failed\n" + "You got : \n" + got + "\nYou expected : \n" + expected + "\n");
            return false;
        }
    }

    public static boolean assertEquals(String test, boolean got, boolean expected) {
        Tests_Run++;
        if (got == expected) {
            Tests_Passed++;
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Passed\n");
            return true;
        } else {
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Failed\n" + "You got : \n" + got + "\nYou expected : \n" + expected + "\n");
            return false;
        }
    }

    public static boolean assertEquals(String test, int got, int expected) {
        Tests_Run++;
        if (got == expected) {
            Tests_Passed++;
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Passed\n");
            return true;
        } else {
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Failed\n" + "You got : \n" + got + "\nYou expected : \n" + expected + "\n");
            return false;
        }
    }

    private static String[] insertStrings1 = {"2[u10:5%]",
            "3[u11:5%,u10:5%]",
            "5[-,u10:5%,u11:5%,u12:5%]",
            "5[u13:5%,u10:5%,u11:5%,u12:5%]",
            "7[u11:5%,u12:5%,u13:5%,u14:5%,-,-,-,u10:5%]",
            "7[u11:5%,u12:5%,u13:5%,u14:5%,u15:5%,-,-,u10:5%]",
            "7[u11:5%,u12:5%,u13:5%,u14:5%,u15:5%,u16:5%,-,u10:5%]",
            "7[u11:5%,u12:5%,u13:5%,u14:5%,u15:5%,u16:5%,u17:5%,u10:5%]",
            "11[u15:5%,u16:5%,u17:5%,u18:5%,-,-,-,-,-,-,-,u10:5%,u11:5%,u12:5%,u13:5%,u14:5%]",
            "11[u15:5%,u16:5%,u17:5%,u18:5%,u19:5%,-,-,-,-,-,-,u10:5%,u11:5%,u12:5%,u13:5%,u14:5%]"};

    public static void main(String[] args) {
        start_suite("Prime Number Generator");

        PrimeNumberGenerator primeNumberGenerator1 = new PrimeNumberGenerator();

        assertEquals("Default Constructor", primeNumberGenerator1.toString(), "[2]");

        primeNumberGenerator1.nextPrime();

        assertEquals("NextPrime once", primeNumberGenerator1.toString(), "[3]");

        primeNumberGenerator1 = new PrimeNumberGenerator();
        for (int i = 0; i < 4; i++) {
            primeNumberGenerator1.nextPrime();
        }

        assertEquals("NextPrime 4 times", primeNumberGenerator1.toString(), "[11]->[13]");

        primeNumberGenerator1 = new PrimeNumberGenerator();
        for (int i = 0; i < 9; i++) {
            primeNumberGenerator1.nextPrime();
        }

        assertEquals("NextPrime 9 times", primeNumberGenerator1.toString(), "[29]->[31]->[37]->[41]->[43]");

        primeNumberGenerator1 = new PrimeNumberGenerator();
        for (int i = 0; i < 14; i++) {
            primeNumberGenerator1.nextPrime();
        }


        assertEquals("NextPrime 14 times", primeNumberGenerator1.toString(), "[47]->[53]->[59]->[61]->[67]" +
                "->[71]->[73]->[79]->[83]");

        primeNumberGenerator1 = new PrimeNumberGenerator();
        for (int i = 0; i < 23; i++) {
            primeNumberGenerator1.nextPrime();
        }


        assertEquals("NextPrime 23 times", primeNumberGenerator1.toString(), "[89]->[97]->[101]->[103]->[107]" +
                "->[109]->[113]->[127]->[131]->[137]->[139]->[149]->[151]->[157]->[163]");

        assertEquals("CurrentPrime after 23 nextPrimes", primeNumberGenerator1.currentPrime(), 89);

        end_suite(); // Prime Number Generator

        //======================================================================

        start_suite("Hashmap Constructors");

        Hashmap hashmap0 = new Hashmap();

        assertEquals("Default Constructor", hashmap0.toStringOneLine(), "2[-]");

        Hashmap hashmap1 = new Hashmap("7[-,u12:5%,u13:5%,u14:5%,u15:5%,-,-,u10:5%]");

        assertEquals("First String Input", hashmap1.toStringOneLine(), "7[-,u12:5%,u13:5%,u14:5%,u15:5%,-,-,u10:5%]");

        Hashmap hashmap2 = new Hashmap("11[u15:5%,u16:5%,u17:5%,u18:5%,u19:5%,-,-,-,-,-,-,u10:5%,-,u12:5%,u13:5%,u14:5%]");

        assertEquals("Second String Input", hashmap2.toStringOneLine(), "11[u15:5%,u16:5%,u17:5%,u18:5%,u19:5%,-,-,-,-,-,-,u10:5%,-,u12:5%,u13:5%,u14:5%]");


        end_suite(); // Hashmap Constructors

        //=======================================================================

        start_suite("Hashmap Insert");

        Hashmap hashmap3 = new Hashmap();

        for (int i = 10; i < 20; i++) {
            hashmap3.insert(i, 5);
            assertEquals("Insert " + i, hashmap3.toStringOneLine(), insertStrings1[i - 10]);
        }

        hashmap3.insert(16, 5);
        assertEquals("Insert " + 16, hashmap3.toStringOneLine(), insertStrings1[9]);

        hashmap3.insert(255, 85);
        assertEquals("Insert " + 255, hashmap3.toStringOneLine(), "11[u15:5%,u16:5%,u17:5%,u18:5%,u19:5%,-,-,-,-,u255:85%,-,u10:5%,u11:5%,u12:5%,u13:5%,u14:5%]");

        hashmap3.insert(1000, 5);
        assertEquals("Insert " + 1000, hashmap3.toStringOneLine(), "11[u15:5%,u16:5%,u17:5%,u18:5%,u19:5%,-,-,-,u1000:5%,u255:85%,-,u10:5%,u11:5%,u12:5%,u13:5%,u14:5%]");

        hashmap3.insert(456, 5);
        assertEquals("Insert " + 456, hashmap3.toStringOneLine(), "11[u15:5%,u16:5%,u17:5%,u18:5%,u19:5%,-,-,-,u1000:5%,u255:85%,u456:5%,u10:5%,u11:5%,u12:5%,u13:5%,u14:5%]");

        hashmap3.insert(321, 5);
        assertEquals("Insert " + 321, hashmap3.toStringOneLine(), "11[u15:5%,u16:5%,u17:5%,u18:5%,u19:5%,u321:5%,-,-,u1000:5%,u255:85%,u456:5%,u10:5%,u11:5%,u12:5%,u13:5%,u14:5%]");

        System.out.println(hashmap3);
        end_suite(); // Hashmap Insert

        //=======================================================================

        start_suite("Hashmap Search");

        assertEquals("Search 15 in hashmap3", hashmap3.search(15).studentNumber, 15);
        assertEquals("Search 19 in hashmap3", hashmap3.search(19).studentNumber, 19);
        assertEquals("Search 13 in hashmap3", hashmap3.search(13).studentNumber, 13);
        assertEquals("Search 14 in hashmap3", hashmap3.search(14).studentNumber, 14);
        assertEquals("Search 12 in hashmap3", hashmap3.search(12).studentNumber, 12);
        assertEquals("Search 321 in hashmap3", hashmap3.search(321).studentNumber, 321);

        end_suite(); // Hashmap Search

        //=======================================================================

        start_suite("Hashmap Remove");

        hashmap3.remove(16);
        assertEquals("Remove 16 from hashmap3", hashmap3.toStringOneLine(), "11[u15:5%,-,u17:5%,u18:5%,u19:5%,u321:5%,-,-,u1000:5%,u255:85%,u456:5%,u10:5%,u11:5%,u12:5%,u13:5%,u14:5%]");


        end_suite(); // Hashmap Remove

        end_tests();

    }
}