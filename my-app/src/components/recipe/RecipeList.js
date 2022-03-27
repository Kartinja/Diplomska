import '../UI/w3.css';
import Recipe from "./Recipe";
import React from "react";

const RecipeList = (props) => {
    return (
        <div id="food">
            {props.recipes.map((recipe) => (
                <Recipe title={recipe.title}
                        text={recipe.text}
                        image={recipe.image}
                />))
            }
        </div>
    );
}
export default RecipeList;