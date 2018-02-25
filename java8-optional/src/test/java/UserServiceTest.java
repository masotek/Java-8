import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserServiceTest {

    private UserService userService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testFindUserWhenUserExists() {
        assertThat(userService.findUser("kowalski").isPresent(), is(true));
        assertThat(userService.findUser("kowalski").get().getFirstName(), is("Jan"));
    }

    @Test
    public void testFindUserWhenUserDoesNotExist() {
        assertThat(userService.findUser("kowalczyk").isPresent(), is(false));
    }

    @Test
    public void testGetUserDetailsWhenUserExists() {
        assertThat(userService.getUserDetails("nowak"),
            is("User{firstName='Andrzej', lastName='Nowak', age=45}"));
    }

    @Test
    public void testGetUserDetailsWhenUserDoesNotExist() {
        assertThat(userService.getUserDetails("nowaczyk"), is(""));
    }

    @Test
    public void testAuthorizeUserWhenUserExists() {
        userService.authorizeUser("kowalski");
    }

    @Test
    public void testAuthorizeUserWhenUserDoesNotExist() {
        expectedException.expect(AccessDeniedException.class);
        expectedException.expectMessage("User not authorized");

        userService.authorizeUser("kowalczuk");
    }
}