import React from 'react';
import { Link } from 'react-router-dom';

function RecordTable({ records }) {
  if (!records || records.length === 0) {
    return <p>No records found</p>;
  }

  return (
    <table border="1" cellPadding="5">
      <thead>
        <tr>
          <th>ID</th>
          <th>Store ID</th>
          <th>SKU</th>
          <th>Product Name</th>
          <th>Price</th>
          <th>Record Date</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {records.map(r => (
          <tr key={r.id}>
            <td>{r.id}</td>
            <td>{r.storeId}</td>
            <td>{r.sku}</td>
            <td>{r.productName}</td>
            <td>{r.price}</td>
            <td>{r.recordDate}</td>
            <td><Link to={`/edit/${r.id}`}>Edit</Link></td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default RecordTable;
