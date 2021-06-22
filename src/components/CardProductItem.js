import React from 'react'
import {Link} from 'react-router-dom'
// import { Button } from './Button'

function CardProductItem(props) {
    return (
        <>
              <li className="cards__item">
                  <Link className="cards_item_link" to={props.path}>
                      <figure className="cards_item_pic_wrap">
                            <img src={props.src} alt="ProductPhoto" className="cards_item_img" />
                      </figure>
                      <div className="cards__item__info">
                          <h5 className="cards__item__text">{props.name}</h5>
                          <p className="cards__item__price">{props.price}</p>
                          <div className="product-item-hover">
                          <Link className="favourite" to={props.favourite}>
                          <i class="far fa-heart"></i>
                          </Link>
                          <button className="addtocart">ADD TO CART <i class="cart-icon fas fa-shopping-cart"></i></button>
                          </div>
                      </div>
                  </Link>
              </li>
        </>
    )
}

export default CardProductItem
