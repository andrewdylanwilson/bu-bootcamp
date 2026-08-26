import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class ContactTest {

    private Contact contact;

  @BeforeEach
  void setUp() {
    contact = new Contact("Ada Lovelace", "+1 617 555 0101");
  }

  @Test
  void constructor_setsNameCorrectly() {
    Contact c = new Contact("Ada Lovelace", "+1 617 555 0101");
    assertEquals("Ada Lovelace", c.getName());
  }

  @Test
  void constructor_setsPhoneCorrectly() {
    Contact c = new Contact("Ada Lovelace", "+1 617 555 0101");
    assertEquals("+1 617 555 0101", c.getPhone());
  }

  @Test
  void getName_returnsExactString_notTransformed() {
    assertEquals("Ada Lovelace", contact.getName());
  }

  @Test
  void toString_containsName() {
    Contact c = new Contact("Alan Turing", "555-0001");
    assertTrue(c.toString().contains("Alan Turing"));
  }

  @Test
  void toString_containsPhone() {
    Contact c = new Contact("Alan Turing", "555-0001");
    assertTrue(c.toString().contains("555-0001"));
  }

  @Test
  void twoObjects_withSameName_areIndependent() {
    Contact c1 = new Contact("Ada Lovelace", "555-0001");
    Contact c2 = new Contact("Ada Lovelace", "555-0002");
    assertNotSame(c1, c2);
    assertEquals("Ada Lovelace", c1.getName());
    assertEquals("Ada Lovelace", c2.getName());
    assertEquals("555-0001", c1.getPhone());
    assertEquals("555-0002", c2.getPhone());
  }
}
