import React from 'react';
// import { Button } from './Button';
import { Link } from 'react-router-dom';

function Footer() {
  return (
    <div className='footer-container'>
      <div class='footer-links'>
        <div className='footer-link-wrapper'>
          <div class='footer-link-items'>
            <img src="http://localhost:3000/images/navbar/Logo.png" alt="" />
          </div>
          <div class='footer-link-items'>
            <Link to='/about'>About</Link>
            <Link to='/contact'>Contact</Link>
            <Link to='/termscondition'>Terms & Condition</Link>
          </div>
        </div>
        <div className='footer-link-wrapper'>
          <div class='footer-link-items'>
            <Link to='/'> <i class='fab fa-facebook-f' />Facebook</Link>
            <Link to='/'><i class='fab fa-twitter' />Twitter</Link>
            <Link to='/'> <i class='fab fa-instagram' />Insagram</Link>
          </div>
        </div>
        <div className='footer-link-wrapper'>
          <div class='footer-link-items'>
           <p className="footer-subscribe">Subscribe to our newsletter</p>
           <div className='input'>
          <form className="subscribe-form single-block-form">
            <input
              className='footer-input sbf__input'
              name='email'
              type='email'
              placeholder='Email Address'
            />
            <input type="submit" value="OK" class="sbf__button button"></input>
          </form>
        </div>
        </div>
        </div>
        <div className='footer-link-wrapper'>
        <div class='footer-link-items'>
        <p className="footer-contact">Lorem ipsum dolor sit amet<br />+234123456789<br />support@safariwebstore.com</p>
        </div>
        </div>
      </div>
    </div>
  );
}

export default Footer;