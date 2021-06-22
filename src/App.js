import Navbar from './components/Navbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import './App.css';
import Home from './pages/Home'
import About from './pages/About'
import Contact from './pages/Contact'
import Cart from './pages/Cart'
import Checkout from './pages/Checkout'
import SignInSignUp from './pages/SignInSignUp'
import TermsConditions from './pages/TermsConditions'
import Clothes from './pages/categories/Clothes'
import Shoes from './pages/categories/Shoes'
import Accessories from './pages/categories/Accessories'
import AccountInfo from './pages/account/Account'
import AccountAddressBook from './pages/account/AddressBook'
import AccountMyOrders from './pages/account/MyOrders'
import AccountMyFavourites from './pages/account/MyFavourites'

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
      <Route path='/categories/clothes/' component={Clothes} />
      <Route path='/categories/shoes' component={Shoes} />
      <Route path='/categories/accessories' component={Accessories} />
      <Route path='/account/information' component={AccountInfo} />
      <Route path='/account/addressbook' component={AccountAddressBook} />
      <Route path='/account/myorders' component={AccountMyOrders} />
      <Route path='/account/myfavourites' component={AccountMyFavourites} />
    </Switch>
    </Router>
    </>
  );
}

export default App;