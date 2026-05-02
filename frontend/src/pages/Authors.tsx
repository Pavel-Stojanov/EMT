import {
    Button,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";
import {useAuthors} from "../hooks/useAuthors.ts";
import {EmptyState, ErrorState, LoadingState} from "../components/PageState";

export default function Authors() {
    const {authors, loading, error} = useAuthors();

    if (loading) return <LoadingState/>
    if (error) return <ErrorState message={error}/>

    return (
        <>
            <Typography variant={'h4'} sx={{textAlign:"center"}} gutterBottom>Authors</Typography>
            {authors.length === 0 ? (
                <EmptyState message="There are no authors available."/>
            ) : (
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Surname</TableCell>
                            <TableCell>Country ID</TableCell>
                            <TableCell align="right">Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {authors.map(a => (
                            <TableRow key={a.id}>
                                <TableCell>{a.id}</TableCell>
                                <TableCell>{a.name}</TableCell>
                                <TableCell>{a.surname}</TableCell>
                                <TableCell>{a.countryId}</TableCell>
                                <TableCell align="right">
                                    <Button component={RouterLink} to={`/authors/${a.id}`} size="small" variant="outlined">
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
