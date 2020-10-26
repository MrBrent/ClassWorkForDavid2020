import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUser {
    @Test
    void testUser() {
        String firstName = "Jacob";
        String lastName = "Nash";
        String email = "Jacob.Nash@intel.com";
        String username = "JNashty";
        String password = "12345";

        User uut = new User(firstName, lastName, email, username, password);

        assertEquals(firstName, uut.getFirstName());
        assertEquals(lastName, uut.getLastName());
        assertEquals(email, uut.getEmail());
        assertEquals(username, uut.getUsername());
        assertEquals(password, uut.getPassword());
    }
}
