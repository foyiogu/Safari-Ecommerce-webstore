import React from 'react';
import { InputField, Label } from './form-fields';

const SignUp = ()=>{
    const [fields, setFields] = React.useState({email:'', password:'', firstName:'', lastName:'', confirmPassword:''})
    return <section className='sign-up'>
        <h5 className='form-title'>CREATE ACCOUNT</h5>
        <form method='POST'>
            <Label elementId='first-name' text='first name' />
            <InputField
            type='text'
            value = {fields.firstName}
            placeholder=''
            id='first-name'
            name='firstName'
            />
            <Label elementId='last-name' text='last name' />
            <InputField
            type='text'
            value = {fields.lastName}
            placeholder=''
            id='last-name'
            name='firstName'
            />

          
            <Label elementId='email' text='email' />
            <InputField
            type='email'
            value = {fields.email}
            placeholder=''
            id='email'
            name='email'
            />

            <Label elementId='password' text='create password' />
            <InputField
            type='password'
            value = {fields.password}
            placeholder=''
            name='password'
            id='password'
            />
            <Label elementId='confirm-password' text='confirm password' />
            <InputField
            type='password'
            value = {fields.confirmpassword}
            placeholder=''
            name='confirmPassword'
            id='confirm-password'
            />
            <button type='submit' className='btn-block btn-block--contained' >create account</button>
        </form>
    </section>;
}

export default SignUp;