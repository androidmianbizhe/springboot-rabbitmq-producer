package com.lihao;

import com.lihao.stubbing.StubbingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {

    private List<String> list;

    @Before
    public void init() {
        this.list = mock(ArrayList.class);
    }

    @Test
    public void howToUseStubbing() {

        when(list.get(0)).thenReturn("first");
        assertThat(list.get(0), equalTo("first"));

        when(list.get(anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(0);
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }

    }

    @Test
    public void howToStubbingVoidMethod() {

        doNothing().when(list).clear();
        list.clear();

        //verify list clear method is run 1 times
        verify(list, times(1)).clear();

        doThrow(RuntimeException.class).when(list).clear();

        try {
            list.clear();
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void studdingToReturn() {
        when(list.get(0)).thenReturn("12");
        doReturn("23").when(list).get(1);

        assertThat(list.get(0), equalTo("12"));
        assertThat(list.get(1), equalTo("23"));
    }

    @Test
    public void iterateStubbing() {
        when(list.size()).thenReturn(1, 2, 3 ,4);
        when(list.size()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);

        assertThat(list.size(), equalTo(1));
        assertThat(list.size(), equalTo(2));
        assertThat(list.size(), equalTo(3));
        assertThat(list.size(), equalTo(4));
        assertThat(list.size(), equalTo(4));
    }


    @Test
    public void studdingWithAnswer() {
        when(list.get(anyInt())).thenAnswer(invocationOnMock->{
            Integer index = invocationOnMock.getArgument(0, Integer.class);
            return String.valueOf(index * 10);
        });
    }

    @Test
    public void stubbingWithRealCall() {

        StubbingService service = mock(StubbingService.class);
        when(service.getS()).thenReturn("Alex");
        assertThat(service.getS(), equalTo("Alex"));

        // call real method
        when(service.getI()).thenCallRealMethod();
        assertThat(service.getI(), equalTo(10));
    }

    @After
    public void destroy() {
        reset(this.list);
    }
}
