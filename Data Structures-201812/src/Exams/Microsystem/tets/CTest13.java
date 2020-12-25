package Exams.Microsystem.tets;

import Exams.Microsystem.Brand;
import Exams.Microsystem.Computer;
import Exams.Microsystem.Microsystem;
import Exams.Microsystem.MicrosystemImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CTest13 {
    private Microsystem microsystems;

    @Before
    public void init() {
        this.microsystems = new MicrosystemImpl();
    }

    @Test
    public void remove_with_brand_should_not_contain_removed_computers() {

        Computer computer = new Computer(1, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(3, Brand.DELL, 2300, 15.6, "grey");
        Computer computer3 = new Computer(4, Brand.DELL, 2300, 15.6, "grey");
        Computer computer4 = new Computer(5, Brand.ACER, 2300, 15.6, "grey");

        //Act
        this.microsystems.createComputer(computer);
        this.microsystems.createComputer(computer2);
        this.microsystems.createComputer(computer3);
        this.microsystems.createComputer(computer4);
        this.microsystems.removeWithBrand(Brand.DELL);

        //Assert

        Assert.assertFalse(this.microsystems.contains(1));
        Assert.assertFalse(this.microsystems.contains(3));
        Assert.assertFalse(this.microsystems.contains(4));
    }
}
