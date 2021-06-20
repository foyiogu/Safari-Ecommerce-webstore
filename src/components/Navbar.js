import React, { useState } from 'react';
// import { Button } from './Button';
import { Link } from 'react-router-dom';
import './css/Navbar.css';

function Navbar() {
  const [click, setClick] = useState(false);
//   const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

//   const showButton = () => {
//     if (window.innerWidth <= 960) {
//       setButton(false);
//     } else {
//       setButton(true);
//     }
//   };

//   useEffect(() => {
//     showButton();
//   }, []);

//   window.addEventListener('resize', showButton);

  return (
    <>
      <nav className='navbar'>
        <div className='navbar-container'>
          <div className='menu-icon' onClick={handleClick}>
            <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
          </div>
          <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            <li className='nav-item active'>
              <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                Home
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/categories/clothes'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Clothes
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='categories/shoes'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Shoes
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='categories/accessories'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Accessories
              </Link>
            </li>

            <li>
              <Link
                to='/sign-up'
                className='nav-links-mobile'
                onClick={closeMobileMenu}
              >
                Account
              </Link>
            </li>
          </ul>
          <div>
          <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
            <img src='../images/navbar/Logo.png' alt='safari-logo'/>
          </Link>
          </div>
          <ul className={click ? 'nav-menu-right active' : 'nav-menu-right'}>
            <li className='nav-item'>
              <input type="text" placeholder="Search..." className="searchbox"/>
            </li>
            <li className='nav-item'>
               <i class="fas fa-search search-icon"></i>
            </li>
            <li className='nav-item'> 
            <Link to='/signin-signup' className='nav-links'><i class="fas fa-user"></i></Link>
            </li>
            <li className='nav-item'> 
            <Link to='/cart' className='nav-links'><i class="fas fa-shopping-cart"></i></Link>
            </li>
            <li className='nav-item'> 
            <Link to='/favourites' className='nav-links'><i class="fas fa-heart"></i></Link>
            </li>
           
          </ul>
        </div>
      </nav>
    </>
  );
}

export default Navbar;
