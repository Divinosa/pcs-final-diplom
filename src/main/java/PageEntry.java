import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageEntry implements Comparable<PageEntry> {

    public static Map<String, List<PageEntry>> base = new HashMap<>();

    private final String pdfName;
    private final Integer page;
    private final Integer count;

    public PageEntry(File pdfName, int page, int count) {
        this.pdfName = String.valueOf(pdfName);
        this.page = page;
        this.count = count;
    }

    public String toString(){
       return  "{" +
                "pdfName='" + pdfName + '\'' +
                ", page=" + page +
                ", count='" + count + '\'' +
                '}';
    }

    @Override
    public int compareTo(PageEntry o) {
        int result = this.count.compareTo(o.count);
        if (result == 0){
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
