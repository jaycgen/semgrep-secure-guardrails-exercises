import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.CookieValue;

import java.io.InputStream;

import org.xml.sax.InputSource;

class BadDocumentBuilderFactoryStatic {
    @RequestMapping(path="/message", produces=MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public void doPost1(@RequestParam(defaultValue="test.txt") String file, @RequestParam String example) {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // ruleid: tainted-documentbuilderfactory-xxe-spring
        db.parse(new InputSource(file));
    }

    @RequestMapping("/hello")
    public void doPost2(@RequestParam(defaultValue="test.txt") String file, @RequestParam String example) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputStream is = new FileInputStream(file);
        //ruleid: tainted-documentbuilderfactory-xxe-spring
        db.parse(is);
    }

    @RequestMapping("/foo")
    public void doPost3(@RequestParam(defaultValue="test.txt") String file, @RequestParam String example) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder db = dbf.newDocumentBuilder();

        InputStream is = new FileInputStream(file);
        //ok: tainted-documentbuilderfactory-xxe-spring
        db.parse(is);
    }

    @RequestMapping("/hello2")
    public void doPost4(@RequestParam(defaultValue="test.txt") String file, @RequestParam String example) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        DocumentBuilder db = dbf.newDocumentBuilder();

        InputStream is = new FileInputStream(file);
        //ok: tainted-documentbuilderfactory-xxe-spring
        db.parse(is);
    }

    @RequestMapping("/hello3")
    public void doPost5(@RequestParam(defaultValue="test.txt") String file, @RequestParam String example) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();

        InputStream is = new FileInputStream(file);
        //ok: tainted-documentbuilderfactory-xxe-spring
        db.parse(is);
    }
    