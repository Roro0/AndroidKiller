package parse_xml;

import android.app.Activity;
import android.app.Notification;
import android.content.res.AssetManager;
import android.os.Bundle;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import entity.Book;

/**
 * Created by Administrator on 2016/11/11.
 */

public class ParseXmlActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    private void initEvent() {
        AssetManager mgr = getAssets();
        try {
            InputStream inputStream =  mgr.open("Book.xml");
            DomXmlParse domXmlParse = new DomXmlParse();
            List<Book> books = domXmlParse.parse(inputStream);
            for (Book b:books) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
