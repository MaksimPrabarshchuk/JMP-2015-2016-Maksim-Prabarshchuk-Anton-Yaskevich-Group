package com.epam;

import com.epam.dao.UserDAO;
import com.epam.model.User;
import com.epam.resource.UserResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {
    private Optional<User> user;
    private static final UserDAO dao = mock(UserDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new UserResource(dao))
            .build();

    @Before
    public void setUp() {
        user = Optional.of(new User(1L, "Anton", "Yaskevich"));
    }

    @After
    public void tearDown() {
        reset(dao);
    }

    @Test
    public void readTest() {
        when(dao.read(anyLong())).thenReturn(user);
        assertThat(resources
                .client()
                .target("/users/1")
                .request()
                .get(User.class))
                .isEqualToComparingFieldByField(user.get());
    }

    @Test
    public void createTest() {
        when(dao.create(any(User.class))).thenReturn(user);
        assertThat(resources
                .client()
                .target("/users")
                .request()
                .post(Entity.entity(user.get(), MediaType.APPLICATION_JSON_TYPE))
                .getStatusInfo())
                .isEqualTo(Response.Status.CREATED);
    }
}
