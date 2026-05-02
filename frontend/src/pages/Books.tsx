import {
    Button,
    Paper,
    Table, TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";
import {useBooks} from "../hooks/useBooks.ts";
import {EmptyState, ErrorState, LoadingState} from "../components/PageState";

export default function Books() {
    const {books, loading, error} = useBooks();

    if (loading) return <LoadingState/>
    if (error) return <ErrorState message={error}/>

    return (
        <>
            <Typography variant={'h4'} gutterBottom sx={{textAlign:"center"}}>
                Books
            </Typography>
            {books.length === 0 ? (
                <EmptyState message="There are no books available."/>
            ) : (
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Category</TableCell>
                            <TableCell>State</TableCell>
                            <TableCell>Available Copies</TableCell>
                            <TableCell align="right">Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {books.map(b=> (
                            <TableRow key={b.id}>
                                <TableCell>{b.id}</TableCell>
                                <TableCell>{b.name}</TableCell>
                                <TableCell>{b.category}</TableCell>
                                <TableCell>{b.state}</TableCell>
                                <TableCell>{b.availableCopies}</TableCell>
                                <TableCell align="right">
                                    <Button component={RouterLink} to={`/books/${b.id}`} size="small" variant="outlined">
                                        View
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            )}
        </>
    )
}
