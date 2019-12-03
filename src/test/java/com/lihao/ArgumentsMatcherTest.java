package com.lihao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
}
