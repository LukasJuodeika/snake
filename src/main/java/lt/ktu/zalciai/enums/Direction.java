package lt.ktu.zalciai.enums;

public enum Direction {
    UP{
        @Override
        public boolean isOpposite(Direction direction) {
            return direction == DOWN;
        }
    },
    DOWN {
        @Override
        public boolean isOpposite(Direction direction) {
            return direction == UP;
        }
    },
    RIGHT {
        @Override
        public boolean isOpposite(Direction direction) {
            return direction == LEFT;
        }
    },
    LEFT {
        @Override
        public boolean isOpposite(Direction direction) {
            return direction == RIGHT;
        }
    };

    abstract public boolean isOpposite(Direction direction);
}
