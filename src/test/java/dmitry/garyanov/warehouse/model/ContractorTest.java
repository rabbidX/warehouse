package dmitry.garyanov.warehouse.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ContractorTest {

    private Contractor contractor;
    @BeforeEach
    void setUp() {
        contractor = new Contractor();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
    }

    @Test
    void getName() {
        contractor.setName("ContractorName");
        String excepted = "ContractorName";
        String actual = contractor.getName();
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void getShipments() {
    }

    @Test
    void setId() {
    }

    @Test
    void setName() {
    }

    @Test
    void setShipments() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}