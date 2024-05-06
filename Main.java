import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        try {
            // Создание и инициализация объекта Cat
            Cat cat = createCatWithReflection("Cat", "Барсик", 5, 4.5);
            // Вывод информации о коте
            printCatInfo(cat);
        } catch (ReflectiveOperationException roe) {
            System.err.println("Ошибка при работе с рефлексией: " + roe.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла неизвестная ошибка: " + e.getMessage());
        }
    }

    /**
     * Создает объект класса Cat и устанавливает его поля с помощью рефлексии.
     *
     * @param className Название класса.
     * @param name Имя кота.
     * @param age Возраст кота.
     * @param weight Вес кота.
     * @return Объект класса Cat.
     * @throws ReflectiveOperationException если происходит ошибка рефлексии.
     */
    public static Cat createCatWithReflection(String className, String name, int age, double weight) throws ReflectiveOperationException {
        Class<?> clazz = Class.forName(className);
        Cat cat = (Cat) clazz.getDeclaredConstructor().newInstance();

        setFieldValue(cat, "name", name);
        setFieldValue(cat, "age", age);
        setFieldValue(cat, "weight", weight);

        return cat;
    }

    /**
     * Устанавливает значение поля объекта.
     *
     * @param object Объект, поле которого нужно изменить.
     * @param fieldName Название поля.
     * @param value Значение, которое нужно установить.
     * @throws ReflectiveOperationException если происходит ошибка рефлексии.
     */
    private static void setFieldValue(Object object, String fieldName, Object value) throws ReflectiveOperationException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        ((Field) field).set(object, value);
    }

    /**
     * Выводит информацию о коте.
     *
     * @param cat Объект класса Cat.
     */
    public static void printCatInfo(Cat cat) {
        System.out.println("Имя кота: " + cat.getName());
        System.out.println("Возраст кота: " + cat.getAge());
        System.out.println("Вес кота: " + cat.getWeight());
    }
}

class Cat {
    private String name;
    private int age;
    private double weight;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }
}