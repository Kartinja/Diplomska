import React, {useCallback, useEffect, useState} from "react";
import IngredientList from "./IngredientList";


const Ingredients = (props) => {
    const [ingredients, setIngredients] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const fetchIngredientsHandler = useCallback(async () => {
        setIsLoading(true);
        setError(null);
        try {
            const request = 'http://localhost:8080/recipe/by?name=' + props.recipeName + "&text=" + props.recipeText;
            const response = await fetch(request);
            if (!response.ok) {
                throw new Error('Something went wrong!');
            }

            const data = await response.json();
            console.log(data.ingredients);

            const ingredients = data.ingredients.map((ingredientData) => {
                return {
                    name: ingredientData.name,
                    carbohydrate: ingredientData.carbohydrate,
                    energy: ingredientData.energy,
                    fat: ingredientData.fat,
                    protein: ingredientData.protein
                };
            });
            setIngredients(ingredients);
        } catch (error) {
            setError(error.message);
        }
        setIsLoading(false);
    }, [props.recipeName,props.recipeText]);

    useEffect(() => {
        fetchIngredientsHandler()
    }, [fetchIngredientsHandler]);

    let content = "";

    if (ingredients.length > 0) {
        content = <IngredientList ingredients={ingredients}/>;
    }
    if (error) {
        content = <p>{error}</p>
    }
    if (isLoading) {

        content = <p>Loading...</p>
    }

    return (
        <div>
                {content}
        </div>
    );

}
export default Ingredients;