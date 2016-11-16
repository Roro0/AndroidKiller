package parse_xml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import entity.Book;

/**
 * Created by Administrator on 2016/11/11.
 */

public class PullXmlParse {
    public List<Book> parsePull(InputStream inputStream) throws XmlPullParserException {
        List<Book> books = new ArrayList<>();
     //创建一个Xml实例
        XmlPullParser  xmlPullParser=Xml.newPullParser();

        xmlPullParser.setInput(inputStream,"utf-8");
        //我们需要自己获取产生的事件然后做出相应的操作
        int eventType = xmlPullParser.getEventType();
        if (eventType==XmlPullParser.END_DOCUMENT){
                switch (eventType){

                }
        }
        return books;
    }
}
