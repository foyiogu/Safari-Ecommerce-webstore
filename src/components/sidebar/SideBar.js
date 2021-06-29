import React from 'react';
import {NavLink, withRouter} from 'react-router-dom';
import './SideBar.css';

const SideBar = ({menuItems}) => {

  return (
     <div className="sidebar-wrapper">
       <div className="menu-wrapper">
         {
           menuItems.map((menuItem, index) => (
              <NavLink
                 className='nav-link'
               to={menuItem.path}
                 key={index}
                 exact={menuItem.exact}
                 activeStyle={{
                   color: '#FFFFFF',
                   background: '#ED165F',
                   borderRadius: '0 50px 50px 0',
                 }}
              >
                {menuItem.name}
              </NavLink>
           ))
         }
       </div>
     </div>
  );
}

export default withRouter(SideBar);