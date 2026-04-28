import {Typography,Table,TableCell,TableContainer,TableHead,TableRow,TableBody,CircularProgress,Paper} from "@mui/material";
import {useCountries} from "../hooks/useCountries.ts";

export default function Countries(){
    const {countries,loading,error} = useCountries();

    if (loading) return <CircularProgress/>
    if (error) return <Typography color={"error"}>{error}</Typography>

    return (
        <>
            <Typography variant={'h4'} gutterBottom sx={{textAlign:"center"}}>Countries</Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Continent</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {countries.map(c=>(
                            <TableRow key={c.id}>
                                <TableCell>{c.id}</TableCell>
                                <TableCell>{c.name}</TableCell>
                                <TableCell>{c.continent}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    )
}