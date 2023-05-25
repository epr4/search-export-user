package eco.searchexportgithubuser.service;

import com.itextpdf.text.DocumentException;
import eco.searchexportgithubuser.GithubUser;
import eco.searchexportgithubuser.db.History;
import eco.searchexportgithubuser.db.HistoryDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;


@RunWith(MockitoJUnitRunner.class)
public class SearchAndExportServiceTest {

    @Mock
    SearchService searchServiceMock;

    @Mock
    ExportService exportServiceMock;

    @Mock
    HistoryDao historyDaoMock;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void searchAndExport() throws IOException, DocumentException, ExecutionException, InterruptedException {
        String sample = """
                {
                  "total_count": 12,
                  "incomplete_results": false,
                  "items": [
                    {
                      "login": "mojombo",
                      "id": 1,
                      "node_id": "MDQ6VXNlcjE=",
                      "avatar_url": "https://secure.gravatar.com/avatar/25c7c18223fb42a4c6ae1c8db6f50f9b?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png",
                      "gravatar_id": "",
                      "url": "https://api.github.com/users/mojombo",
                      "html_url": "https://github.com/mojombo",
                      "followers_url": "https://api.github.com/users/mojombo/followers",
                      "subscriptions_url": "https://api.github.com/users/mojombo/subscriptions",
                      "organizations_url": "https://api.github.com/users/mojombo/orgs",
                      "repos_url": "https://api.github.com/users/mojombo/repos",
                      "received_events_url": "https://api.github.com/users/mojombo/received_events",
                      "type": "User",
                      "score": 1,
                      "following_url": "https://api.github.com/users/mojombo/following{/other_user}",
                      "gists_url": "https://api.github.com/users/mojombo/gists{/gist_id}",
                      "starred_url": "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                      "events_url": "https://api.github.com/users/mojombo/events{/privacy}",
                      "site_admin": true
                    }
                  ]
                }""";
        String login = "mojombo";
        String query = "Q";

        History history = new History();
        Mockito.when(historyDaoMock.save(history)).thenReturn(history);
        Mockito.when(exportServiceMock.export(anyString(),anyList())).thenReturn(true);
        Mockito.when(searchServiceMock.search(anyString())).thenReturn(sample);
        SearchAndExportService searchAndExportService =
                new SearchAndExportService(searchServiceMock,exportServiceMock,historyDaoMock);

        List<GithubUser> githubUsers = searchAndExportService.search(query);
        assertThat(githubUsers.get(0).getLogin(), is(login));

        assertThat(searchAndExportService.searchAndExport(query).get().get(0).getLogin(), is(login));

    }

}