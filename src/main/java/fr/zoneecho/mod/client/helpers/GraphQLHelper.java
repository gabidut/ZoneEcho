package fr.zoneecho.mod.client.helpers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class GraphQLHelper {
    public static void main(String[] args) throws IOException {
        String query = "{ pages { list (orderBy: TITLE) { id path title } } }";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://193.38.250.14:3000/graphql")
                .addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcGkiOjIsImdycCI6MSwiaWF0IjoxNjcyNTIxMzMxLCJleHAiOjE3NjcxOTQxMzEsImF1ZCI6InVybjp3aWtpLmpzIiwiaXNzIjoidXJuOndpa2kuanMifQ.u8sbBPCXuYIs6uCd8eNQD2EEKhrQAGFWtSDHSAz3kNGew-RULLy3MngXQh84lj3LVNgfAjlfJYMn5G0efdikyziHlWHaAf5m7UdlXM1j1-an7VDnetN79cOIWzzi6tbPE7-I54J6DupamkATFoelr3EX7TdX115SGn17yq2Z1282CbebEUSoqYsEbo1bWTyQJbfW0BFmFpyIJQ3CbXR6ZGvmUcyCIviVw63uiw7C7zpwWAJje7EdXUm6uEepAC1eduFGcADRwf3Kp-z5i9nEBVobQ3iL3RdQbCcZOcqirU2h7TSDVHX0Q7MfooKUd79mCtyAgjz4Bcjea-JJCEbPjg")
                .post(okhttp3.RequestBody.create(okhttp3.MediaType.get("application/json"), "{\"query\":\"" + query + "\"}"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseString = response.body().string();
            JSONObject jsonObject = new JSONObject(responseString);
            System.out.println(jsonObject.toString(2));
        }
    }
}