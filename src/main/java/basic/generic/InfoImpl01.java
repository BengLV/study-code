package basic.generic;


/**
 * 传入泛型实参时：不是泛型类
 * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
 * 即：InfoImpl01<T>，public String getVar();中的的T都要替换成传入的String类型。
 */
public class InfoImpl01 implements Info<String> {

    @Override
    public String getVar() {
        return null;
    }

}
