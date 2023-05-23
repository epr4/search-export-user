package eco.searchexportgithubuser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class GithubUserTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void readFromJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

/*
        GithubApiResponse githubApiResponse = objectMapper.readValue(raw,GithubApiResponse.class);
        System.out.println("githubApiResponse = " + githubApiResponse);
*/

        String raw = " {\n" +
                "      \"login\": \"leaderliang\",\n" +
                "      \"id\": 3939753,\n" +
                "      \"node_id\": \"MDQ6VXNlcjM5Mzk3NTM=\",\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/3939753?v=4\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/leaderliang\",\n" +
                "      \"html_url\": \"https://github.com/leaderliang\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/leaderliang/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/leaderliang/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/leaderliang/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/leaderliang/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/leaderliang/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/leaderliang/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/leaderliang/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/leaderliang/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/leaderliang/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false,\n" +
                "      \"score\": 1.0\n" +
                "    }";
        GithubUser githubUser = objectMapper.readValue(raw,GithubUser.class);
        System.out.println("githubUser = " + githubUser);
        assertThat(githubUser.id, is(3939753));

    }

}