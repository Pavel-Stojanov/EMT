package mk.ukim.finki.library_api.service.domain;

import mk.ukim.finki.library_api.model.domain.BookHistory;

public interface BookHistoryService{
    BookHistory getHistory(Long id);
    void addHistoryEntry(Long id,String description);
}
