package inheritance.protected2;

import inheritance.protected1.ParentOrder;

public class KidOrder extends ParentOrder {
    public void test(){
        /**
         * 不同包中，如果子类继承了另一个包中的父类：
         *      父类中protected和public权限修饰符的属性和方法在子类中都是可见的
         */
        this.protectedVal = 1;
        this.publicVal = 1;
    }
}
