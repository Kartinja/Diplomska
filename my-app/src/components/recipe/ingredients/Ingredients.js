import React, {useCallback, useEffect, useState} from "react";
import IngredientList from "./IngredientList";


const Ingredients = (props) => {
    const [recipeName, setRecipeName] = useState();
    const [carbsRecipeValue, setCarbsRecipeValue] = useState();
    const [fatRecipeValue, setFatRecipeValue] = useState();
    const [energyRecipeValue, setEnergyRecipeValue] = useState();
    const [proteinRecipeValue, setProteinRecipeValue] = useState();
    const [recipeText, setRecipeText] = useState();
    const [ingredients, setIngredients] = useState([]);
    const [quantity, setQuantity] = useState(new Map());
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleCallbackIngredientList = (isChanged) => {
        fetchIngredientsHandler();
    }

    const fetchIngredientsHandler = useCallback(async () => {
        setIsLoading(true);
        setError(null);
        try {
            const requestBy = 'http://localhost:8080/recipe/by?recipeName=' + props.recipeName + "&recipeText=" + props.recipeText;
            const responseBy = await fetch(requestBy);
            const request = "http://localhost:8080/recipe/quantities?recipeName=" + props.recipeName + "&recipeText=" + props.recipeText;
            const response = await fetch(request);
            if (!responseBy.ok) {
                throw new Error('Something went wrong!');
            }

            const ingredientsData = await responseBy.json();
            setProteinRecipeValue(ingredientsData.proteinValue);
            setFatRecipeValue(ingredientsData.fatValue);
            setEnergyRecipeValue(ingredientsData.energyValue);
            setCarbsRecipeValue(ingredientsData.carbohydrateValue);
            setRecipeName(ingredientsData.name);
            setRecipeText(ingredientsData.text);

            const ingredients = ingredientsData.ingredients.map((ingredientData) => {
                return {
                    name: ingredientData.name,
                    carbohydrate: ingredientData.carbohydrate,
                    energy: ingredientData.energy,
                    fat: ingredientData.fat,
                    protein: ingredientData.protein
                };
            });
            setIngredients(ingredients);
            console.log(ingredients);

            const data = await response.json();

            const ingredientQuantities = data.map((ingredientData) => {
                return {
                    name: ingredientData.ingredientName,
                    quantity: ingredientData.ingredientQuantity
                };
            });
            setQuantity(ingredientQuantities);
        } catch (error) {
            setError(error.message);
        }
        setIsLoading(false);
    }, [props.recipeName, props.recipeText]);

    useEffect(() => {
        fetchIngredientsHandler()
    }, [fetchIngredientsHandler]);


    let content = "";

    if (error) {
        content = <p>{error}</p>
    }
    if (isLoading) {

        content = <p>Loading...</p>
    }

    return (
        <div>
            {content}
            {!error && !isLoading &&
            <div>
                <IngredientList ingredientQuantity={quantity} ingredients={ingredients} recipeName={recipeName}
                                recipeText={recipeText} carbs={carbsRecipeValue} fat={fatRecipeValue}
                                protein={proteinRecipeValue} energy={energyRecipeValue} onParentCallback={handleCallbackIngredientList}/>
            </div>}
        </div>
    );

}
export default Ingredients;