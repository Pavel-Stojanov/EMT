import {
    CircularProgress,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import {useAuthors} from "../hooks/useAuthors.ts";

export default function Authors() {
    const {authors, loading, error} = useAuthors();

    if (loading) return <CircularProgress/>
    if (error) return <Typography color={'error'}>{error}</Typography>

    return (
        <>
            <Typography variant={'h4'} sx={{textAlign:"center"}} gutterBottom>Authors</Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Surname</TableCell>
                            <TableCell>Country ID</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {authors.map(a => (
                            <TableRow key={a.id}>
                                <TableCell>{a.id}</TableCell>
                                <TableCell>{a.name}</TableCell>
                                <TableCell>{a.surname}</TableCell>
                                <TableCell>{a.countryId}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>

                </Table>
            </TableContainer>
        </>
    )
}