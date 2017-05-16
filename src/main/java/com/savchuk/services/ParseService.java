package com.savchuk.services;

import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by home on 08.05.17.
 */
@Service
public class ParseService{
    public static JSONObject getJsonFromRequest(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = request.getReader();
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            JSONObject jObj = new JSONObject(sb.toString());
            return  jObj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T parseRequest(Class<T> type, HttpServletRequest request){
        //ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();
        T temp = gson.fromJson(getJsonFromRequest(request).toString(), type);
        return temp;
    }
}
