import Navbar from './components/Navbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import './styles/style.scss';
import Home from './pages/HomePage'
import About from './pages/AboutPage'
import Contact from './pages/ContactPage'
import Cart from './pages/CartPage'
import Checkout from './pages/CheckoutPage'
import SignInSignUp from './pages/SignInSignUpPage'
import TermsConditions from './pages/TermsConditionsPage'
// import Clothes from './pages/categories/ClothesPage'
// import Shoes from './pages/categories/ShoesPage'
// import Accessories from './pages/categories/AccessoriesPage'
import AccountInfo from './pages/account/Account'
import AccountAddressBook from './pages/account/AddressBook'
import AccountMyOrders from './pages/account/MyOrders'
import AccountMyFavourites from './pages/account/MyFavourites'
import AdminDashboard from "./pages/admindashboard/AdminDashboard";
import AdminOrders from "./pages/adminorders/AdminOrders";
import AdminProducts from "./pages/adminproducts/AdminProducts";

function App() {
  return (
    <>
    <Router>
    <Navbar />
    <Switch>
      <Route path='/' exact component={Home}/>
      <Route path='/about' component={About} />
      <Route path='/contact' component={Contact} />
      <Route path='/termscondition' component={TermsConditions} />
      <Route path='/cart' component={Cart} />
      <Route path='/checkout' component={Checkout} />
      <Route path='/signin-signup' component={SignInSignUp} />
      {/* <Route path='/categories/clothes/' component={Clothes} />
      <Route path='/categories/shoes' component={Shoes} />
      <Route path='/categories/accessories' component={Accessories} /> */}
      <Route path='/account/information' component={AccountInfo} />
      <Route path='/account/addressbook' component={AccountAddressBook} />
      <Route path='/account/myorders' component={AccountMyOrders} />
      <Route path='/account/myfavourites' component={AccountMyFavourites} />
      <Route path='/admin/dashboard' component={AdminDashboard} />
      <Route path='/admin/orders' component={AdminOrders} />
      <Route path='/admin/products' component={AdminProducts} />
    </Switch>
    </Router>
    </>
  );
}

export default App;