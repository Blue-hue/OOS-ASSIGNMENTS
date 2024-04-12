// Shape interface
interface Shape {
    void draw();
}

// Concrete Circle class
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

// Concrete Rectangle class
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

// Abstract ShapeDecorator class
abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}

// Concrete RedShapeDecorator class
class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Setting red border");
    }
}

public class DecoratorPatternExample {
    public static void main(String[] args) {
        // Create a circle
        Shape circle = new Circle();

        // Decorate the circle with a red border
        Shape redCircle = new RedShapeDecorator(new Circle());
        
        // Create a rectangle
        Shape rectangle = new Rectangle();

        // Decorate the rectangle with a red border
        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        // Draw shapes
        System.out.println("Normal Circle:");
        circle.draw();

        System.out.println("\nCircle with Red Border:");
        redCircle.draw();

        System.out.println("\nNormal Rectangle:");
        rectangle.draw();

        System.out.println("\nRectangle with Red Border:");
        redRectangle.draw();
    }
}
