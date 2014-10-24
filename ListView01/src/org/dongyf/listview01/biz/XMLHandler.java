package org.dongyf.listview01.biz;

import android.net.http.AndroidHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.dongyf.listview01.entity.BusStatus;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongyf on 2014/10/24.
 */
public class XMLHandler
{

    public static InputStream downloadXml()
    {
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0; QQBrowser/7.7.28658.400) like Gecko";
        String url = "http://apis.juhe.cn/szbusline/bus?key=c8baa60764b604d148d89c400696345b&dtype=xml&busline=9acf55b9-8406-40ef-8056-6de249174ee0";
        AndroidHttpClient httpClient = AndroidHttpClient.newInstance(userAgent);
        InputStream inputStream = null;
        try
        {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            httpClient.close();
        }
        return inputStream;
    }

    public static List<BusStatus> parserXml()
    {
        List<BusStatus> busStatusList = null;
        InputStream inputStream = downloadXml();
        System.out.println(inputStream.toString());
        try
        {
            BusStatus busStatus = null;
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser pullParser = factory.newPullParser();
            pullParser.setInput(inputStream, "UTF-8");
            int type = pullParser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT)
            {

                switch (type)
                {
                    case XmlPullParser.START_TAG:
                        String nodeName = pullParser.getName();
                        if (nodeName.equalsIgnoreCase("stationName"))
                        {
                            busStatus = new BusStatus();
                            busStatus.setStationName(pullParser.getText());
                        } else if (nodeName.equalsIgnoreCase("stationCode"))
                        {
                            busStatus.setStationCode(pullParser.getText());
                        } else if (nodeName.equalsIgnoreCase("carCode"))
                        {
                            busStatus.setCarCode(pullParser.getText());
                        } else if (nodeName.equalsIgnoreCase("ArrivalTime"))
                        {
                            busStatus.setArrivalTime(pullParser.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (pullParser.getName().equalsIgnoreCase("ArrivalTime"))
                        {
                            busStatusList = new ArrayList<BusStatus>();
                            busStatusList.add(busStatus);
                            busStatus = null;
                        }
                        break;
                }
                type = pullParser.next();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }


        return busStatusList;
    }


}
