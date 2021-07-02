import {config} from "./baseUrl";
import axios from "axios";
// import UserContext from "../context/UserContext";

const doLogin = async (event, formData, buttonState, setAlertBox) => {
    event.preventDefault();
    buttonState(true);
    handleSingleSignOn(formData);
    const url = config.baseURL + "/login";
    try {
        const apiResponse = await axios.post(url, formData);
        const {data} = apiResponse;
        setAlertBox({state: true, message: 'Logged in successfully', type: 'success'});
        localStorage.setItem('token', `Bearer ${data.token}`);
        window.location = '/';
    } catch (e) {
        const { status} = await e.response;
        if (status >= 400 ){
            setAlertBox({state: true, message:'Invalid credentials', type: 'error'});
        }
        buttonState('');
    }
};


const doRegistration = async (event, formData, buttonState, setAlertBox) => {
    event.preventDefault();
    setAlertBox({state:true, message:'Loading...', type:'success'});
    buttonState('disabled');
    const {email, password} = formData;

    try{
        console.log('call to register')
        const url = config.baseURL + '/api/register';
        const registrationResponse = await axios.post(url, formData);
        const {status} = registrationResponse;
        setAlertBox({state:true, message:'Registration Successful!', type:'success'});
        buttonState('disable-button');
        status === 200 && await doLogin(event,{email, password, rememberMe:true},buttonState,setAlertBox);
    }catch (e) {
        const {status}= e.response;
        if (status === 400 ){
            setAlertBox({state:true, message:'Already registered, try Login!.', type:'error'});
        }
        buttonState('');
    }
}


const handleSingleSignOn = (formData) => {
    const {rememberMe, email}  = formData;
    if (formData.rememberMe) {
        localStorage.setItem('rememberMe', JSON.stringify({email, rememberMe}));
    } else {
        if (localStorage.getItem('rememberMe')) {
            localStorage.removeItem('rememberMe');
        }
    }
};

export {doLogin, doRegistration};
