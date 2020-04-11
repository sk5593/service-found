package com.service.found.test;

public class danli {

}

/**
 * 懒汉式，顾名思义，就是需要的时候才去创建
 */
class lanhan {
    private static lanhan lanhan;
    private lanhan() {}
    private static lanhan getInstance(){
        if (lanhan == null){
            lanhan = new lanhan();
        }
        return lanhan;
    }
}
class ehan {
    private static ehan e = new ehan();
    private ehan(){}
    public static ehan getInstance(){
        return  e;
    }
}
/**
 * 双重检验锁
 */
class doubleCheck{
    private static doubleCheck dc;
    private doubleCheck(){}
    public static doubleCheck getInstance(){
        if (dc != null){
            synchronized (doubleCheck.class){
                /**
                 * 为什么需要第二次检验
                 * 如果不加检查的话，两个线程同时在synchronized外面等待，一个拿到锁创建对象，释放锁，
                 * 另一个线程拿到锁，又创建了一次对象。
                 */
                if (dc != null){
                    dc = new doubleCheck();
                }
            }
        }
        return dc;
    }
}
/**
 * 内部类模式创建锁,即使在多线程情况下，也会保证线程安全
 */
class neibulei{
    public static neibulei getInstance(){
        return nei.neibulei;
    }
    private static class nei{
        public static final neibulei neibulei  = new neibulei();
    }
}