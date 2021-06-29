import React, {useState} from 'react';
import Select from 'react-select';
import makeAnimated from 'react-select/animated';
import './AdminProducts.css';
import AdminLayout from '../../components/adminlayout/AdminLayout';
import {categories} from '../../data/categories';
import {subCategories} from '../../data/subCategories';
import {useForm} from 'react-hook-form';
import {yupResolver} from '@hookform/resolvers/yup';
import * as yup from 'yup';

const schema = yup.object().shape({
  title: yup.string().required(),
  price: yup.string().required(),
  description: yup.string().required,
})

const AdminProducts = (props) => {

  const {register, handleSubmit, errors} = useForm({
    resolver: yupResolver(schema),
  });

  const [selectedImages, setSelectedImages] = useState([]);
  const [category, setCategory] = useState([]);
  const [subCategory, setSubCategory] = useState([]);

  const handleImageChange = (e) => {
    if (e.target.files) {
      const filesArray = Array.from(e.target.files).map((file) => URL.createObjectURL(file));

      setSelectedImages((prevImages) => prevImages.concat(filesArray));
      Array.from(e.target.files).map(
         (file) => URL.revokeObjectURL(file) // avoid memory leak
      );
    }
  };

  const renderPhotos = (source) => {
    // console.log('source: ', source);
    return source.map((photo) => {
      return <img className="product-image" src={photo} alt="" key={photo} />;
    });
  };

  const customTheme = (theme) => {
    return {
      ...theme,
      colors: {
        ...theme.colors,
        primary25: '#F5F5F5',
        primary: '#ED165F',
      }
    }
  }

  const submitHandler = (data) => {}

  return (
     <AdminLayout>
       <form onSubmit={handleSubmit(submitHandler)}>
         <div className="product-wrapper">
           <h1 className="title">Products</h1>
           <div className="product-list-wrapper">
             <h3 className="info-title">Add Products</h3>

             <div className="product-details-wrapper">

               <div className="product-details">

                 <div className="name-price">

                   <div>
                     <h4>Title:</h4>
                     <input name="title" type="text" placeholder="Title of Product"/>
                   </div>
                   <div>
                     <h4>Price:</h4>
                     <input name="price" type="text" placeholder="Price of Product"/>
                   </div>
                 </div>

                 <div>
                   <h4>Add Image(s):</h4>
                   <input name="image" type="file" id="file" multiple onChange={handleImageChange}/>
                   <div className="product-image-wrapper">
                     <div className="label-holder">
                       <label htmlFor="file" className="label">
                         <i className="material-icons">add_a_photo</i>
                       </label>
                     </div>
                     <div className="result">{renderPhotos(selectedImages)}</div>
                   </div>
                 </div>


               </div>

               <div className="description-details">
                 <label>
                   <h4>Description:</h4>
                   <textarea name="description" placeholder="Please Enter A Description" className="description"/>
                 </label>
               </div>
             </div>

             <div className="category-main-wrapper">

               <div className="category-wrapper">
                 <h4>Category: </h4>
                 <Select
                    name="category"
                    components={makeAnimated()}
                    onChange={setCategory}
                    theme={customTheme}
                    isMulti
                    isSearchable
                    options={categories}
                    placeholder="Select a category"
                    noOptionsMessage={() => "No matching category found!"}
                 />
               </div>

               <div className="category-wrapper">
                 <h4>Sub-Category: </h4>
                 <Select
                    name="sub-category"
                    components={makeAnimated()}
                    onChange={setSubCategory}
                    theme={customTheme}
                    isMulti
                    isSearchable
                    options={subCategories}
                    placeholder="Select a sub-category"
                    noOptionsMessage={() => "No matching sub-category found!"}
                 />
               </div>
             </div>
           </div>
         </div>
         <input type="submit" id="submit" value="Add Product" className="add-product-btn"/>
       </form>

       <div className="product-wrapper">
         <div className="title">Product List</div>
         <div className="product-list-wrapper">

         </div>
       </div>
     </AdminLayout>
  );
}

export default AdminProducts;