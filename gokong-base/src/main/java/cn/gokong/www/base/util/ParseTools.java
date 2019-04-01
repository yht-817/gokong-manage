package cn.gokong.www.base.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 解析回调的（微信、支付宝）传参的解析公用方法
 */

public class ParseTools {

    // 支付宝的公用解析方法
    public static Map<String, String> analyticAlipay(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        System.out.println("params转换字符串进行数据查看：" + params.toString());
        return params;
    }

    // 微信回调公用解析参数
    public static Map<String, String> wxParsing(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuffer tStringBuffer = new StringBuffer();
            String sTempOneLine = new String("");
            while ((sTempOneLine = tBufferedReader.readLine()) != null) {
                tStringBuffer.append(sTempOneLine);
            }
            String result = tStringBuffer.toString();
            tBufferedReader.close();
            System.out.println("微信支付通知结果：" + result);
            // 解析微信通知返回的信息
            Map<String, String> datamsgmap = xmlToMap(result); // 把xml信息转换成map对象
            if (datamsgmap != null) {
                return datamsgmap;
            }
            System.out.println("微信调用异步返回当前充值的结果" + datamsgmap.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换成Map格式
     *
     * @param strXML
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == 1) {
                    Element element = (Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            stream.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
