import java.util.Scanner;
class Shape {
    public void area() {
        System.out.println("Not implemented.");
    }
}
class Rectangle extends Shape {
    private double length;
    private double width;
    public Rectangle(double length, double width) {                                                            
        this.length = length;
        this.width = width;
    }
    public void area() {
        double area = length * width;
        System.out.println("Area of Rectangle: " + area);
    }
}
class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public void area() {
        double area = Math.PI * radius * radius;
        System.out.println("Area of Circle: " + area);
    }
}
public class ShapeDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter length of rectangle: ");
        double length = scanner.nextDouble();
        System.out.print("Enter width of rectangle: ");
        double width = scanner.nextDouble();
        System.out.print("Enter radius of circle: ");
        double radius = scanner.nextDouble();
        Shape shape1 = new Rectangle(length, width);
        Shape shape2 = new Circle(radius);
        shape1.area();
        shape2.area();
        scanner.close();
    }
}
