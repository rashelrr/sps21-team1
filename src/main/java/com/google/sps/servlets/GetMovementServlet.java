package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /get-movement URL **/
@WebServlet("/get-movement")
public class GetMovementServlet extends HttpServlet {
  
  static final long serialVersionUID = 0;
  static Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    //** not sure if this is right
    String hashtag = request.getParameter("hashtag");

    // Query finds entity (movement) that contains the specified hashtag
    Query<Entity> query =
        Query.newEntityQueryBuilder()
        .setKind("Movement")
        .setFilter(PropertyFilter.eq("hashtag", hashtag))
        .build();
    QueryResults<Entity> results = datastore.run(query);

    // Adds property values of entity to a hashmap
    Map<String, String> map = new HashMap<String, String>();
    while (results.hasNext()) {
        Entity currentEntity = results.next();
        map.put("name", currentEntity.getString("name"));
        map.put("description", currentEntity.getString("description"));
        map.put("hashtag", currentEntity.getString("hashtag"));
        map.put("video", currentEntity.getString("video"));
        map.put("image", currentEntity.getString("image"));
        map.put("donation", currentEntity.getString("donation"));
        map.put("resources", currentEntity.getString("resources"));
    }

    // Returns movement data as JSON
    Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().write(gson.toJson(map));
  }

}
