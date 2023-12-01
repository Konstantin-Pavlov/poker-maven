import org.example.Greet;

import org.junit.Assert;
public class Test {

    @org.junit.Test
    public void t0(){
        Assert.assertEquals("hello", new Greet().says());
    }

    @org.junit.Test
    public void t1(){
        Assert.assertEquals("helo", new Greet().says());
    }
}
