package com.cts.signup.mockmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestTestController {
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testUserDetailsSaveSuccessfully() throws Exception {

		String USER_REQUEST = "{\"name\":\"Nimisha\",\"email\":\"n@gmail.com\",\"password\":\"111\",\"blackListStatus\":\"false\",\"language\":{\"id\": \"1\"}}";
		mockMvc.perform(post("/signup/save").content(USER_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());

	}

	@Test
	public void testPreExistingEmailId() throws Exception {

		String USER_REQUEST = "{\"name\":\"Nimisha\",\"email\":\"n@gmail.com\",\"password\":\"111\",\"blackListStatus\":\"false\",\"language\":{\"id\": \"1\"}}";

		mockMvc.perform(post("/signup/save").content(USER_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());

	}

	@Test
	public void testWrongUrl() throws Exception {

		String USER_REQUEST = "{\"name\":\"Nimisha\",\"email\":\"n@gmail.com\",\"password\":\"111\",\"blackListStatus\":\"false\",\"language\":{\"id\": \"1\"}}";

		mockMvc.perform(post("/signup/addd").content(USER_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError());

	}

	@Test
	public void usernameCanNotbeNull() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\":\"n@gmail.com\",\"password\":\"111\",\"blackListStatus\":\"false\",\"language\":{\"id\": \"1\"}}";

		mockMvc.perform(post("/signup/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Name cannot be empty"));
		;

	}

	@Test
	public void passwordCanNotbeNull() throws Exception {

		String EMPLOYEE_REQUEST = "{\"name\":\"Nimisha \",\"email\":\"n@gmail.com\",\"blackListStatus\":\"false\",\"language\":{\"id\": \"1\"}}";

		mockMvc.perform(post("/signup/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Password cannot be empty"));

	}

	@Test
	public void emailCanNotbeNull() throws Exception {

		String EMPLOYEE_REQUEST = "{\"name\":\"Nim\",\"password\":\"1234\",\"blackListStatus\":\"false\",\"language\":{\"id\": \"1\"}}";

		mockMvc.perform(post("/signup/add").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Email cannot be empty"));
		

	}

	@Test
	public void testWhenPasswordIsSmall() throws Exception {

		String USER_REQUEST = "{\"name\":\"Nimissshhhaaa\",\"email\":\"nimimim@gmail.com\",\"password\":\"44\",\"blackListStatus\":\"false\",\"language\":{\"id\": \"1\"}}";
		mockMvc.perform(post("/signup/save").content(USER_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError()).andExpect(jsonPath("$.errorMessage")
						.value("Input validation failed: Password must be 3 to 20 characters"));

	}

	@Test
	public void testWhenNameIsSmall() throws Exception {
		String USER_REQUEST = "{\"name\":\"nid\",\"email\":\"ankitani4@gmail.com\",\"password\":\"1233435\",\"blacklisted\":\"No\",\"language\":{\"id\": \"5\"}}";
		mockMvc.perform(post("/signup/add").content(USER_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError()).andExpect(
						jsonPath("$.errorMessage").value("Input validation failed: Name must be 4 to 30 characters"));
	}

	@Test
	public void testUserDetailsByEmail() throws Exception {

		mockMvc.perform(get("/signup/analystDetail/anki@gmail.com").contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.email").value("anki@gmail.com"));

	}

	@Test
	public void testForChangedUserStatus() throws Exception {

		mockMvc.perform(get("/signup/saveUserStatus/anki@gmail.com").contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.blacklisted").value("Yes"));

	}

	@Test
	public void addFavouriteArticleTest() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\" : \"anki@gmail.com\",\"favouriteArticle\" :[{\"title\":\"java\",\"description\":\"java java\",\"language\":{\"id\":2}}]}";

		mockMvc.perform(
				post("/signup/articleList").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	public void loginTest() throws Exception {

		String EMPLOYEE_REQUEST = "{\"email\":\"anki@gmail.com\",\"password\":\"1234\"}";

		mockMvc.perform(post("/authenticate").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());

	}
}
