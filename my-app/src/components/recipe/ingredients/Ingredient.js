import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import * as React from "react";
import {useCallback, useEffect, useRef, useState} from "react";

const Ingredient = (props) => {
    const [recipeIngredient, setRecipeIngredient] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    const quantityRef = useRef('');
    const ingredientNameRef = useRef('');
    const [isResponseValid, setIsResponseValid] = useState(true);
    const formData = new FormData();

    async function editQuantityHandler() {
        const response = await fetch('http://localhost:8080/recipe/quantity?recipeName=' + props.recipeName + "&recipeText=" + props.recipeText + "&ingredientName=" + props.row.name + "&quantity=" + formData.get("quantity"), {
            method: 'POST',
            body: formData,
            headers: {
                'Accept': 'application/json'
            }
        });
        console.log(props.recipeName);
        console.log(props.recipeText);
        console.log(props.row.name);
        console.log("ingredient Name:" + formData.get("ingredientName"));
        if (!response.ok) {
            setIsResponseValid(false);
            return;
        }
        setIsResponseValid(true);
        fetchRecipeIngredientHandler();
        props.parentCallBack(true);
    }

    const fetchRecipeIngredientHandler = useCallback(async () => {
        setIsLoading(true);
        setError(null);

        try {
            const request = 'http://localhost:8080/recipe/quantity?recipeName=' + props.recipeName + "&recipeText=" + props.recipeText + "&ingredientName=" + props.row.name;
            const response = await fetch(request);
            if (!response.ok) {
                throw new Error('Something went wrong!');
            }

            const data = await response.json();
            if (data.protein === null) {
                data.protein = 0;
            }
            if (data.fat === null) {
                data.fat = 0;
            }
            if (data.energy === null) {
                data.energy = 0;
            }
            if (data.carbohydrate === null) {
                data.carbohydrate = 0;
            }
            setRecipeIngredient(data);
        } catch (error) {
            setError(error.message);
        }
        setIsLoading(false);
    }, [props.recipeName,props.recipeText,props.row.name]);

    useEffect(() => {
        fetchRecipeIngredientHandler()
    }, [fetchRecipeIngredientHandler]);

    function submitHandler(event) {
        event.preventDefault();
        formData.append('quantity', quantityRef.current.value);
        editQuantityHandler();
    }

    return (
        <TableRow key={props.row.name} sx={{'&:last-child td, &:last-child th': {border: 0}}}>
            {recipeIngredient &&
            <TableCell component="th" scope="row" ref={ingredientNameRef}>
                {props.row.name}
            </TableCell>}
            {recipeIngredient &&
            <TableCell component="th" scope="row">
                <form onSubmit={submitHandler} className="form">
                    <input ref={quantityRef} defaultValue={props.quantity} style={{borders:"none"}}/>
                    <button type="submit" className="search-button"/>
                </form>
            </TableCell>}
            {recipeIngredient && <TableCell align="right">{recipeIngredient.energy}</TableCell>}
            {recipeIngredient && <TableCell align="right">{recipeIngredient.fat}</TableCell>}
            {recipeIngredient && <TableCell align="right">{recipeIngredient.carbohydrate}</TableCell>}
            {recipeIngredient && <TableCell align="right">{recipeIngredient.protein}</TableCell>}
        </TableRow>
    )
}
export default Ingredient