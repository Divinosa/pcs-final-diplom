package service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    Map<String, Integer> freqs = new HashMap<>();
    public BooleanSearchEngine(File pdfsDir) throws IOException {
        var doc = new PdfDocument(new PdfReader(pdfsDir));
        for (int i = 1; i < doc.getNumberOfPages(); i++){
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
            for (Map.Entry<String, Integer> entry : freqs.entrySet()){
                PageEntry pageEntry = new PageEntry(pdfsDir,i,entry.getValue());
                addToList(entry.getKey(), pageEntry);
            }
            freqs.clear();
        }
    }

    public synchronized void addToList(String mapKey, PageEntry pageEntry){
        List<PageEntry> pageEntryList = PageEntry.base.get(mapKey);
        if (pageEntryList == null){
            pageEntryList = new ArrayList<>();
            pageEntryList.add(pageEntry);
            PageEntry.base.put(mapKey, pageEntryList);
        } else {
            if (!pageEntryList.contains(pageEntry)) pageEntryList.add(pageEntry);
        }

    }

    @Override
    public List<PageEntry> search(String word) {
        List<PageEntry> pageEntryList = new ArrayList<>();
        if (PageEntry.base.containsKey(word)){
            pageEntryList = PageEntry.base.get(word);
        } else pageEntryList = null;
        Collections.sort(pageEntryList);
        return pageEntryList;
    }
}
