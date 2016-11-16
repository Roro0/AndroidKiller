package parse_xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import entity.Book;

/**
 * Dom解析
 * Created by Administrator on 2016/11/11.
 */

public class DomXmlParse {
    public  List<Book> parse(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        List<Book> books = new ArrayList<>();
        //获取DocumentBuilderFactory实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //获取DocumentBuilder对象
        DocumentBuilder builder=factory.newDocumentBuilder();
        //将输入流转换为文档
        Document doc = builder.parse(inputStream);
        //读取文档的元素
        Element element = doc.getDocumentElement();
        //获取元素中的节点
       NodeList item =  element.getElementsByTagName("book");
        for (int i=0;i<item.getLength();i++) {
            Book book = new Book();
            //拿到单个的节点
            Node node = item.item(i);
            //获取此节点的子节点列表
            NodeList chileList = node.getChildNodes();
            for (int f=0;f<chileList.getLength();f++){
                //拿到一个子节点
                Node childNode = chileList.item(f);
                //获得该子节点的名称
                String chileName = childNode.getNodeName();
                if (chileName=="book"){
                    book.setName(childNode.getNodeValue());
                }else if(chileName=="type"){
                    book.setType(childNode.getNodeValue());
                }else if(chileName=="id") {
                    book.setId(Integer.parseInt(childNode.getNodeValue()));
                }
            }
                   books.add(book);
        }
        return books;
    }
}
