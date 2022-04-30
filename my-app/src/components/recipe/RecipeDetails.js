import {useParams} from "react-router-dom";
import "../recipe/w3Recipes.css"
//import image from "../images/download.jpg";
import './recipe.css';
import Ingredients from "./ingredients/Ingredients";
import BasicGrid from "../UI/grid/BasicGrid";

const RecipeDetails = () => {
    const params = useParams();
    const image = 'data:image/jpeg;base64,' + params.image;
    console.log(image);

    return (
        <div className={"w3-main w3-content w3-padding"} style={{"maxWidth": "1200px", "marginTop": "100px"}}>
            <h1 className={"w3-row-padding w3-padding-16 w3-center w3-quarter"}>
                {params.recipeName}
            </h1>
            <BasicGrid image={image} text={params.recipeText}/>
            <Ingredients recipeName={params.recipeName} recipeText={params.recipeText}/>
        </div>
    );
}
export default RecipeDetails;