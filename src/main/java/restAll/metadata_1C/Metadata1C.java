package restAll.metadata_1C;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata1C  {
    //@SerializedName("odata.metadata")
    @JsonProperty("odata.metadata")
    private String odata;
    @JsonProperty("value")
    //private ArrayList<Page1C> value = new ArrayList<>();
    private List<Page1C> value;


}
