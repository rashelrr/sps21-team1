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
public class CreateMovementServlet extends HttpServlet {

  static final long serialVersionUID = 0;
  static Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
  static KeyFactory keyFactory = datastore.newKeyFactory().setKind("Movement");

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String name = Jsoup.clean(request.getParameter("name"), Whitelist.none());
    String description = Jsoup.clean(request.getParameter("description"), Whitelist.none());
    String hashtag = Jsoup.clean(request.getParameter("hashtag"), Whitelist.none());
    String video = Jsoup.clean(request.getParameter("video"), Whitelist.none());
    String image = Jsoup.clean(request.getParameter("image"), Whitelist.none());
    String donation = Jsoup.clean(request.getParameter("donation"), Whitelist.none());
    String resources = Jsoup.clean(request.getParameter("resources"), Whitelist.none());
    
    // removes '#'
    if (!hashtag.isEmpty())
      hashtag = hashtag.substring(1);

    FullEntity movementEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("description", description)
            .set("hashtag", hashtag)
            .set("video", video)
            .set("image", image)
            .set("donation", donation) 
            .set("resources", resources) 
            .build();
    datastore.put(movementEntity);

    // TODO : redirect to movement.html # URL: appengine.com/movement?=<HASHTAG> ex:  appengine.com/movement?=BLM (im not sure if pound sign is allowed)
    // "https://spring21-sps-1.uc.r.appspot.com/movement?=" + hashtag
    response.sendRedirect("/index.html");
  } 

}