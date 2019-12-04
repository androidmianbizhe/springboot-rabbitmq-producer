package com.lihao;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class ArgumentsMatcherTest {

    @Test
    public void basicTest() {

        List<String> list = mock(ArrayList.class);

        when(list.get(0)).thenReturn("asd");

        assertThat(list.get(0), equalTo("asd"));
        assertThat(list.get(1), nullValue());

    }

    @Test
    public void testComplex() {

        Foo foo = mock(Foo.class);

        when(foo.fun(Mockito.isA(Child1.class))).thenReturn(100);
        assertThat(foo.fun(new Child1()), equalTo(100));
        assertThat(foo.fun(new Child2()), equalTo(0));
    }

    class Foo {
        int fun(Parent p) {
            return p.work();
        }
    }


    interface Parent {
        int work();
    }

    public class Child1 implements Parent {

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    public class Child2 implements Parent {

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }


    public interface Fun {
        boolean checkDulp(String code);
    }

    public class FunImpl implements Fun {

        public final Bar bar;

        public FunImpl(Bar bar) {
            this.bar = bar;
        }

        @Override
        public boolean checkDulp(String code) {
            return bar.getList().contains(code);
        }
    }

    interface Bar{

        List<String> getList();
    }

    class BarImpl implements Bar {

        @Override
        public List<String> getList() {
            throw new RuntimeException();
        }
    }

    @Test
    public void testbar () {

        Bar bar = Mockito.mock(BarImpl.class);
        Fun fun = new FunImpl(bar);

        List<String> list = new ArrayList<>();
        list.add("12");
        list.add("34");

        when(bar.getList()).thenReturn(list);
        Assert.assertThat(fun.checkDulp("12"), equalTo(true));

    }



}
