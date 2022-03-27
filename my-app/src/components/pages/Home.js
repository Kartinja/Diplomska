import React, {useCallback, useEffect, useState} from "react";
import RecipeList from "../recipe/RecipeList";
import "../recipe/w3Recipes.css"
import AddRecipe from "../recipe/newRecipe/AddRecipe";

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
            const request = 'http://localhost:8080/recipe/' + props.searchBy;
            const response = await fetch(request);
            if (!response.ok) {
                throw new Error('Something went wrong!');
            }

            const data = await response.json();

            const transformedRecipe = data.map((recipeData) => {
                const base64Image = 'data:image/png;base64,'+recipeData.image;
                console.log(recipeData.image);
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
    }, [props.searchBy]);

    useEffect(() => {
        fetchRecipeHandler()
    }, [fetchRecipeHandler]);


    let content = <p>Found no recipes.</p>;

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
