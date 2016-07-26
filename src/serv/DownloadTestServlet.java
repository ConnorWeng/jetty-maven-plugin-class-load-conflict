package serv;

import org.eclipse.jetty.http.HttpContent;
import org.eclipse.jetty.servlet.DefaultServlet;

/**
 * Created by ConnorWeng on 2016/7/26.
 */
public class DownloadTestServlet extends DefaultServlet {
    @Override
    public void printLoadedByWhom() {
        System.out.println("[DownloadTestServlet]: " + HttpContent.class.getClassLoader());
        System.out.println("[DownloadTestServlet]: " + this.getClass().getClassLoader());
        /*
         * below is also not allowed
         * will throw loader constraint violation: when resolving method...
         */
        // super.overrideThisWillThrowError(new HttpContent());
    }

    /*
    @Override
    public void overrideThisWillThrowError(HttpContent h) {
    }
    */
}
