public class Person {
    String name;
    int age;

    public Person() {
        name = "";
        age = 0;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void shuchu() {
        System.out.println("我叫" + name + "，今年" + age + "岁");
    }
}