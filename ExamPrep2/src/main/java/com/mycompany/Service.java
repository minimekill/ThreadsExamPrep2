package com.mycompany;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Service {

    Future<Group> f = null;
    List<Group> groupList = new ArrayList();
    List<Future> futureList = new ArrayList();
    String[] webArr = {"http://165.227.151.92:8080/CA1/"};
    ExecutorService ex = Executors.newFixedThreadPool(4);
    ArrayBlockingQueue<String> websites = new ArrayBlockingQueue(webArr.length);

    public static void main(String[] args) {
    }

    public void callBack() {

        for (int i = 0; i < webArr.length; i++) {
            String url = webArr[i];
            Callable<Group> test = () -> {
                Document doc = Jsoup.connect(url).get();
                Elements eAuthors = doc.select("#authors");
                Elements eKlasse = doc.select("#class");
                Elements eGroup = doc.select("#group");
                String sauthor = eAuthors.text();
                String sklasse = eKlasse.text();
                String sgroup = eGroup.text();
            
                Group group = new Group(sauthor, sklasse, sgroup);
                String info = sauthor + " " + sklasse + " " + sgroup;
                System.out.println(info);
                return group;
            };
            f = ex.submit(test);
            futureList.add(f);
        }

        try {
            //System.out.println(futureList.get(0).get());
            for (int i = 0; i < futureList.size(); i++) {
            System.out.println("??????????????????");
                Group group =  (Group) futureList.get(i).get();
                groupList.add(group);
            }

        } catch (InterruptedException ex ) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        ex.shutdown();
    }

    public List getList() {
        return groupList;
    }

}
