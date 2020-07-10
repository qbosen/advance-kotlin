package creational.abstract_factory.java.products;

/**
 * @author qiubaisen
 * @date 2020/7/10
 */
public abstract class Sofa {
    public void sit() {
        System.out.println(String.format("坐在沙发上，感觉[%s]", feels()));
    }

    public abstract String feels();
}
