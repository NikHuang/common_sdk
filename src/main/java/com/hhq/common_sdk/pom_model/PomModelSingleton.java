package com.hhq.common_sdk.pom_model;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class PomModelSingleton {

    private PomModelSingleton() {
    }

    private static Model model = null;

    static {
        String rootPath = System.getProperty("user.dir");
        MavenXpp3Reader reader = new MavenXpp3Reader();
        String myPom = rootPath + File.separator + "pom.xml";
        try {
            model = reader.read(new FileReader(myPom));
        } catch (Exception e) {
            log.error("加载pom信息失败");
        }
    }

    public static Model getInstance(){
        return model;
    }
}
