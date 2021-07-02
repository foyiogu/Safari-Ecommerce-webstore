import React from "react";
import CardProductItem from "./CardProductItem";
import "../styles/Components/_card-product.scss";

function CardProducts(props) {
  return (
    <div className="cards">
      <h1 className="shopstyleh">Shop with Style</h1>
      <p className="shopstylep">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vitae gravida
        cursus adipiscing viverra at tortor, egestas odio parturient. Morbi ut
        lorem in erat. Et et molestie diam diam ultricies. Scelerisque duis diam
        ac cras dictum adipiscing. Venenatis at sit proin ut vitae adipiscing id
        facilisis.
      </p>
      <div className="cards__container">
        <div className="cards__wrapper">
          <ul className="cards__items">
            {props.products.map((product, index) => {
              const { id, name, price } = product;
              return (
                <CardProductItem
                  key={index}
                  id={product.id}
                  src="/images/products/gustavo-spindula-l7wrlsKDmCE-unsplash 1.png"
                  name={product.name}
                  price={"₦" + product.price}
                  path={`/cart/${id}`}
                  // favourtie="/favourite"
                  favourtie={`/api/customer/favourite/${product.id}`}
                />
              );
            })}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default CardProducts;
