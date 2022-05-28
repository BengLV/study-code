package basic.generic;


/**
 * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
 * 即：class InfoImpl<T> implements Info<T>
 * 如果不声明泛型，如：class InfoImpl implements Info<T>，编译器会报错："Unknown class"
 */
public class InfoImpl<T> implements Info<T> {

    @Override
    public T getVar() {
        return null;
    }
}
