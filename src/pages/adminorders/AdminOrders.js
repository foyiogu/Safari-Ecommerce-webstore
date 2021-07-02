import React from 'react';
import './AdminOrders.css';
import AdminLayout from "../../components/adminlayout/AdminLayout";

const AdminOrders = (props) => {
  return (
     <AdminLayout>
       <div className="orders-wrapper">
        <div className="title">Orders</div>
         <div className="orders-list-wrapper">

         </div>
       </div>
     </AdminLayout>
  );
}

export default AdminOrders;