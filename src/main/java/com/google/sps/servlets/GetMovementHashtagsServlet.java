package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /get-hashtags URL **/
@WebServlet("/get-hashtags")
public class GetMovementHashtagsServlet extends HttpServlet {
  
  static final long serialVersionUID = 0;
  static Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Query finds all entities (movements)
    Query<Entity> query =
        Query.newEntityQueryBuilder()
        .setKind("Movement")
        .build();
    QueryResults<Entity> results = datastore.run(query);

    // Adds hashtags to list 
    ArrayList<String> hashtags = new ArrayList<String>();
    while (results.hasNext()) {
        Entity currentEntity = results.next();
        hashtags.add(currentEntity.getString("hashtag"));
    }

    // Returns hastags as JSON
    Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().write(gson.toJson(hashtags));
  }

}
