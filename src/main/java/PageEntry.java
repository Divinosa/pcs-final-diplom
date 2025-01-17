import java.io.File;

public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;
    private final Integer page;
    private final Integer count;

    public PageEntry(File pdfName, int page, int count) {
        this.pdfName = String.valueOf(pdfName);
        this.page = page;
        this.count = count;
    }

    @Override
    public int compareTo(PageEntry o) {
        int result = o.count.compareTo(this.count);
        if (result == 0) {
            result = o.page.compareTo(this.page);
        }
        return result;
    }
}
