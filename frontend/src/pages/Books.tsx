import {
    CircularProgress,
    Paper,
    Table, TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import {useBooks} from "../hooks/useBooks.ts";

export default function Books() {
    const {books, loading, error} = useBooks();

    if (loading) return <CircularProgress/>
    if (error) return <Typography color={"error"}>{error}</Typography>

    return (
        <>
            <Typography variant={'h4'} gutterBottom sx={{textAlign:"center"}}>
                Books
            </Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Category</TableCell>
                            <TableCell>State</TableCell>
                            <TableCell>Available Copies</TableCell>
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
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    )
}