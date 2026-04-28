export interface Country {
    id: number;
    name: string;
    continent: string;
}

export interface Author {
    id: number;
    name: string;
    surname: string;
    countryId: number;
}

export interface Book {
    id: number;
    name: string;
    category: string;
    authorId: number;
    state: string;
    availableCopies: number;
}

export interface PageResponse<T> {
    content: T[];
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
}