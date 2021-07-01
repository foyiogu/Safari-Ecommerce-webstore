import React from "react";
import { Link } from "react-router-dom";
import CartContext from "../store/Cart-Context";
import { useContext, useEffect, useState } from "react";
import productApis from "../apis/ProductApi";
import FavouriteContext from "../store/Favourite-Context";

function CardProductItem(props) {
  const [cartItemsDB, setCartItemsDB] = useState([]);

  useEffect(async () => {
    // const cart = await productApis.getAllProductsInCart;
    //  console.log(`CART ITEMS ARE ${cart}`)
    //  const cats = cart;
    //  setCartItemsDB(cats);
  }, []);

  const cartCtx = useContext(CartContext);
  const favoriteCtx = useContext(FavouriteContext);

  const itemIsInCart = cartCtx.itemIsInCart(props.id);
  const itemIsFavorite = favoriteCtx.isInFavourite(props.id);

  function toggleAddToCartHandler() {
    if (itemIsInCart) {
      cartCtx.removeCartItem(props.id);
      productApis.deleteProductFromCart(props.id);
    } else {
      cartCtx.addToCart({
        id: props.id,
        price: props.price,
        name: props.name,
        image: props.image,
      });

      productApis.addProductToCart(props.id);
    }
  }

  function toggleAddToFavoriteHandler() {
    if (itemIsFavorite) {
      favoriteCtx.removeFavourite(props.id);
      // productApis.deleteProductFromCart(props.id)
    } else {
      favoriteCtx.addToFavourite({
        id: props.id,
        price: props.price,
        name: props.name,
        image: props.image,
      });
      productApis.addProductToFavorite(props.id);
    }
  }

  return (
    <>
      <li className="cards__item">
        <div>
          <figure className="cards_item_pic_wrap">
            <img
              src={props.src}
              alt="ProductPhoto"
              className="cards_item_img"
            />
          </figure>
          <div className="cards__item__info">
            <h5 className="cards__item__text">{props.name}</h5>
            <p className="cards__item__price">{props.price}</p>
            <div className="product-item-hover">
              <div className="favourite" onClick={toggleAddToFavoriteHandler}>
                <i class="far fa-heart">{itemIsFavorite ? "x" : "a"}</i>
              </div>

              <button className="addtocart" onClick={toggleAddToCartHandler}>
                {itemIsInCart ? "Remove from cart" : "ADD TO CART"}
                <i class="cart-icon fas fa-shopping-cart"></i>
              </button>
            </div>
            <div>{props.id}</div>
          </div>
        </div>
      </li>
    </>
  );
}

export default CardProductItem;
