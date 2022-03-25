import {useParams} from "react-router-dom";
import "../recipe/w3Recipes.css"
import image from "../images/download.jpg";
import './recipe.css';
import Ingredients from "./ingredients/Ingredients";

const RecipeDetails = () => {

    const params = useParams();
    console.log(params.recipeName);
    return (
        <div className={"w3-main w3-content w3-padding"} style={{"maxWidth": "1200px", "marginTop": "100px"}}>
            <h1 className={"w3-row-padding w3-padding-16 w3-center w3-quarter"}>
                {params.recipeName}
            </h1>
            <div className={"w3-row"}>
                <div className="w3-xlarge w3-animate-left w3-container w3-left ">
                    <img src={image} alt="food"/>
                </div>
                <div className={"w3-half w3-container"}>
                    <p className={"text"}>{params.recipeText}</p>
                </div>
            </div>
            <Ingredients recipeName={params.recipeName} recipeText={params.recipeText}/>
        </div>
    );
}
export default RecipeDetails;