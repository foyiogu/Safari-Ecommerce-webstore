import React from 'react';
import  {InputField, CheckBox, Label}  from './form-fields';



const SignIn = ()=>{
    const [fields, setFields] = React.useState({email:'', password:''});

    const handleChange = (event)=>{
        const {name,value} = event.target;
            setFields(fields =>({...fields, [name]:value}))
    }
    return (
        <section className='sign-in'>
        <h5 className='form-title'>SIGN IN</h5>
        <form method='POST'>
            <Label elementId='user-email' text='email' />
            <InputField
            type='email'
            value = {fields.email}
            placeholder=''
            id='user-email'
            changeHandler= {handleChange}
            name='email'
            />

            <Label elementId='user-password' text='password' />
            <InputField
            type='password'
            value = {fields.password}
            placeholder=''
            name='password'
            id='user-password'
            changeHandler= {handleChange}
            />
            <CheckBox id='remember-me' />
            <Label elementId='remember-me' text='remember my details' />
            <button type='submit' className='btn-block btn-block--contained' >Sign In</button>
        </form>
        </section>
    );
}

export default SignIn;