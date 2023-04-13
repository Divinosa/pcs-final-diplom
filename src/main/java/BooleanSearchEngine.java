import Client.Client;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import java.io.*;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    private Map<String, Integer> freqs = new HashMap<>();
    private Map<String, List<PageEntry>> base = new HashMap<>();

    public BooleanSearchEngine(File[] dir) throws IOException {
        for (File file: dir) {
            var doc = new PdfDocument(new PdfReader(file));
            for (int i = 1; i < doc.getNumberOfPages(); i++) {
                var page = doc.getPage(i);
                var text = PdfTextExtractor.getTextFromPage(page);
                var words = text.split("\\P{IsAlphabetic}+");
                for (var word : words) { // перебираем слова
                    if (word.isEmpty()) {
                        continue;
                    }
                    word = word.toLowerCase();
                    freqs.put(word, freqs.getOrDefault(word, 0) + 1);
                }
                for (Map.Entry<String, Integer> entry : freqs.entrySet()) {
                    PageEntry pageEntry = new PageEntry(file, i, entry.getValue());
                    addToList(entry.getKey(), pageEntry);
                }
                freqs.clear();
            }
        }
        for (Map.Entry<String, List<PageEntry>> entry : base.entrySet()) {
            Collections.sort(entry.getValue());
        }
    }

    private void addToList(String mapKey, PageEntry pageEntry) throws IOException {
        List<PageEntry> pageEntryList = base.get(mapKey);
        if (pageEntryList == null) {
            pageEntryList = new ArrayList<>();
            pageEntryList.add(pageEntry);
            base.put(mapKey, pageEntryList);
        } else {
            if (!pageEntryList.contains(pageEntry)) pageEntryList.add(pageEntry);
        }
    }

    @Override
    public List<PageEntry> search(String word) throws IOException {
        List<PageEntry> pageEntryList;
        if (base.containsKey(word.toLowerCase())) {
            pageEntryList = base.get(word);
        } else {
            pageEntryList = new ArrayList<>();;
        }
        return pageEntryList;
    }
}
