import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../api';

function EditPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [record, setRecord] = useState(null);

  useEffect(() => {
    const fetchRecord = async () => {
      try {
        const response = await api.get('/pricing/search');
        const data = response.data;
        const rec = data.find(r => r.id === parseInt(id, 10));
        setRecord(rec);
      } catch (error) {
        console.error(error);
      }
    };
    fetchRecord();
  }, [id]);

  const handleSave = async () => {
    try {
      await api.put(`/pricing/${id}`, record);
      navigate('/search');
    } catch (error) {
      console.error('Error saving record:', error);
    }
  };

  if (!record) return <div>Loading...</div>;

  return (
    <div>
      <h2>Edit Record {id}</h2>
      <div>
        <label>Store ID: </label>
        <input value={record.storeId} onChange={(e) => setRecord({...record, storeId: e.target.value})} />
      </div>
      <div>
        <label>SKU: </label>
        <input value={record.sku} onChange={(e) => setRecord({...record, sku: e.target.value})} />
      </div>
      <div>
        <label>Product Name: </label>
        <input value={record.productName} onChange={(e) => setRecord({...record, productName: e.target.value})} />
      </div>
      <div>
        <label>Price: </label>
        <input value={record.price} onChange={(e) => setRecord({...record, price: e.target.value})} />
      </div>
      <div>
        <label>Record Date: </label>
        <input type="date" value={record.recordDate} onChange={(e) => setRecord({...record, recordDate: e.target.value})} />
      </div>
      <button onClick={handleSave}>Save</button>
    </div>
  );
}

export default EditPage;
