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

// Concrete Square class
class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

// ShapeFactory class
class ShapeFactory {
    // Factory method to create shapes
    public static Shape createShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        } else {
            throw new IllegalArgumentException("Invalid shape type: " + shapeType);
        }
    }
}

public class FactoryPatternExample {
    public static void main(String[] args) {
        // Create shapes using the factory
        Shape circle = ShapeFactory.createShape("CIRCLE");
        Shape rectangle = ShapeFactory.createShape("RECTANGLE");
        Shape square = ShapeFactory.createShape("SQUARE");

        // Draw the shapes
        circle.draw();
        rectangle.draw();
        square.draw();
    }
}
