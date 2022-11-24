package ru.job4j.ood.ocp.violations.shapes;

/**
 * В данном класс я попытался
 * показать пример нарушения принципа
 * OCP.
 * Здесь, в методе drawShape()
 * мы передаем абстрактную фигуру,
 * а на выходе получаем нужную фигуру
 * путем casting-а к нужной.
 */
public abstract class Shape {

    private ShapeType type;

    public Shape(ShapeType shapeType) {
        this.type = shapeType;
    }

    public static void drawShape(Shape s) {
        if (s.type == ShapeType.SQUARE) {
            s = null;
            /**cast к квадрату и вызов метода draw()*/
        } else if (s.type == ShapeType.CIRCLE) {
            s = null;
            /**cast к кругу и вызов метода draw()*/
        }
    }

    public void draw() {
    }

}
