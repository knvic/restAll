package restAll.metadata_1C;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page1C implements Serializable {

    @JsonProperty("Ref_Key")
    private String Ref_Key;
    @JsonProperty("DataVersion")
    private String DataVersion;
    @JsonProperty("DeletionMark")
    private String DeletionMark;
    @JsonProperty("Code")
    private String Code;
    @JsonProperty("Description")
    private String Description;


}

