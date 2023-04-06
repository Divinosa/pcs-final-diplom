import java.io.File;
import java.util.List;
import java.util.Map;

public class PageEntry implements Comparable<PageEntry> {



    private final String pdfName;
    private final int page;
    private final int count;

    public PageEntry(File pdfName, int page, int count) {
        this.pdfName = String.valueOf(pdfName);
        this.page = page;
        this.count = count;
    }

    public String toString(){
        return this.pdfName + " page=" + getPage() + " count=" + getCount();
    }

    @Override
    public int compareTo(PageEntry o) {
        return 0;
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
