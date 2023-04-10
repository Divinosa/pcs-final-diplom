package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageEntry implements Comparable<PageEntry>, Serializable {

    private static final long serialVersionUID = 1L;

    public static Map<String, List<PageEntry>> base = new HashMap<>();

    private final String pdfName;
    private final Integer page;
    private final Integer count;

    public PageEntry(File pdfName, int page, int count) {
        this.pdfName = String.valueOf(pdfName);
        this.page = page;
        this.count = count;
    }

    public static void toString(List<PageEntry> pageEntryList) {
        OutputStreamWriter stream = new OutputStreamWriter(System.out);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try {
            for (int i = 0; i < pageEntryList.size(); i++) {
                stream.write(gson.toJson(pageEntryList.get(i)));
            }
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(PageEntry o) {
        int result = this.count.compareTo(o.count);
        if (result == 0) {
            result = this.page.compareTo(o.page);
        }
        return result;
    }

    public String getPdfName() {
        return pdfName;
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }
}
