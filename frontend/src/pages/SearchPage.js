import React, { useState } from 'react';
import api from '../api';
import RecordTable from '../components/RecordTable';

function SearchPage() {
  const [storeId, setStoreId] = useState('');
  const [sku, setSku] = useState('');
  const [records, setRecords] = useState([]);

  const handleSearch = async () => {
    let query = '/pricing/search?';
    if (storeId) query += `storeId=${storeId}`;
    else if (sku) query += `sku=${sku}`;

    try {
      const response = await api.get(query);
      setRecords(response.data);
    } catch (error) {
      console.error('Error fetching records', error);
    }
  };

  return (
    <div>
      <h2>Search Records</h2>
      <div>
        <label>Store ID: </label>
        <input value={storeId} onChange={(e) => setStoreId(e.target.value)} />
        <label> SKU: </label>
        <input value={sku} onChange={(e) => setSku(e.target.value)} />
        <button onClick={handleSearch}>Search</button>
      </div>
      <RecordTable records={records} />
    </div>
  );
}

export default SearchPage;
