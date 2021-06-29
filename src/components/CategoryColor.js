import React from 'react'
import '../styles/Components/_category_color.scss'
import { Table, Grid, Dropdown, Segment, Container, Menu, GridColumn } from 'semantic-ui-react'

function CategoryColor() {
    return (
        <> 

              <div className="categoryColorContainer">

              <div className="CategoryColor_section1"> 
                <div className="">
                    <img src="/images/colorSwatches/Beige.svg" className="color"/>
                <div className="colors">Beige</div>
                </div>
                <div className="">
                    <img src="/images/colorSwatches/Blue.svg" className="color"/>
                 <div className="colors">Blue</div></div>
                <div className="">
                    <img src="/images/colorSwatches/Black.svg" className="color"/>
                    <div className="colors">Black</div></div>
               </div>
               <div className="CategoryColor_section3"> 
                <div className="">
                    <img src="/images/colorSwatches/Orange.svg" className="color"/>
                <div className="colors">Orange</div></div>
                <div className="">
                <img src="/images/colorSwatches/Green.svg" className="color"/>
                <div className="colors">Green</div></div>
                <div className="">
                    <img src="/images/colorSwatches/Brown.svg" className="color"/>
                <div className="colors">Brown</div></div>
               </div>
               <div className="CategoryColor_section3"> 
                <div className="">
                    <img src="/images/colorSwatches/Purple.svg" className="color"/>
                    <div className="colors">Purple</div></div>
                <div className="">
                    <img src="/images/colorSwatches/Gold.svg" className="color"/>
                    <div className="colors">Gold</div></div>
                <div className="">
                    <img src="/images/colorSwatches/Taupe.svg" className="color"/>
                <div className="colors">Taupe</div></div>
               </div>
               <div className="CategoryColor_section4"> 
                <div className="">
                    <img src="/images/colorSwatches/White.svg" className="color"/>
                <div className="colors">White</div></div>
                <div className="">
                    <img src="/images/colorSwatches/Pink.svg" className="color"/>
                <div className="colors">Pink</div></div>
                <div className="">
                    <img src="/images/colorSwatches/Red.svg" className="color"/>
                <div className="colors">Red</div></div>
               </div>
            </div> 

        </>
    )
}

export default CategoryColor