import React, {useCallback, useEffect, useState} from "react";
import RecipeList from "../recipe/RecipeList";
import "../recipe/w3Recipes.css"
import AddRecipe from "../recipe/newRecipe/AddRecipe";
import {Alert} from "@mui/material";

const Home = (props) => {
    const [recipes, setRecipes] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleCallbackAddRecipe = () => {
        fetchRecipeHandler();
    }

    const fetchRecipeHandler = useCallback(async () => {
        setIsLoading(true);
        setError(null);
        try {
            let request;
            if (props.searchByCarbs.length === 2) {
                request = "http://localhost:8080/recipe/carbohydrate/" + props.searchByCarbs[0] + "/" + props.searchByCarbs[1];
            } else if (props.searchByFat.length === 2) {
                request = "http://localhost:8080/recipe/fat/" + props.searchByFat[0] + "/" + props.searchByFat[1];
            } else if (props.searchByProtein.length === 2) {
                request = "http://localhost:8080/recipe/protein/" + props.searchByProtein[0] + "/" + props.searchByProtein[1];
            } else if (props.searchByCalories.length === 2) {
                request = "http://localhost:8080/recipe/calories/" + props.searchByCalories[0] + "/" + props.searchByCalories[1];
            } else {
                request = 'http://localhost:8080/recipe/' + props.searchBy;
            }
            // if (props.searchByCarbs !== undefined) {
            //     request = "http://localhost:8080/recipe/carbohydrate/" + props.searchByCarbs[0] + "/" + props.searchByCarbs[1];
            // }
            // if (props.searchByFat !== undefined && request !== null) {
            //     request = "http://localhost:8080/recipe/fat/" + props.searchByFat[0] + "/" + props.searchByFat[1];
            // }
            // if (props.searchByProtein !== undefined && request !== null) {
            //     request = "http://localhost:8080/recipe/protein/" + props.searchByProtein[0] + "/" + props.searchByProtein[1];
            // }
            // if (props.searchByCalories !== undefined && request !== null) {
            //     request = "http://localhost:8080/recipe/calories/" + props.searchByCalories[0] + "/" + props.searchByCalories[1];
            // } else {
            //     request = 'http://localhost:8080/recipe/' + props.searchBy;
            // }

            //const request = 'http://localhost:8080/recipe/' + props.searchBy;
            const response = await fetch(request);
            if (!response.ok) {
                throw new Error('Something went wrong!');
            }

            const data = await response.json();
            console.log("recipe data")
            console.log(data);

            const transformedRecipe = data.map((recipeData) => {
                const base64Image = 'data:image/jpeg;base64,' + recipeData.image;

                return {
                    title: recipeData.name,
                    text: recipeData.text,
                    image: base64Image
                };
            });
            setRecipes(transformedRecipe);
        } catch (error) {
            setError(error.message);
        }
        setIsLoading(false);
    }, [props.searchBy, props.searchByCalories, props.searchByCarbs, props.searchByFat, props.searchByProtein]);

    useEffect(() => {
        fetchRecipeHandler()
    }, [fetchRecipeHandler]);


    let content = <Alert severity="info">Found no recipes.</Alert>;

    if (recipes.length > 0) {
        content = <RecipeList recipes={recipes}/>;
    }
    if (error) {
        content = <p>{error}</p>
    }
    if (isLoading) {

        content = <p>Loading...</p>
    }
    return (
        <div className={"w3-main w3-content w3-padding"}
             style={{"maxWidth": "1200px", "marginTop": "100px"}}>
            <section>
                <AddRecipe onParentCallback={handleCallbackAddRecipe}/>
            </section>
            <div className={"w3-row-padding w3-padding-16 w3-center"}>
                {content}
            </div>
        </div>
    );
}

export default Home;
