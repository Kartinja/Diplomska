import React from 'react';
import './w3.css';
import './TopMenu.css';
import {Link,useHistory} from "react-router-dom";
import SearchByIngredient from "./SearchByIngredient";
import SearchByCalories from "./searchByComponents/SearchByCalories";
import SearchByCarbohydrate from "./searchByComponents/SearchByCarbohydrate";
import SearchByFat from "./searchByComponents/SearchByFat";
import SearchByProtein from "./searchByComponents/SearchByProtein";
import image from "../images/burger.png";


const TopMenu = (props) => {
    let history = useHistory();
    const handleCallbackFromSearchByIngredient = (ingredientInputRef) => {
        childToParentFromSearchByIngredient(ingredientInputRef);
    }
    const childToParentFromSearchByIngredient = (ingredientInputRef) => {
        w3_close();
        props.onParentCallback(ingredientInputRef);
    }

    const CaloriesHandleCallback = (minCaloriesRef,maxCaloriesRef) => {
        CaloriesChildToParent(minCaloriesRef,maxCaloriesRef);
    }
    const CaloriesChildToParent = (minCaloriesRef,maxCaloriesRef) => {
        w3_close();
        props.onParentCallBackForSearchByCalories(minCaloriesRef,maxCaloriesRef);
    }
    const CarbohydrateHandleCallback= (minCarbohydrateRef,maxCarbohydrateRef)=>{
        CarbohydrateChildToParent(minCarbohydrateRef,maxCarbohydrateRef);
    }
    const CarbohydrateChildToParent = (minCaloriesRef,maxCaloriesRef) => {
        w3_close();
        props.onParentCallBackForSearchByCarbohydrate(minCaloriesRef,maxCaloriesRef);
    }
    const FatHandleCallback= (minFatRef,maxFatRef)=>{
        FatChildToParent(minFatRef,maxFatRef);
    }
    const FatChildToParent = (minFatRef,maxFatRef) => {
        w3_close();
        props.onParentCallBackForSearchByFat(minFatRef,maxFatRef);
    }
    const ProteinHandleCallback= (minProteinRef,maxProteinRef)=>{
        ProteinChildToParent(minProteinRef,maxProteinRef);
    }
    const ProteinChildToParent = (minProteinRef,maxProteinRef) => {
        w3_close();
        props.onParentCallBackForSearchByProtein(minProteinRef,maxProteinRef);
    }

    function w3_open() {
        document.getElementById("mySidebar").style.display = "block";
    }

    function addRecipe() {
        w3_close();
        history.push("/");
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
                <Link onClick={addRecipe} to="/" className="w3-bar-item w3-button">Create New Recipe</Link>
                <div className="w3-bar-item w3-padding-16">
                    <SearchByIngredient parentCallBack={handleCallbackFromSearchByIngredient}/>
                </div>

                <div className="w3-bar-item w3-padding-16">
                    <SearchByCalories onParentCallBackForSearchByCalories ={CaloriesHandleCallback}/>
                    <SearchByCarbohydrate onParentCallBackForSearchByCarbohydrate = {CarbohydrateHandleCallback}/>
                    <SearchByFat onParentCallBackForSearchByFat = {FatHandleCallback}/>
                    <SearchByProtein onParentCallBackForSearchByProtein={ProteinHandleCallback}/>
                </div>
                <Link onClick={w3_close} className="w3-bar-item w3-button">Close Menu</Link>
            </nav>
            <div className="w3-top">
                <div className="w3-white w3-xlarge" style={{"maxWidth": "1200px", "margin": "auto"}}>
                    <button className="w3-button w3-padding-16 w3-left" onClick={w3_open}>☰</button>
                    <img src={image} alt="home" className="w3-right"  height="68" width="68"/>
                    <div className="w3-center w3-padding-16" >My Food</div>
                </div>
            </div>
        </div>
    );
}

export default TopMenu;
