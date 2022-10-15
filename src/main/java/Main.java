interface IPolaczenie {

    char get(int indeks);

    void set(int indeks, char c);

    int length();

}

class Baza {

    private char[] tab = new char[100]; /* ... */

    public static IPolaczenie getPolaczenie() {

        return Polaczenie.getInstance();

    }

    private static class Polaczenie implements IPolaczenie {

        private Baza baza; /* ... */

        public static IPolaczenie getInstance() {

            /* ... */

        }

        public char get(int indeks) {

            return baza.tab[indeks];

        }

        public void set(int indeks, char c) {

            baza.tab[indeks] = c;

        }

        public int length() {

            return baza.tab.length;

        }

    }

}