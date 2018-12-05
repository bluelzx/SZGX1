package com.lanzx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.selenium.DefaultSelenium;


public class Main {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
//		String host = "localhost";
//		int port = 4444;
//		String url = "http://www.szsti.gov.cn/services/hightech/default.aspx";
//		String browser = "*iexplore";
//		String pageNum = "document.getElementById('PagerControl_input')";//PagerControl_input
//		String goBtn = "document.getElementById('PagerControl_btn')";//PagerControl_btn
//
//		DefaultSelenium selenium = new DefaultSelenium(host,port,url,browser);
//		selenium.start();
//		selenium.open("http://www.szsti.gov.cn/services/hightech/default.aspx");
//		selenium.type(pageNum,"2");
//		selenium.click(goBtn);
//
//		selenium.waitForPageToLoad("5000000");

        FileWriter fw = new FileWriter("data.txt");
        BufferedWriter bw = new BufferedWriter(fw);


        String host = "localhost";
        int port = 4444;
        String url = "http://www.szsti.gov.cn/";
        String browserType = "*firefox";

        String pageNum = "document.getElementById('PagerControl_input')";
        String goBtn = "document.getElementById('PagerControl_btn')";
        DefaultSelenium selenium = new DefaultSelenium(host,port,browserType,url);
        selenium.start();
        selenium.open("http://www.szsti.gov.cn/services/hightech/default.aspx");
        for(int ss=315;ss<408;ss++){
            selenium.type(pageNum,""+ss);
            selenium.click(goBtn);
            selenium.waitForPageToLoad("50000000");

            String table = "//table[@id='data_list_container']/tbody/tr";
            int rowNumber = selenium.getXpathCount(table).intValue();
//		  String tableId = "document.getElementById('data_list_container')";
            for(int i=1;i<rowNumber;i++){
                for(int j=0;j<=5;j++){
                    if(j!=5) {
                        System.out.print(selenium.getTable("data_list_container." + i + "." + j) + ",");
                        bw.write(selenium.getTable("data_list_container." + i + "." + j) + ",");
                    }else{
                        System.out.println(selenium.getTable("data_list_container." + i + "." + j) );
                        bw.write(selenium.getTable("data_list_container." + i + "." + j) + "\r\n");

                    }
                }

            }
            System.out.println(ss);
        }

        bw.close();
        selenium.stop();
    }

}

