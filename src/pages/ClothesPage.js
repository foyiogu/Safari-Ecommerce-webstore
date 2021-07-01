import React from "react";
import CardProductItem from "../components/CardProductItem";
import CartContext from "../store/Cart-Context";
import { useContext } from "react";
import { useState, useEffect } from "react";
import productApis from "../apis/ProductApi";

function ClothesPage(props) {
  const cartCtx = useContext(CartContext);

  const { products } = props;
  console.log("THE CONTENT ISSSSSSS", products);
  return (
    <div>
      <div className="productmain">
        <div className="cards__wrapper">
          <ul className="cards__items clothes__items">
            {products ? (
              products.map((product, index) => {
                return (
                  <CardProductItem
                    key={index}
                    id={product.id}
                    src="/images/products/gustavo-spindula-l7wrlsKDmCE-unsplash 1.png"
                    price={product.price}
                    name={product.name}
                    favourtie={`/api/customer/favourite/${product.id}`}
                  />
                );
              })
            ) : (
              <div></div>
            )}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default ClothesPage;
