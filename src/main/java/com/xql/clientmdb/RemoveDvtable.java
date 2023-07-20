package com.xql.clientmdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

public class RemoveDvtable {
    private String baseUrl;
    private String url;

    private String dvsname;
    private String dvt_name;

    public RemoveDvtable() {

    }

    /**
     * @return String return the baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return String return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return String return the dvsname
     */
    public String getDvsname() {
        return dvsname;
    }

    /**
     * @param dvsname the dvsname to set
     */
    public void setDvsname(String dvsname) {
        this.dvsname = dvsname;
    }

    /**
     * @return String return the dvt_name
     */
    public String getDvt_name() {
        return dvt_name;
    }

    /**
     * @param dvt_name the dvt_name to set
     */
    public void setDvt_name(String dvt_name) {
        this.dvt_name = dvt_name;
    }

    public String toJsonString() {
        HashMap<String, String> queryMap = new HashMap<String, String>();

        queryMap.put("dvsname", this.dvsname);
        queryMap.put("dvt_name", this.dvt_name);

        String jsonStr = "";
        JSONObject queryObj = new JSONObject(queryMap);
        jsonStr = queryObj.toString();

        return jsonStr;
    }

    public String encodeURL(String path) {
        String encPath = "";
        if (path.length() == 0) {
            // nothing to be done
        } else if (path.equals("/")) {
            // only one character '/'; done; length 1
            encPath = "/";
        } else if (path.substring(0, 1).equals("/")) {
            // begins with '/'; length more than 1
            encPath = "/";
            String nextpath = path.substring(1);
            while (true) {
                String[] paths = nextpath.split("/");
                // System.out.println("paths[0]: " + paths[0]);
                try {
                    encPath = encPath + URLEncoder.encode(paths[0], "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("UnsupportedEncodingException: " + ex.getMessage());
                    System.exit(0);
                }
                int slashIndex = nextpath.indexOf("/");
                // System.out.println("slashIndex: " + slashIndex);
                if (slashIndex == -1) {
                    break;
                } else {
                    encPath = encPath + "/";
                    nextpath = nextpath.substring(slashIndex + 1);
                }
            }
        } else {
            String nextpath = path.substring(0);
            while (true) {
                String[] paths = nextpath.split("/");
                // System.out.println("paths[0]: " + paths[0]);
                try {
                    encPath = encPath + URLEncoder.encode(paths[0], "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("UnsupportedEncodingException: " + ex.getMessage());
                    System.exit(0);
                }
                int slashIndex = nextpath.indexOf("/");
                if (slashIndex == -1) {
                    break;
                } else {
                    encPath = encPath + "/";
                    nextpath = nextpath.substring(slashIndex + 1);
                }
            }
        }
        String spacePath = encPath.replaceAll("[+]", "%20");

        return spacePath;
    }

    public String removeDvtable() {
        String responseBody = "";
        String uri = "";
        if (this.dvsname != null) {
            uri = this.url + this.dvsname;
        } else {
            uri = this.url;
        }
        if (this.dvt_name != null) {
            uri = uri + "/" + this.dvt_name;
        } else {
            // uri = this.url;
        }

        int len = baseUrl.length();
        String path = uri.substring(len);
        System.out.println("path: " + path);

        String encPath = encodeURL(path);

        uri = baseUrl + encPath;

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            System.out.println("DELETE " + uri);
            System.out.println();
            HttpDelete httpDelete = new HttpDelete(uri);
            System.out.println("Executing request " + httpDelete.getRequestLine());
            // Create a custom response handler
            HttpResponse response = httpclient.execute(httpDelete);
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            System.out.println("status code: " + response.getStatusLine().getStatusCode());

            StringBuffer result = new StringBuffer();
            String line = "";
            System.out.println("Response: ");
            while ((line = br.readLine()) != null) {
                System.out.println(result.append(line));
            }
            responseBody = result.toString();
        } catch (ClientProtocolException ex) {
            System.out.println("ClientProtocolException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
            ex.printStackTrace();
        }

        return responseBody;
    }

}
