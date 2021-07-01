import React from 'react';
import DashboardLayout from "../../components/userdashboard/DashboardLayout";

const MyOrders = (props) => {
  return (
     <DashboardLayout>
         <div className="wrapper">

         <div className="info-wrapper">
           <div className="info-content">
             <h3 className="info-title">Products</h3>
             <h2>3925</h2>
           </div>
           <div className="info-content">
             <h3 className="info-title">payment details</h3>
             <h2>1335</h2>
           </div>
           <div className="info-content">
             <h3 className="info-title">Delivery method</h3>
             <h2>#371,335</h2>
           </div>
         </div>

         <div className="account-info">
           <h3 className="info-title">Account Info</h3>
         </div>
       </div>
     </DashboardLayout>
  );
}

export default MyOrders;