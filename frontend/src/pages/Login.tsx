import { useState } from 'react';
import { useNavigate,Link as RouterLink } from 'react-router-dom';
import {Box, Button, TextField, Typography, Paper, Alert, Link} from '@mui/material';
import { libraryService } from '../services/libraryService';

export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        setError(null);

        try {
            const response = await libraryService.login(username, password);
            localStorage.setItem('jwt_token', response.data.token);
            navigate('/books');
        } catch (err) {
            setError('Incorrect username or password.');
        }
    };

    return (
        <Box sx={{ display: 'flex', justifyContent: 'center', mt: 8 }}>
            <Paper sx={{ p: 4, width: '100%', maxWidth: 400 }}>
                <Typography variant="h5" gutterBottom align="center">
                    Login
                </Typography>

                {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}

                <form onSubmit={handleLogin}>
                    <TextField
                        fullWidth
                        label="Username"
                        margin="normal"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <TextField
                        fullWidth
                        label="Password"
                        type="password"
                        margin="normal"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <Button
                        fullWidth
                        type="submit"
                        variant="contained"
                        color="primary"
                        sx={{ mt: 3 }}
                    >
                        Login
                    </Button>
                </form>
                <Typography align={"center"} variant={"body2"} sx={{ mt :2}}>
                    Don't have an account? <Link component={RouterLink} to={'/register'}>Register here</Link>
                </Typography>
            </Paper>
        </Box>
    );
}