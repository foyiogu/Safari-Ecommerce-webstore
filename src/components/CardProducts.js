import React from 'react'
import CardProductItem from './CardProductItem'
import './css/CardProducts.css'
  
function CardProducts() {
    return (
        <div className="cards">
            <h1 className="shopstyleh">Shop with Style</h1>
            <p class="shopstylep">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vitae gravida cursus adipiscing 
             viverra at tortor, egestas odio parturient. Morbi ut lorem in erat. Et et molestie diam diam ultricies. 
             Scelerisque duis diam ac cras dictum adipiscing. Venenatis at sit proin ut vitae adipiscing id facilisis.
             </p>
             <div className="cards__container">
                 <div className="cards__wrapper">
                     <ul className="cards__items">
                        <CardProductItem 
                            src="/images/products/gustavo-spindula-l7wrlsKDmCE-unsplash 1.png"
                            name="Multicolored armless top"
                            price="₦10,000"
                            path="/cart"
                            favourtie="/favourite"

                        />
                        <CardProductItem 
                            src="/images/products/Grey Bee Watch 1.png"
                            name="Grey bee wrist-watch"
                            price="₦10,000"
                            path="/cart"
                            favourite="/favourite"

                        />
                        <CardProductItem 
                            src="/images/products/engin-akyurt-TDOClniEwmI-unsplash 1.png"
                            name="Blue botton-down gown"
                            price="₦10,000"
                            path="/cart"
                            favourtie="/favourite"
                        />
                        <CardProductItem 
                            src="/images/products/engin-akyurt-BAoncvnAl8I-unsplash 1.png"
                            name="Red lightweight gown"
                            price="₦10,000"
                            path="/cart"
                            favourtie="/favourite"

                        />
                     </ul>
                 </div>
             </div>
        </div>
    )
}

export default CardProducts
