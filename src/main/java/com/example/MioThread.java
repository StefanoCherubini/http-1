package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread  {
    Socket s;
    String ric = "";
    String ricSplit = "";

    public  MioThread(Socket s){
        this.s=s;
    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            ric = in.readLine();
            if(ric.split(" ")[0].equals("GET"))
            {
                while(true){
                    String line = in.readLine();
                    if(line.equals(""))
                    {
                        String resbody = "<html><body> <h1> pagina non trovata </h1> </body> </html>";
                        out.writeBytes("HTTP/1.1 404 not found \r\n");
                        out.writeBytes("Content-length:" + resbody.length()+ "\r\n");
                        out.writeBytes("Content-type: text/html \r\n");
                        out.writeBytes("\r\n");
                        out.writeBytes(resbody);
                        break;
                    }
                    else 
                        System.out.println(line);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
