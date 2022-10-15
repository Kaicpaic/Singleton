interface IConnection {

    char get(int index);

    void set(int index, char c);

    int length();

}

final class Base {  // FINAL CLASS TO BLOCK INITIATION AND E.G. CLONING OR SERIALIZATION
    private static Base instance = new Base();  // EARLY INITIALIZATION
    private char[] tab = new char[100];

    private Base() {

    }

    public static Base getInstance() {
/*
        LATE INITIALIZATION:

        if (instance == null) {
            instance = new Base();
        }
*/
        return instance;
    }

    public static IConnection getConnection() {

        return Connection.getInstance();

    }

    private static class Connection implements IConnection {

        private static Base base = Base.getInstance();
        private static int sequence = 0;
        //EARLY INITIALIZATION:
        private static Connection instances[] = {new Connection(), new Connection(), new Connection()};

        private Connection() {
        }

        public static IConnection getInstance() {
            sequence = (sequence + 1) % instances.length;
            return instances[sequence];
        }

        public char get(int index) {
            return base.tab[index];
        }

        public void set(int index, char c) {
            base.tab[index] = c;
        }

        public int length() {
            return base.tab.length;
        }

    }

}

public class Main {
    public static void main(String[] args) {
        IConnection connection1 = Base.getConnection();
        IConnection connection2 = Base.getConnection();
        IConnection connection3 = Base.getConnection();
        IConnection connection4 = Base.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
        System.out.println(connection4);

        System.out.print("\nAdding indexes");
        connection1.set(1, '1');
        connection2.set(2, '2');
        connection3.set(3, '3');
        connection4.set(4, '4');
        System.out.println(".\n");

        System.out.println("Connection 1:");
        System.out.println(connection1.get(1));
        System.out.println(connection1.get(2));
        System.out.println(connection1.get(3));
        System.out.println(connection1.get(4));
        System.out.println("■\n");

        System.out.println("Connection 2:");
        System.out.println(connection2.get(1));
        System.out.println(connection2.get(2));
        System.out.println(connection2.get(3));
        System.out.println(connection2.get(4));
        System.out.println("■\n");

        System.out.println("Connection 3:");
        System.out.println(connection3.get(1));
        System.out.println(connection3.get(2));
        System.out.println(connection3.get(3));
        System.out.println(connection3.get(4));
        System.out.println("■\n");

        System.out.println("Connection 4:");
        System.out.println(connection4.get(1));
        System.out.println(connection4.get(2));
        System.out.println(connection4.get(3));
        System.out.println(connection4.get(4));
        System.out.println("■");
    }
}