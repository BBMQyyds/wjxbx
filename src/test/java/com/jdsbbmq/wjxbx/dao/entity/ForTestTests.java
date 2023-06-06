package com.jdsbbmq.wjxbx.dao.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ForTestTests {

    @Test
    void testAllArgsConstructor() {
        // 准备测试数据
        String a = "valueA";
        String b = "valueB";

        // 创建对象
        ForTest forTest = new ForTest(a, b);

        // 断言属性值是否正确设置
        Assertions.assertEquals(a, forTest.getA());
        Assertions.assertEquals(b, forTest.getB());
    }

    @Test
    void testNoArgsConstructor() {
        // 创建对象
        ForTest forTest = new ForTest();

        // 断言属性值是否为默认值
        Assertions.assertNull(forTest.getA());
        Assertions.assertNull(forTest.getB());
    }

    @Test
    void testSetterGetter() {
        // 创建对象
        ForTest forTest = new ForTest();

        // 设置属性值
        String a = "valueA";
        String b = "valueB";
        forTest.setA(a);
        forTest.setB(b);

        // 断言属性值是否正确获取
        Assertions.assertEquals(a, forTest.getA());
        Assertions.assertEquals(b, forTest.getB());
    }

    @Test
    void testEqualsAndHashCode() {
        // 创建两个相同属性值的对象
        ForTest forTest1 = new ForTest("valueA", "valueB");
        ForTest forTest2 = new ForTest("valueA", "valueB");

        // 断言对象相等
        Assertions.assertEquals(forTest1, forTest2);
        Assertions.assertEquals(forTest1.hashCode(), forTest2.hashCode());

        // 创建一个不同属性值的对象 valueY不同
        ForTest forTest3 = new ForTest("valueA", "valueY");

        // 断言对象不相等
        Assertions.assertNotEquals(forTest1, forTest3);
        Assertions.assertNotEquals(forTest1.hashCode(), forTest3.hashCode());

        //和空对象进行比较
        Assertions.assertNotEquals(forTest1, null);

        //与无参构造进行比较
        ForTest forTest5 = new ForTest();
        Assertions.assertNotEquals(forTest1, forTest5);
        Assertions.assertNotEquals(forTest1.hashCode(), forTest5.hashCode());

        //与自身进行比较
        Assertions.assertEquals(forTest1, forTest1);
        Assertions.assertEquals(forTest1.hashCode(), forTest1.hashCode());

        //与无参构造后有一个参数通过set注入的进行比较
        ForTest forTest6 = new ForTest();
        forTest6.setA("valueA");
        Assertions.assertNotEquals(forTest1, forTest6);
        Assertions.assertNotEquals(forTest1.hashCode(), forTest6.hashCode());

        //无参构造间的比较
        Assertions.assertNotEquals(forTest5, forTest6);
        Assertions.assertNotEquals(forTest5.hashCode(), forTest6.hashCode());

        //两个无参构造相等
        ForTest forTest9= new ForTest();
        Assertions.assertEquals(forTest5, forTest9);
        Assertions.assertEquals(forTest5.hashCode(), forTest9.hashCode());
    }

    @Test
//    hashcode()的测试类
    public void hashcodeTest() {
        ForTest forTest = new ForTest();
        if (forTest.hashCode() == 0) {
            System.out.println("hashcode测试失败");
        } else {
            System.out.println("hashcode测试成功");
        }
    }
}
