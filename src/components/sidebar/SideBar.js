import React from 'react';
import {NavLink, withRouter} from 'react-router-dom';
import './SideBar.css';
import '../../styles/Components/_button.scss'

const SideBar = ({menuItems}) => {

  return (
    <div>
    
     <div className="sidebar-wrapper">
     <h1 style={{textAlign:'center', marginTop:'20px'}}>Dashboard</h1>
       <div className="menu-wrapper">
         { 
           menuItems.map((menuItem, index) => (   
              <NavLink
                 className={`nav-link`}
               to={menuItem.path}
                 key={index}
                 exact={menuItem.exact}
                 activeStyle={{
                   color: '#FFFFFF',
                   background: '#ED165F',
                   borderRadius: '0 50px 50px 0',
                 }}
              >
                <i className={`${menuItem.icon}`}></i>
                {menuItem.name}
              </NavLink>
           ))
         }
       </div>
       <button className="signout-button"><img src="../images/sign-out-icon.svg" alt="signout-icon" className="signout-image"></img>Sign Out </button>
     </div>
      </div>
  );
}

export default withRouter(SideBar);