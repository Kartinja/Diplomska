import {useParams} from "react-router-dom";
import "../recipe/w3Recipes.css"
import './recipe.css';
import Ingredients from "./ingredients/Ingredients";
import BasicGrid from "../UI/grid/BasicGrid";
import React, {useCallback, useEffect, useState} from "react";

const RecipeDetails = () => {
    const params = useParams();
    const [image, setImage] = useState("");

    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchImageHandler = useCallback(async () => {
        setIsLoading(true);
        setError(null);
        try {
            const request = 'http://localhost:8080/recipe/by?recipeName=' + params.recipeName + "&recipeText=" + params.recipeText;
            const response = await fetch(request);
            if (!response.ok) {
                throw new Error('Something went wrong!');
            }

            const data = await response.json();
            setImage('data:image/jpeg;base64,' + data.image);

        } catch (error) {
            setError(error.message);
        }
        setIsLoading(false);
    }, [params.recipeName, params.recipeText]);

    useEffect(() => {
        fetchImageHandler()
    }, [fetchImageHandler]);


    return (
        <div className={"w3-main w3-content w3-padding"} style={{"maxWidth": "1200px", "marginTop": "100px"}}>
            {!error && <div><h1 className={"w3-row-padding w3-padding-16 w3-center w3-quarter"}>
                {params.recipeName}
            </h1>
                <BasicGrid image={image} text={params.recipeText}/>
                <Ingredients recipeName={params.recipeName} recipeText={params.recipeText}/>
            </div>}
        </div>
    );
}
export default RecipeDetails;