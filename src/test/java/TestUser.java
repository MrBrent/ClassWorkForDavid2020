import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUser {
    @Test
    void testUser() {
        User uut = new User("Jacob", "Nash", "Jacob.Nash@intel.com", "JNashty", "12345");
        assertEquals(uut.getFirstName(), "Jacob");
        assertEquals(uut.getLastName(), "Nash");
        assertEquals(uut.getEmail(), "Jacob.Nash@intel.com");
        assertEquals(uut.getUsername(), "JNashty");
        assertEquals(uut.getPassword(), "12345");
    }
}
