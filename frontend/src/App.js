import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import UploadPage from './pages/UploadPage';
import SearchPage from './pages/SearchPage';
import EditPage from './pages/EditPage';

function App() {
  return (
    <Router>
      <div style={{padding: '20px'}}>
        <h1>Retail Pricing Records</h1>
        <nav>
          <Link to="/upload">Upload CSV</Link> | {" "}
          <Link to="/search">Search Records</Link>
        </nav>
        <hr />
        <Routes>
          <Route path="/upload" element={<UploadPage />} />
          <Route path="/search" element={<SearchPage />} />
          <Route path="/edit/:id" element={<EditPage />} />
          <Route path="/" element={<div>Welcome to Pricing Records System</div>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
