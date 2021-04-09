package com.google.sps.servlets;
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


/** Saves input by user into datastore */
@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  static final long serialVersionUID = 0;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String name = Jsoup.clean(request.getParameter("name"), Whitelist.none());
    String description = Jsoup.clean(request.getParameter("description"), Whitelist.none());
    String hashtag = Jsoup.clean(request.getParameter("hashtag"), Whitelist.none());
    String video = Jsoup.clean(request.getParameter("video"), Whitelist.none());
    String image = Jsoup.clean(request.getParameter("image"), Whitelist.none());
    String resources = Jsoup.clean(request.getParameter("resources"), Whitelist.none());
    
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("MovementByUser");
    
    FullEntity movementEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("description", description)
            .set("hashtag", hashtag)
            .set("video", video)
            .set("image", image)
            .set("resources", resources)
            .build();
    datastore.put(movementEntity);

    response.sendRedirect("/index.html");
  } 

}