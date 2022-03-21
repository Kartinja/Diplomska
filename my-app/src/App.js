import TopMenu from "./components/UI/TopMenu";
import React, {useState, useEffect, useCallback} from "react";
import RecipeList from "./components/recipe/RecipeList";
import "./components/recipe/recipes.css"
import AddRecipe from "./components/recipe/newRecipe/AddRecipe";

const App = () => {
    const [recipes, setRecipes] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const [isRecipeValid, setIsRecipeValid] = useState(true);

    const fetchRecipeHandler = useCallback(async () => {
        setIsLoading(true);
        setError(null);
        try {
            const response = await fetch('http://localhost:8080/recipe');
            if (!response.ok) {
                throw new Error('Something went wrong!');
            }

            const data = await response.json();

            const transformedRecipe = data.map((recipeData) => {
                return {
                    title: recipeData.name,
                    text: recipeData.text
                };
            });
            setRecipes(transformedRecipe);
        } catch (error) {
            setError(error.message);
        }
        setIsLoading(false);
    }, []);

    useEffect(() => {
        fetchRecipeHandler();
    }, [fetchRecipeHandler]);

    async function addRecipeHandler(recipe) {
        const response = await fetch('http://localhost:8080/recipe', {
            method: 'POST',
            body: JSON.stringify(recipe),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            setIsRecipeValid(false);
            return;
        }
        await fetchRecipeHandler();
        setIsRecipeValid(true);
    }

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
        <div>
            <TopMenu/>
            <div className={"w3-main w3-content w3-padding"} style={{"maxWidth": "1200px", "marginTop": "100px"}}>
                <section>
                    <AddRecipe onAddRecipe={addRecipeHandler} isRecipeValid={isRecipeValid}/>
                    {!isRecipeValid && <p>There must be ingredients in the recipe you try to add.</p>}
                </section>
                <div className={"w3-row-padding w3-padding-16 w3-center"}>
                    {content}
                </div>
            </div>
        </div>
    );
}

export default App;
