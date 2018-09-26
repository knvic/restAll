package restAll.jasonplaceholder_post;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;


}
