package com.lihao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    @Before
    public void init() {

    }

    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>();
        List<String> list = spy(realList);

        list.add("Mockito");
        list.add("PowerMockito");

        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("Mockito"));

    }
    @After
    public void destroy() {

    }
}
