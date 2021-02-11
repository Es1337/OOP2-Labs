public class Enums {
    public enum Direction {
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, -1),
        DOWN(0, 1);

        private int[] vector = new int[2];

        /**
         * Sets up vector as 2D
         * @param x integer -1/0/1
         * @param y integer -1/0/1
         */
        private Direction(int x, int y) {
            vector[0] = x;
            vector[1] = y;
        }

        public int getX() {
            return vector[0];
        }

        public int getY() {
            return vector[1];
        } 

        @Override
        public String toString() {
            return String.format("[%d, %d]", getX(), getY());
        }
    }

    public enum Option {
        RESET('0'),
        LEFT('a'),
        RIGHT('d'),
        UP('w'),
        DOWN('s'),
        EXIT('q');

        private char key;
        private String description;
        private Direction direc;

        /**
         * Sets up direction and/or description fields based on key set up with setKey
         * In case of unrecognized ket sets description to "Wrong key" and direction to null
         * @param setKey char representing player choice
         */
        private Option(char setKey) {
            key = setKey;

            switch (key) {
                case '0': description = "Reset the board"; direc = null; break;
                case 'q': description = "End the game"; direc = null; break;
                case 'w': description = "Go Up"; direc = Direction.UP; break;
                case 's': description = "Go Down"; direc = Direction.DOWN; break;
                case 'a': description = "Go Left"; direc = Direction.LEFT; break;
                case 'd': description = "Go Right"; direc = Direction.RIGHT; break;
                default: description = "Wrong key"; direc = null; break;
            }
        }

        public char getKey() {
            return key;
        }

        public String getDescription() {
            return description;
        }

        public Direction getDirection() {
            return direc;
        }

        @Override
        public String toString() {
            if(direc != null)
                return String.format("%c ==> opcja %s, opis: %s, wektor przesuniecia: %s", getKey(), super.toString(), getDescription(), getDirection());
            else
                return String.format("%c ==> opcja %s, opis: %s", getKey(), super.toString(), getDescription());
        }
    }
}
