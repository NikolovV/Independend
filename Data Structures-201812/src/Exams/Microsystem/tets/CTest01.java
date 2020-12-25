package Exams.Microsystem.tets;

import Exams.Microsystem.Brand;
import Exams.Microsystem.Computer;
import Exams.Microsystem.Microsystem;
import Exams.Microsystem.MicrosystemImpl;
import org.junit.Before;
import org.junit.Test;

public class CTest01 {
    private Microsystem microsystem;

    @Before
    public void init() {
        this.microsystem = new MicrosystemImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createComputer_should_throw_exception() {

        Computer computer = new Computer(1, Brand.ASUS, 10D, 13.3, "red");
        Computer computer1 = new Computer(1, Brand.DELL, 11D, 14.3, "black");

        this.microsystem.createComputer(computer);
        this.microsystem.createComputer(computer1);

    }
}
