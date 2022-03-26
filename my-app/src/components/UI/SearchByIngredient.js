import React, {useRef} from "react";
import search from '../images/search.png'
import './searchButton.css';
import {useHistory} from "react-router-dom";

const SearchByIngredient = (props) => {
    const ingredientInputRef = useRef('');
    let history = useHistory();

    const formSubmissionHandler = event => {
        event.preventDefault();
        history.push("/");
        props.parentCallBack(ingredientInputRef.current.value)
    }
    return (
        <form onSubmit={formSubmissionHandler} className="form">
            <input placeholder="Search by ingredient" ref={ingredientInputRef} className="search-field"/>
            <button type="submit" className="search-button">
                <img src={search} alt="Magnifying glass"/>
            </button>
        </form>
    );
}

export default SearchByIngredient;