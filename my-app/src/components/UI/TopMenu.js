import React from 'react';
import './w3.css';
import './TopMenu.css';


const TopMenu = () => {

    function w3_open() {
        document.getElementById("mySidebar").style.display = "block";
    }
    function addRecipeOpen() {
        document.getElementById("addRecipe").style.display = "block";
        w3_close();
    }

    function w3_close() {
        document.getElementById("mySidebar").style.display = "none";
    }


    return (
        <div>
            <nav className="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left"
                 style={{"display": "none", "z-index": "2", "width": "40%", "min-width": "300px"}} id="mySidebar">
                <button onClick={w3_close} className="w3-bar-item w3-button">My Food</button>
                <button onClick={addRecipeOpen} className="w3-bar-item w3-button">Create New Recipe</button>
            </nav>
            <div className="w3-top">
                <div className="w3-white w3-xlarge" style={{"max-width": "1200px", "margin": "auto"}}>
                    <button className="w3-button w3-padding-16 w3-left" onClick={w3_open}>☰</button>
                    <div className="w3-right w3-padding-16">Mail</div>
                    <div className="w3-center w3-padding-16">My Food</div>
                </div>
            </div>
        </div>
    );
}

export default TopMenu;
