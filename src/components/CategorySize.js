import React from 'react'
import '../styles/Components/_category_size.scss'

function CategorySize() {
    return (
        <>
           <div className="categorySizeContainer">
              <div className="CategorySize_section1"> 
                <div className="categorySize XSS"><span>XXS</span></div>
                <div className="categorySize XS"><span>XS</span></div>
                <div className="categorySize S"><span>S</span></div>
                <div className="categorySize M"><span>M</span></div>
               </div>
               <div className="CategorySize_section2"> 
                <div className="categorySize XSS"><span>L</span></div>
                <div className="categorySize XS"><span>XL</span></div>
                <div className="categorySize S"><span>23</span></div>
                <div className="categorySize M"><span>24</span></div>
               </div>
               <div className="CategorySize_section3"> 
                <div className="categorySize XSS"><span>25</span></div>
                <div className="categorySize XS"><span>26</span></div>
                <div className="categorySize S"><span>27</span></div>
                <div className="categorySize M"><span>28</span></div>
               </div>
               <div className="CategorySize_section4"> 
                <div className="categorySize XSS"><span>29</span></div>
                <div className="categorySize XS"><span>30</span></div>
                <div className="categorySize S"><span>31</span></div>
                <div className="categorySize M"><span>32</span></div>
               </div>
            </div> 
        </>
    )
}

export default CategorySize