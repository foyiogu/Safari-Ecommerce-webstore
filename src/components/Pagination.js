import React from 'react'
// import './css/Pagination.css';
// import { Link } from 'react-router-dom';

export default function Pagination() {
    return (
        <div className="container">
             <div className="pagination">
            <a href="/" className="blocks active-pagination">1</a>
            <a href="/" className="blocks">2</a>
            <a href="/" className="blocks">3</a>
            <a href="/" className="blocks"><img src="/images/next.svg" alt="pagination-next"/></a>
        </div>
        </div>
    )
}
