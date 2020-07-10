package creational.abstract_factory.java.products;

/**
 * @author qiubaisen
 * @date 2020/7/10
 */
public abstract class Chair {
    public void sit(){
        System.out.println(String.format("坐在一个[%s]风格的椅子上", style()));
    }

    public abstract String style();
}
