import React, {useRef, useState} from "react";
import search from '../images/search.png'
import './searchButton.css';

const SearchByIngredient = () => {
    const [enteredIngredient,setEnteredIngredient] = useState('');
    const ingredientInputRef = useRef('');

    const ingredientInputChangeHandler = event =>{
        setEnteredIngredient(event.target.value);
    }
    const formSubmissionHandler = event =>{
        event.preventDefault();
        console.log(enteredIngredient);
        const enteredValue = ingredientInputRef.current.value;
        console.log(enteredValue);
    }
    return (
        <form onSubmit={formSubmissionHandler} className="form">
            <input placeholder="Search by ingredient" ref={ingredientInputRef} className="search-field" onChange={ingredientInputChangeHandler}/>
            <button type="submit" className="search-button">
                <img src={search} alt="Magnifying glass"/>
            </button>
        </form>
    );
}

export default SearchByIngredient;