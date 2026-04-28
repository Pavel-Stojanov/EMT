import {Box, Typography} from "@mui/material";

export default function Home() {
    return (
        <Box sx={{textAlign: "center", mt: 8}}>
            <Typography variant={"h3"} gutterBottom>
                Welcome to the Library System
            </Typography>
            <Typography variant={'h6'} color={"textSecondary"}>
                Use the navigation menu to look at books, authors and countries.
            </Typography>
        </Box>

    )
}