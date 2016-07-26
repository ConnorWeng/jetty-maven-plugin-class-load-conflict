package org.eclipse.jetty.servlet;

import org.eclipse.jetty.http.HttpContent;

/**
 * Created by ConnorWeng on 2016/7/26.
 */
public class DefaultServlet {
    public void printLoadedByWhom() {
        System.out.println("[DefaultServlet]: " + HttpContent.class.getClassLoader());
        System.out.println("[DefaultServlet]: " + this.getClass().getClassLoader());
    }

    public void overrideThisWillThrowError(HttpContent h) {

    }
}
