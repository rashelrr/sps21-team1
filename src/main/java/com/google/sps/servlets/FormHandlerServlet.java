/*package com.google.sps.servlets;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the value entered in the form.
    String name = Jsoup.clean(request.getParameter("name"), Whitelist.none());
    String description = Jsoup.clean(request.getParameter("description"), Whitelist.none());
    String hashTag = Jsoup.clean(request.getParameter("hashTag"), Whitelist.none());
    
    
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Task");
    
    FullEntity taskEntity =
    Entity.newBuilder(keyFactory.newKey())
        .set("name", name)
        .set("description", description)
        .set("hashTag", hashTag)
        .build();
    datastore.put(taskEntity); }
  } */