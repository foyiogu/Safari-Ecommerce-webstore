import React from 'react';
// import '../../src/App.css';   
import Hero  from '../components/Hero';
import CardProducts from '../components/CardProducts'
import Pagination from '../components/Pagination';
import BackToTop from '../components/BackToTop';
import Footer from '../components/Footer';


function Home(){
    return (
        <>
        <Hero />
        <CardProducts />
        <Pagination />
        <BackToTop />
        <Footer />
        </>
    )
}

export default Home;