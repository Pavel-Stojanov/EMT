import {Button, Typography,Table,TableCell,TableContainer,TableHead,TableRow,TableBody,Paper} from "@mui/material";
import {Link as RouterLink} from "react-router-dom";
import {useCountries} from "../hooks/useCountries.ts";
import {EmptyState, ErrorState, LoadingState} from "../components/PageState";

export default function Countries(){
    const {countries,loading,error} = useCountries();

    if (loading) return <LoadingState/>
    if (error) return <ErrorState message={error}/>

    return (
        <>
            <Typography variant={'h4'} gutterBottom sx={{textAlign:"center"}}>Countries</Typography>
            {countries.length === 0 ? (
                <EmptyState message="There are no countries available."/>
            ) : (
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Continent</TableCell>
                            <TableCell align="right">Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {countries.map(c=>(
                            <TableRow key={c.id}>
                                <TableCell>{c.id}</TableCell>
                                <TableCell>{c.name}</TableCell>
                                <TableCell>{c.continent}</TableCell>
                                <TableCell align="right">
                                    <Button component={RouterLink} to={`/countries/${c.id}`} size="small" variant="outlined">
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
