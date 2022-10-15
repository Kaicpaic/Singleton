interface IPolaczenie {

    char get(int indeks);

    void set(int indeks, char c);

    int length();

}

final class Baza {  // KLASA FINALNA ABY ZABLOKOWAĆ DZIDZICZENIE I NP. KLONOWANIE LUB SERIALIZACJĘ
    private static Baza instance = new Baza();  // WCZESNA INICJALIZACJA
    private char[] tab = new char[100];

    private Baza() {

    }

    public static Baza getInstance() {
/*
        PÓŹNA INICJALIZACJA

        if (instance == null) {
            instance = new Baza();
        }
*/
        return instance;
    }

    public static IPolaczenie getPolaczenie() {

        return Polaczenie.getInstance();

    }

    private static class Polaczenie implements IPolaczenie {

        private static Baza baza = Baza.getInstance();
        private static int kolejnosc = 0;
        // WCZESNA INICJALIZACJA:
        private static Polaczenie instances[] = {new Polaczenie(), new Polaczenie(), new Polaczenie()};

        private Polaczenie() {
        }

        public static IPolaczenie getInstance() {
/*
            PÓŹNA INICJALIZACJA

            if (instances == null) {
                instances = new Polaczenie[3];
            }
*/
            kolejnosc = (kolejnosc + 1) % instances.length;
            return instances[kolejnosc];
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

public class Main {
    public static void main(String[] args) {
        IPolaczenie polaczenie1 = Baza.getPolaczenie();
        IPolaczenie polaczenie2 = Baza.getPolaczenie();
        IPolaczenie polaczenie3 = Baza.getPolaczenie();
        IPolaczenie polaczenie4 = Baza.getPolaczenie();

        System.out.println(polaczenie1);
        System.out.println(polaczenie2);
        System.out.println(polaczenie3);
        System.out.println(polaczenie4);

        System.out.print("\nDodawanie indeksów");
        polaczenie1.set(1, '1');
        polaczenie2.set(2, '2');
        polaczenie3.set(3, '3');
        polaczenie4.set(4, '4');
        System.out.println(".\n");

        System.out.println("Połączenie 1:");
        System.out.println(polaczenie1.get(1));
        System.out.println(polaczenie1.get(2));
        System.out.println(polaczenie1.get(3));
        System.out.println(polaczenie1.get(4));
        System.out.println("■\n");

        System.out.println("Połączenie 2:");
        System.out.println(polaczenie2.get(1));
        System.out.println(polaczenie2.get(2));
        System.out.println(polaczenie2.get(3));
        System.out.println(polaczenie2.get(4));
        System.out.println("■\n");

        System.out.println("Połączenie 3:");
        System.out.println(polaczenie3.get(1));
        System.out.println(polaczenie3.get(2));
        System.out.println(polaczenie3.get(3));
        System.out.println(polaczenie3.get(4));
        System.out.println("■\n");

        System.out.println("Połączenie 4:");
        System.out.println(polaczenie4.get(1));
        System.out.println(polaczenie4.get(2));
        System.out.println(polaczenie4.get(3));
        System.out.println(polaczenie4.get(4));
        System.out.println("■");
    }
}