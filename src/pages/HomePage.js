import { React, useState, useEffect} from 'react';
// import '../../src/App.css';   
import Hero  from '../components/Hero';
import CardHomeProducts from '../components/CardHomeProducts'
import Pagination from '../components/Pagination';
import Footer from '../components/Footer';
import ProductApi from '../apis/ProductApi';


function HomePage() {
    
    const [products, setProducts] = useState([]);

    useEffect( async () => {
        let mounted = true;

        const allProducts = await ProductApi.getAllProducts();

        console.log(allProducts)

        if(mounted) setProducts(allProducts.content);

        return () => mounted = false;

    }, [])

    return (
        <>
        <Hero />
        <CardHomeProducts products={products}/>
        <Pagination />
        <Footer />
        </>
    )
}


export default HomePage;