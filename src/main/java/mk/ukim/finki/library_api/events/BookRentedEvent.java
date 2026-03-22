package mk.ukim.finki.library_api.events;

public record BookRentedEvent(
        Long bookId,
        String bookName,
        Integer remainingCopies
) {
}
