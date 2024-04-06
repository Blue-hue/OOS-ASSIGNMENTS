import java.lang.reflect.*;

class q4 {
    public static void main(String[] args) {
        System.out.println("[be22122@localhost Assignment3]$ javac q4.java");
        System.out.println("[be22122@localhost Assignment3]$ java q4");
        MyClass myObject = new MyClass();

        // getClass()
        Class<?> myClass = myObject.getClass();
        System.out.println("Class Name: " + myClass.getName());

        // getMethods()
        Method[] methods = myClass.getMethods();
        System.out.println("Methods:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        // getConstructors()
        Constructor<?>[] constructors = myClass.getConstructors();
        System.out.println("Constructors:");
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.getName());
        }

        // getDeclaredMethod()
        try {
            Method setNameMethod = myClass.getDeclaredMethod("setName", String.class);
            System.out.println("Declared method: " + setNameMethod.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // getDeclaredField()
        try {
            Field ageField = myClass.getDeclaredField("age");
            System.out.println("Declared field: " + ageField.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // setAccessible()
        try {
            Field ageField = myClass.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.setInt(myObject, 30);
            System.out.println("Age set via reflection: " + myObject.getAge());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class MyClass {
    private String name;
    private int age;

    public MyClass() {}

    public MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
