package com.browseaware;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import com.datumbox.framework.applications.nlp.TextClassifier;
import com.datumbox.framework.common.Configuration;
import com.datumbox.framework.core.machinelearning.MLBuilder;

@Path("classify")
public class Classify {

    @POST
    @Produces (MediaType.APPLICATION_JSON)
    @Consumes("text/plain")
    public Response getCategory(String inputText){
        JSONObject result = new JSONObject();
        result.put("status","1");
        Configuration configuration = Configuration.getConfiguration();
        TextClassifier textClassifier = MLBuilder.load(TextClassifier.class, "TopicClassification", configuration);
        result.put("category", textClassifier.predict(inputText).getYPredicted().toString());
        return Response.status(Response.Status.OK).entity(result.toString()).build();
    }

}
