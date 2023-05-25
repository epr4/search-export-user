package eco.searchexportgithubuser;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SearchAndExportServiceTest {

    @Mock
    SearchService searchServiceMock;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void search() throws JsonProcessingException {
        String sample = "{\n" +
                "  \"total_count\": 12,\n" +
                "  \"incomplete_results\": false,\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"login\": \"mojombo\",\n" +
                "      \"id\": 1,\n" +
                "      \"node_id\": \"MDQ6VXNlcjE=\",\n" +
                "      \"avatar_url\": \"https://secure.gravatar.com/avatar/25c7c18223fb42a4c6ae1c8db6f50f9b?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/mojombo\",\n" +
                "      \"html_url\": \"https://github.com/mojombo\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/mojombo/followers\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/mojombo/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/mojombo/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/mojombo/repos\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/mojombo/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"score\": 1,\n" +
                "      \"following_url\": \"https://api.github.com/users/mojombo/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/mojombo/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/mojombo/starred{/owner}{/repo}\",\n" +
                "      \"events_url\": \"https://api.github.com/users/mojombo/events{/privacy}\",\n" +
                "      \"site_admin\": true\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        when(searchServiceMock.search(anyString())).thenReturn(sample);

        SearchAndExportService searchAndExportService = new SearchAndExportService(searchServiceMock);
        assertThat(searchAndExportService.search("Q").get(0).login, is("mojombo"));

    }

}