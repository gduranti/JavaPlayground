package com.gduranti.processengine;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AbstractIntegratedTest {

    private static final String WEBINF = "src/main/webapp/WEB-INF";

    @Deployment
    public static Archive createDeployment() {

        WebArchive war = ShrinkWrap.create(WebArchive.class);
        war.addPackages(true, "com.gduranti.processengine");//
        war.addAsWebInfResource(new File(WEBINF, "beans.xml"), "beans.xml");

        System.out.println(war.toString(true));

        return war;
    }

}