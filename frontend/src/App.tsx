import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { CssBaseline, ThemeProvider, createTheme } from '@mui/material';
import Layout from './components/Layout';
import Home from './pages/Home';
import Books from './pages/Books';
import Authors from './pages/Authors';
import Countries from './pages/Countries';
import Login from "./pages/Login.tsx";
import Register from "./pages/Register.tsx";

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
        <CssBaseline /> {/* Ова ги ресетира основните стилови на прелистувачот */}
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Layout />}>
              <Route index element={<Home />} />
              <Route path={"login"} element={<Login/>}/>
              <Route path={"register"} element={<Register/>}/>
              <Route path="books" element={<Books />} />
              <Route path="authors" element={<Authors />} />
              <Route path="countries" element={<Countries />} />
            </Route>
          </Routes>
        </BrowserRouter>
      </ThemeProvider>
  );
}

export default App;