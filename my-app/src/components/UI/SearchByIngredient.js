import React, {useRef, useState} from "react";
import search from '../images/search.png'
import './searchButton.css';

const SearchByIngredient = (props) => {
    const ingredientInputRef = useRef('');
    const [error, setError] = useState(null);

    const formSubmissionHandler =  event => {
        event.preventDefault();
        props.parentCallBack(ingredientInputRef.current.value);
        // const enteredValue = ingredientInputRef.current.value;
        // enteredValue.trim();
        // try {
        //     const response = await fetch("http://localhost:8080/recipe/" + enteredValue);
        //     if (!response.ok) {
        //         throw new Error("Something went wrong");
        //     }
        //     const data = await response.json();
        //     console.log(data);
        // } catch (error) {
        //     setError(error.message);
        // }
    }
    return (
        <form onSubmit={formSubmissionHandler} className="form">
            <input placeholder="Search by ingredient" ref={ingredientInputRef} className="search-field"/>
            <button type="submit" className="search-button">
                <img src={search} alt="Magnifying glass"/>
            </button>
            {error!=null && <p>{error}</p>}
        </form>
    );
}

export default SearchByIngredient;