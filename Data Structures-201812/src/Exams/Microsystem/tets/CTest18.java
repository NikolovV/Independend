package Exams.Microsystem.tets;

import Exams.Microsystem.Brand;
import Exams.Microsystem.Computer;
import Exams.Microsystem.Microsystem;
import Exams.Microsystem.MicrosystemImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.StreamSupport;

public class CTest18 {
    private Microsystem microsystems;

    @Before
    public void init() {
        this.microsystems = new MicrosystemImpl();
    }

    @Test
    public void getAllFromBrand_should_return_correct_order() {

        Computer computer = new Computer(1, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(3, Brand.DELL, 2200, 15.6, "grey");
        Computer computer3 = new Computer(4, Brand.DELL, 2800, 15.6, "grey");
        Computer computer4 = new Computer(5, Brand.ACER, 2300, 15.6, "grey");

        this.microsystems.createComputer(computer);
        this.microsystems.createComputer(computer2);
        this.microsystems.createComputer(computer3);
        this.microsystems.createComputer(computer4);

        Computer[] computers = StreamSupport.stream(this.microsystems.getAllFromBrand(Brand.DELL).spliterator(), false).
                toArray(Computer[]::new);

        Assert.assertEquals("Order is not correct", 4, computers[0].getNumber());
        Assert.assertEquals("Order is not correct", 1, computers[1].getNumber());
        Assert.assertEquals("Order is not correct", 3, computers[2].getNumber());
    }
}
