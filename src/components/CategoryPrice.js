import React from 'react'
import '../styles/Components/_category_price.scss'
import { Table, Grid, Dropdown, Segment, Container, Menu, GridColumn } from 'semantic-ui-react'


function CategoryPrice() {
    return (
        <>
            <div className="Category-Price">
                <form>
                    <input type="checkbox" className="prizecheckbox"/> <label className="price-label">₦0 - ₦10,000 </label><br/><br/>
                    <input type="checkbox" className="prizecheckbox" /> <label className="price-label">₦10,000 - ₦20,000</label><br/><br/>
                    <input type="checkbox" className="prizecheckbox" /> <label className="price-label">₦20,000 - ₦50,000</label><br/><br/>
                    <input type="checkbox" className="prizecheckbox" /> <label className="price-label">₦50,000 - ₦100,000</label><br/><br/>
                    <input type="checkbox" className="prizecheckbox" /> <label className="price-label">₦100,000 - ₦200,000</label><br/><br/>
                    <div className="price-filter">
                        <Grid>
                            <GridColumn width="4">
                            <input type="text" className="prizetext" placeholder="₦"/>
                            </GridColumn>
                            <GridColumn width="1">
                            <p className="filter-between">To</p>
                            </GridColumn>
                            <GridColumn width="4">
                            <input type="text" className="prizetext" placeholder="₦"/>
                            </GridColumn>
                            <GridColumn width="6">
                            <button className="pricefilterapply">Apply</button>
                            </GridColumn>
                    
                        </Grid>
                        {/* <div className="prize-A">
                            <input type="text" className="prizetext" placeholder="₦"/>
                        </div>
                        <div className="prize-B">
                            <input type="text" className="prizetext" placeholder="₦"/>
                        </div> */}
                    </div>
                    {/* <button className="pricefilterapply">Apply</button> */}
                </form>
            </div>
        </>
    )
}

export default CategoryPrice