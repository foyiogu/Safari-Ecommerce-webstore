import React from 'react';
import './AdminDashboard.css';
import AdminLayout from "../../components/adminlayout/AdminLayout";

const AdminDashboard = (props) => {
  return (
     <AdminLayout>
       <div className="wrapper">
         <h1 className="title">Dashboard</h1>

         <div className="info-wrapper">
           <div className="info-content">
             <h3 className="info-title">Products</h3>
             <h2>3925</h2>
           </div>
           <div className="info-content">
             <h3 className="info-title">Orders</h3>
             <h2>1335</h2>
           </div>
           <div className="info-content">
             <h3 className="info-title">Revenue</h3>
             <h2>#371,335</h2>
           </div>
         </div>

         <div className="account-info">
           <h3 className="info-title">Account Info</h3>
         </div>
       </div>
     </AdminLayout>
  );
}

export default AdminDashboard;