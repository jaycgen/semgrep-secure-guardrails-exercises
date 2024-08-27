import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

public class CookieHelper {

    public static void addSecureCookie(HttpServletRequest r, Cookie c){
        c.setSecure(true);
        c.setHttpOnly(true);
        c.setDomain(".semgrep.dev");
        c.setMaxAge(60*60*24); // 1 day
        c.setPath("/user");

        r.addCookie(c);
    }
}

public class Servlet extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

        Cookie cookie = new Cookie("name", "value");
      
        //ruleid: java-cookie-addsecurecookie
        request.addCookie(cookie);

        //ok: java-cookie-addsecurecookie
        CookieHelper.addSecureCookie(request, cookie);
   }

}