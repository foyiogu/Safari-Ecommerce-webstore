import React from 'react';
import SideBar from "../sidebar/SideBar";
import './AdminLayout.css';

const AdminLayout = ({ children }) => {

  const menu = [
    {
      icon: "",
      name: "Dashboard",
      path: "/admin/dashboard",
      exact: true
    },
    {
      icon: "",
      name: "Orders",
      path: "/admin/orders",
      exact: true
    },
    {
      icon: "",
      name: "Products",
      path: "/admin/products",
      exact: true
    },
  ]

  return (
     <div className="dashboard-wrapper">
       <SideBar menuItems={menu} />
       <div>
         { children }
       </div>
     </div>
  );
}

export default AdminLayout;