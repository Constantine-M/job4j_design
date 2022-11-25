package ru.job4j.ood.lsp.violations;

/**
 * Данный пример показывает нарушение LSP.
 * Здесь ослаблено постусловие в подклассе
 * {@link Boat} - вместо требования как
 * минимум 3 колес, было выставлено
 * условие в 0 колес, чтобы транспорт поехал.
 * Вроде как лодка - это тоже траспорт...
 * В качестве решения можно использовать
 * интерфейс.
 */
public class TransportTest {

    private static class Transport {

        private int wheels;

        public Transport(int wheels) {
            this.wheels = wheels;
        }

        public String move(int wheels) {
            if (wheels < 3) {
                throw new IllegalArgumentException("Need more wheels");
            }
            return "Let's go!";
        }
    }

    private static class Boat extends Transport {

        public Boat(int wheels) {
            super(wheels);
        }

        @Override
        public String move(int wheels) {
            if (wheels < 0) {
                throw new IllegalArgumentException("Need more wheels");
            }
            return "Let's go!";
        }
    }
}
