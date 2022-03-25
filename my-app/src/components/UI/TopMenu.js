import React from 'react';
import './w3.css';
import './TopMenu.css';
import {Link} from "react-router-dom";
import SearchByIngredient from "./SearchByIngredient";

const TopMenu = (props) => {
    const handleCallback = (ingredientInputRef) => {
        childToParent(ingredientInputRef);
    }
    const childToParent = (ingredientInputRef) => {
        props.onParentCallback(ingredientInputRef);
    }

    function w3_open() {
        document.getElementById("mySidebar").style.display = "block";
    }

    function addRecipeOpen() {
        w3_close();
        document.getElementById("addRecipe").style.display = "block";
    }

    function w3_close() {
        document.getElementById("mySidebar").style.display = "none";
    }

    return (
        <div>
            <nav className="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left"
                 style={{"display": "none", "zIndex": "2", "width": "40%", "minWidth": "300px"}} id="mySidebar">
                <Link onClick={w3_close} to="/" className="w3-bar-item w3-button">My Food</Link>
                <Link to="/" onClick={addRecipeOpen}  className="w3-bar-item w3-button">Create New Recipe</Link>
            </nav>
            <div className="w3-top">
                <div className="w3-white w3-xlarge" style={{"maxWidth": "1200px", "margin": "auto"}}>
                    <button className="w3-button w3-padding-16 w3-left" onClick={w3_open}>☰</button>
                    <div className="w3-right w3-padding-16">
                        <SearchByIngredient parentCallBack={handleCallback}/>
                    </div>
                    <div className="w3-center w3-padding-16">My Food</div>
                </div>
            </div>
        </div>
    );
}

export default TopMenu;
