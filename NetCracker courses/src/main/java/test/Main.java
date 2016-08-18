package test;

/**
 * Created by Maks on 27.03.2016.
 */
class A {
    void f(){
        System.out.println("Method of A");
    }
}

class B extends A {
    void f(long i){
        System.out.println("Method of B");
    }
}

class C extends B {
    static void f(int i){
        System.out.println("Method of C");
    }
}

public class Main {
    public static void main(String[] args) {
        A a = new A();
        A b = new B();
        B c = new C();
        a.f();
        b.f();
        c.f();
    }
}