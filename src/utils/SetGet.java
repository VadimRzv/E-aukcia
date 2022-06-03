package utils;

public class SetGet<Type> {
    private Type type;

    public SetGet(){}
    public SetGet(Type type){this.type = type;}
    public void set(Type type){this.type = type;}
    public Type get(){return type;}
}
