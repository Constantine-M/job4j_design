package ru.job4j.ood.srp.violationsrp;


/**
 * В данном классе нарушается принцип SRP.
 * 1.Здесь следует метод, который
 * занимается печатью, вынести в отдельный класс.
 * 2.Класс TextManipulator не зависит
 * от абстракции.
 */
public class TextManipulator {

    private String text;

    public TextManipulator(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void appendText(String newText) {
        text = text.concat(newText);
    }

    public String findWordAndReplace(String word, String replacementWord) {
        if (text.contains(word)) {
            text = text.replace(word, replacementWord);
        }
        return text;
    }

    public String findWordAndDelete(String word) {
        if (text.contains(word)) {
            text = text.replace(word, "");
        }
        return text;
    }

    public void printText() {
        TextManipulator manipulator = new TextManipulator("beee");
        System.out.println(manipulator.getText());
    }
}
