import {BrowserRouter, Route, Routes} from 'react-router-dom';
import {createTheme, CssBaseline, ThemeProvider} from '@mui/material';
import Layout from './components/Layout';
import Home from './pages/Home';
import Books from './pages/Books';
import Authors from './pages/Authors';
import Countries from './pages/Countries';
import Login from "./pages/Login.tsx";
import Register from "./pages/Register.tsx";
import CategoryStatistics from "./pages/CategoryStatistics.tsx";

const theme = createTheme({
    palette: {
        mode: 'light',
        primary: {
            main: '#1976d2',
        },
    },
});

function App() {
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/> {/* Ова ги ресетира основните стилови на прелистувачот */}
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Layout/>}>
                        <Route index element={<Home/>}/>
                        <Route path={"login"} element={<Login/>}/>
                        <Route path={"register"} element={<Register/>}/>
                        <Route path="books" element={<Books/>}/>
                        <Route path="authors" element={<Authors/>}/>
                        <Route path="countries" element={<Countries/>}/>
                        <Route path={'statistics'} element={<CategoryStatistics/>}/>
                    </Route>
                </Routes>
            </BrowserRouter>
        </ThemeProvider>
    );
}

export default App;